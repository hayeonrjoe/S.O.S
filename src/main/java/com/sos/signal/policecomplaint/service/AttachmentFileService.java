package com.sos.signal.policecomplaint.service;

import com.sos.signal.policecomplaint.dto.AttachmentFile;
import com.sos.signal.policecomplaint.repository.AttachmentFileRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
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
                AttachmentFile attachmentFile = new AttachmentFile();
                String originalFileName = file.getOriginalFilename();

                // Extracting file name without extension
                String fileName = originalFileName.substring(0, originalFileName.lastIndexOf("."));

                // Extracting file extension
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);

                attachmentFile.setAf_file_name(fileName);
                attachmentFile.setAf_file_extension(fileExtension);
                attachmentFile.setAf_upload_path("c:/sos/attachments/");
                attachmentFileRepository.save(attachmentFile);

                File destinationFile = new File(attachmentFile.getAf_upload_path() + attachmentFile.getAf_file_id());
                file.transferTo(destinationFile);
                fileIds.append(attachmentFile.getAf_file_id()).append("-");
            }
        }
        return fileIds.toString();
    }

//    // Set the fileIds in the PoliceComplaint object
//        policeComplaint.setPc_file_ids(fileIds.toString());
//
//    // Save the updated PoliceComplaint object
//        policeComplaintRepository.save(policeComplaint);

    //                // Constructing the file name with extension
//                String fileNameWithExtension = attachmentFile.getAf_file_id() + "." + fileExtension;
    public void downloadAttachmentFiles(String fileId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        File file = new File("c:/sos/attachments/" + fileId);
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));

        AttachmentFile attachmentFile = attachmentFileRepository.findById(Integer.parseInt(fileId)).orElse(null);

        if (attachmentFile == null) {
            // Handle file not found error
            return;
        }

        String header = request.getHeader("User-Agent");
        String fileName = attachmentFile.getAf_file_name();

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

