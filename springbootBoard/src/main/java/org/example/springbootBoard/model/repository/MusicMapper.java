package org.example.springbootBoard.model.repository;

import org.apache.ibatis.annotations.Mapper;
import org.example.springbootBoard.model.dto.MusicDTO;

import java.util.List;

@Mapper
public interface MusicMapper {
    int getMusicCount();
    List<MusicDTO> selectMusicListByPage(int page, int size);
    MusicDTO selectMusic(int musicId);
    MusicDTO insert(MusicDTO musicDTO);
}
