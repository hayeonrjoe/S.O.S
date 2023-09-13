package com.sos.signal.user.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sos.signal.user.dto.KakaoDTO;
import com.sos.signal.user.repository.MemberRepository;
import okhttp3.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@Service
public class MemberService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberService.class);

    private static final String KAKAO_AUTH_URL = "https://kauth.kakao.com/oauth/token";
    private static final String KAKAO_USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";
    private static final String CLIENT_ID = "d52eb1fa182c6d476ac8b1e6b2b73099";
    private static final String REDIRECT_URI = "http://localhost:8080/login";

    @Autowired
    private HttpSession session;

    @Autowired
    private MemberRepository mr;

    public String getAccessToken(String authorizeCode) {
        String accessToken = "";

        OkHttpClient client = new OkHttpClient();

        // Build the request
        Request request = new Request.Builder()
                .url(KAKAO_AUTH_URL)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("charset", "utf-8")
                .post(new FormBody.Builder()
                        .add("grant_type", "authorization_code")
                        .add("client_id", CLIENT_ID)
                        .add("redirect_uri", REDIRECT_URI)
                        .add("code", authorizeCode)
                        .build())
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                ResponseBody responseBody = response.body();
                if (responseBody != null) {
                    String result = responseBody.string();
                    LOGGER.info("Kakao Auth Response: {}", result);

                    // Parse the JSON response to get access_Token
                    accessToken = parseAccessToken(result);
                }
            }
        } catch (IOException e) {
            LOGGER.error("Error getting access token from Kakao: {}", e.getMessage(), e);
        }

        return accessToken;
    }

    public KakaoDTO getUserInfo(String accessToken) {
        final KakaoDTO[] kakaoDTO = {new KakaoDTO()}; // Create an array to hold kakaoDTO

        OkHttpClient client = new OkHttpClient();

        // Build the request
        Request request = new Request.Builder()
                .url(KAKAO_USER_INFO_URL)
                .addHeader("Authorization", "Bearer " + accessToken)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            int responseCode = response.code();
            LOGGER.info("Kakao User Info Response Code: {}", responseCode);

            if (responseCode == 200) {
                ResponseBody responseBody = response.body();
                if (responseBody != null) {
                    String responseStr = responseBody.string();
                    LOGGER.info("Kakao User Info Response Body: {}", responseStr);
                    KakaoDTO parsedKakaoDTO = parseKakaoDTO(responseStr);
                    kakaoDTO[0] = parsedKakaoDTO; // Update the array
                }
            }
        } catch (IOException e) {
            LOGGER.error("Error getting user info from Kakao: {}", e.getMessage(), e);
        }

        session.setAttribute("kakaoE", kakaoDTO[0].getU_email());
        session.setAttribute("kakaoA", kakaoDTO[0].getU_age_range());

        Optional<KakaoDTO> result = Optional.ofNullable(mr.findkakao(kakaoDTO[0].getU_email()));
        result.ifPresentOrElse(
                existingKakaoDTO -> LOGGER.info("User already exists: {}", existingKakaoDTO),
                () -> {
                    mr.kakaoinsert(kakaoDTO[0]);
                    LOGGER.info("New user added: {}", kakaoDTO[0]);
                }
        );

        return kakaoDTO[0];
    }

    private String parseAccessToken(String json) {
        JsonElement element = JsonParser.parseString(json);
        JsonObject jsonObject = element.getAsJsonObject();
        return jsonObject.get("access_token").getAsString();
    }

    private KakaoDTO parseKakaoDTO(String json) {
        JsonElement element = JsonParser.parseString(json);
        JsonObject jsonObject = element.getAsJsonObject();
        JsonObject kakaoAccount = jsonObject.getAsJsonObject("kakao_account");

        KakaoDTO kakaoDTO = new KakaoDTO();
        kakaoDTO.setU_email(kakaoAccount.get("email").getAsString());
        kakaoDTO.setU_age_range(kakaoAccount.get("age_range").getAsString());

        return kakaoDTO;
    }
}
