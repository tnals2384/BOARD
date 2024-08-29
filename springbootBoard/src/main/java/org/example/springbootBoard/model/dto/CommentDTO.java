package org.example.springbootBoard.model.dto;

import java.util.ArrayList;
import java.util.List;

public class CommentDTO {
    private int id;
    private String writer;
    private String content;
    private String createdAt;
    private List<CommentDTO> childList;

    public CommentDTO() {
        this.childList = new ArrayList<>();
    }

    public CommentDTO(String writer, String content) {
        this.writer = writer;
        this.content = content;
    }

    public CommentDTO(int id, String writer, String content, String createdAt, List<CommentDTO> childList) {
        this.id = id;
        this.writer = writer;
        this.content = content;
        this.createdAt = createdAt;
        this.childList = childList;
    }

    public int getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public List<CommentDTO> getChildList() {
        return childList;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setChildList(List<CommentDTO> childList) {
        this.childList = childList;
    }
}
