package org.example.springMybatisBoard.model.repository;

import org.apache.ibatis.annotations.Param;
import org.example.springMybatisBoard.model.dto.FileDTO;

import java.util.List;

public interface FileRepository {
    int insertFiles(@Param("fileDTOList") List<FileDTO> fileDTOList);
    int insertFile(FileDTO fileDTO);
    List<FileDTO> selectFiles(int bno);
    FileDTO selectFile(int fno);
}
