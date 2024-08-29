package org.example.springbootBoard.model.dto;

public class MusicDTO {

    private int id;
    private String title;
    private String artist;
    private String releaseYear;
    private String musicLink;
    private String albumCover;
    private String description;
    private String member;
    private String createdAt;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setAlbumCover(String albumCover) {
        this.albumCover = albumCover;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public String getMusicLink() {
        return musicLink;
    }

    public String getAlbumCover() {
        return albumCover;
    }

    public String getDescription() {
        return description;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setMusicLink(String musicLink) {
        this.musicLink = musicLink;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
