package org.example.springbootBoard.model.service;

import org.example.springbootBoard.global.AlertException;
import org.example.springbootBoard.model.dto.MusicDTO;
import org.example.springbootBoard.model.dto.MusicPageInfo;
import org.example.springbootBoard.model.repository.MusicMapper;
import org.example.springbootBoard.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.example.springbootBoard.global.AlertType.MUSIC_NOT_FOUND;

@Service
public class MusicService {
    private final MusicMapper musicMapper;
    private final FileUtil fileUtil;
    private static final int COUNT_PER_PAGE = 10;

    @Autowired
    public MusicService(MusicMapper musicMapper, FileUtil fileUtil) {
        this.musicMapper = musicMapper;
        this.fileUtil = fileUtil;
    }

    public MusicPageInfo getMusics(int page) {
        int totalCount = musicMapper.getMusicCount();
        int totalPageCount = totalCount / COUNT_PER_PAGE;
        if (totalPageCount % COUNT_PER_PAGE > 0) totalPageCount++;

        int startPage = (page - 1) / 10 * 10 + 1;
        int endPage = Math.min(totalPageCount, startPage + 9);

        int startRow = (page - 1) * COUNT_PER_PAGE;
        List<MusicDTO> musicList = musicMapper.selectMusicListByPage(startRow, COUNT_PER_PAGE);

        return new MusicPageInfo(page, totalPageCount, startPage, endPage, musicList);
    }

    public MusicDTO getMusicInfo(int musicId) {
        MusicDTO musicDTO = musicMapper.selectMusic(musicId);
        if (musicDTO == null) throw new AlertException(MUSIC_NOT_FOUND);

        return musicDTO;
    }

    @Transactional
    public int write(String memberId, MusicDTO musicDTO, MultipartFile file) throws IOException {

        musicDTO.setMember(memberId);
        musicDTO.setAlbumCover(fileUtil.saveFile(file));
        return musicMapper.insert(musicDTO).getId();
    }
}
