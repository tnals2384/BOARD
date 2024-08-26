package org.example.springMybatisBoard.controller;

import org.example.springMybatisBoard.global.AlertException;
import org.example.springMybatisBoard.model.dto.BoardDTO;
import org.example.springMybatisBoard.model.dto.FileDTO;
import org.example.springMybatisBoard.model.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.example.springMybatisBoard.global.AlertType.NO_AUTHORIZATION;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;


    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public String getBoardList(Model model, @RequestParam(defaultValue = "1", value = "page") int page) throws SQLException {
        model.addAttribute("pageData", boardService.getBoards(page));
        return "list";
    }

    @GetMapping("/write")
    public String WriteForm() {
        return "writeForm";
    }

    @PostMapping("/write")
    public String write(@SessionAttribute("memberId") String memberId,
                        @ModelAttribute BoardDTO boardDTO,
                        @RequestPart(name = "file", required = false) MultipartFile[] uploadFile
    ) throws SQLException, IOException {
        int boardId = boardService.write(memberId, boardDTO,uploadFile);
        return "redirect:/board/" + boardId;
    }


    @GetMapping("/{bno}")
    public String getBoardDetail(@PathVariable("bno") int bno, Model model) throws SQLException {

        BoardDTO board = boardService.read(bno);

        model.addAttribute("board", board);
        return "detail";
    }


    @GetMapping("/{bno}/edit")
    public String editForm(@SessionAttribute("memberId") String memberId,
                           @PathVariable("bno") int bno,
                           Model model) throws SQLException {

        BoardDTO board = boardService.getBoard(bno);

        if (!boardService.checkWriter(memberId, board.getWriter())) {
            throw new AlertException(NO_AUTHORIZATION);
        }

        model.addAttribute("board", board);
        return "editForm";
    }

    @PostMapping("/{bno}/edit")
    public String update(@SessionAttribute("memberId") String memberId,
                         @PathVariable("bno") int bno,
                         @ModelAttribute BoardDTO boardDTO) throws SQLException {

        boardService.update(memberId, bno, boardDTO);
        return "redirect:/board/" + bno;
    }

    @PostMapping("/{bno}/delete")
    public String delete(@SessionAttribute("memberId") String memberId,
                         @PathVariable("bno") int bno) throws SQLException {

        boardService.delete(memberId, bno);
        return "redirect:/board/list";
    }
}
