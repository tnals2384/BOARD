package org.example.springbootBoard.model.dto;

import java.util.List;

public class MusicPageInfo {
    private int page;
    private int totalPageCount;
    private int startPage;
    private int endPage;
    private List<MusicDTO> musicList;

    public MusicPageInfo(int page, int totalPageCount, int startPage, int endPage, List<MusicDTO> musicList) {
        this.page = page;
        this.totalPageCount = totalPageCount;
        this.startPage = startPage;
        this.endPage = endPage;
        this.musicList = musicList;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public int getStartPage() {
        return startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public List<MusicDTO> getMusicList() {
        return musicList;
    }
}
