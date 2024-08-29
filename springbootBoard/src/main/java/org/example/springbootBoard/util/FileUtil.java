package org.example.springbootBoard.util;


import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Random;

@Component
public class FileUtil {
    public String saveFile(MultipartFile uploadFile) throws IOException {
        if (uploadFile != null) {
            String uploadPath = "/Users/sumin/devcourse/upload/";
            if (!new File(uploadPath).exists())
                new File(uploadPath).mkdir();

            // 원본 파일명에서 확장자 추출
            String originalFilename = uploadFile.getOriginalFilename();
            String fileExtension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }

            // 랜덤한 파일 이름 생성
            String savedName = new Random().nextInt(1000000000) + fileExtension;
            File savedFile = new File(uploadPath + savedName);
            uploadFile.transferTo(savedFile); // 클라이언트가 업로드한 파일을 서버 컴퓨터 폴더에 저장

            return savedName;
        }
        return null;
    }
}
