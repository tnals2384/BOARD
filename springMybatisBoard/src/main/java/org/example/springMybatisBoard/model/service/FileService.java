package org.example.springMybatisBoard.model.service;

import org.example.springMybatisBoard.global.AlertException;
import org.example.springMybatisBoard.model.dto.FileDTO;
import org.example.springMybatisBoard.model.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.example.springMybatisBoard.global.AlertType.FAILED_FILE_UPLOAD;

@Service
public class FileService {

    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public void saveFiles(int bno, MultipartFile[] uploadFile) throws IOException {
        List<FileDTO> fileDTOList = new ArrayList<>();
        if (uploadFile != null && uploadFile.length > 0) {
            String uploadPath = "c:/programmers_upload/";
            if (!new File(uploadPath).exists())
                new File(uploadPath).mkdir();

            for (MultipartFile f : uploadFile) {
                String savedName = new Random().nextInt(1000000000) + "";
                File savedFile = new File(uploadPath + savedName);
                f.transferTo(savedFile); //클라이언트가 업로드한 파일을 서버 컴퓨터 폴더에 비어있는 c:/programmers_upload/18452133 파일에 저장시킴

                fileDTOList.add(new FileDTO(f.getOriginalFilename(), uploadPath + savedName));
            }

            if(saveFileInfos(bno, fileDTOList) < 0)
                throw new AlertException(FAILED_FILE_UPLOAD);
        }
    }

    public int saveFileInfos(int bno, List<FileDTO> fileDTOList) {
        if (fileDTOList == null || fileDTOList.isEmpty()) return 0;

        for (FileDTO f : fileDTOList) {
            f.setBno(bno);
        }
        return fileRepository.insertFiles(fileDTOList);
    }
}
