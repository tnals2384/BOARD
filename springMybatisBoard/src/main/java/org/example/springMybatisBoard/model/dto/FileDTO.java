package org.example.springMybatisBoard.model.dto;

public class FileDTO {
    private int fno;
    private int bno;

    public void setFno(int fno) {
        this.fno = fno;
    }

    public FileDTO( String originalName, String savedPath) {
        this.originalName = originalName;
        this.savedPath = savedPath;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public void setSavedPath(String savedPath) {
        this.savedPath = savedPath;
    }

    public int getFno() {
        return fno;
    }

    public int getBno() {
        return bno;
    }

    public String getOriginalName() {
        return originalName;
    }

    private String originalName;

    public String getSavedPath() {
        return savedPath;
    }

    private String savedPath;

    @Override
    public String toString() {
        return "FileDTO{" +
                "fno=" + fno +
                ", bno=" + bno +
                ", originalName='" + originalName + '\'' +
                ", savedPath='" + savedPath + '\'' +
                '}';
    }
}
