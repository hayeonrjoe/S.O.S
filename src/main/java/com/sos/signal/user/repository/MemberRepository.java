package com.sos.signal.user.repository;

import com.sos.signal.user.dto.KakaoDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    @Autowired
    private SqlSessionTemplate sql;

    public void kakaoinsert(KakaoDTO kakaoDTO) {
        sql.insert("Member.kakaoInsert", kakaoDTO);
    }

    public KakaoDTO findkakao(String email) {
//        System.out.println("RE:" + userInfo.get("email"));
//        System.out.println("RR:" + userInfo.get("age_range"));
        return sql.selectOne("Member.findKakao", email);
    }
}
