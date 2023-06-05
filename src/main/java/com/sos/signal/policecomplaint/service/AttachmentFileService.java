package com.sos.signal.policecomplaint.service;

import com.sos.signal.policecomplaint.dto.AttachmentFile;
import com.sos.signal.policecomplaint.repository.AttachmentFileRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

@Service
public class AttachmentFileService {

    private final AttachmentFileRepository attachmentFileRepository;

    public AttachmentFileService(AttachmentFileRepository attachmentFileRepository) {
        this.attachmentFileRepository = attachmentFileRepository;
    }

    public String saveAttachmentFiles(MultipartFile[] files) throws IOException {
        StringBuilder fileIds = new StringBuilder();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                AttachmentFile afile = new AttachmentFile();
                afile.setAf_file_name(file.getOriginalFilename());
                afile.setAf_upload_path("c:/sos/upload/");
                attachmentFileRepository.save(afile);
                File dfile = new File(afile.getAf_upload_path() + afile.getPc_file_id());
                file.transferTo(dfile);
                fileIds.append(afile.getPc_file_id()).append("-");
            }
        }
        return fileIds.toString();
    }

    public void downloadAttachmentFile(String fileId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        File file = new File("c:/sos/upload/" + fileId);
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));

        AttachmentFile afile = attachmentFileRepository.findById(Integer.parseInt(fileId)).orElse(null);

        if (afile == null) {
            // Handle file not found error
            return;
        }

        String header = request.getHeader("User-Agent");
        String fileName = afile.getAf_file_name();

        if ((header.contains("MSIE")) || (header.contains("Trident")) || (header.contains("Edge"))) {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } else {
            fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        FileCopyUtils.copy(in, response.getOutputStream());
        in.close();
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }
}

