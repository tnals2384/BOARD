package org.example.springBoard.controller;

import org.example.springBoard.global.AlertException;
import org.example.springBoard.model.dto.BoardDTO;
import org.example.springBoard.model.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

import static org.example.springBoard.global.AlertType.*;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public String getBoardList(Model model) throws SQLException {
        model.addAttribute("boardList", boardService.getBoards());
        return "list";
    }

    @GetMapping("/write")
    public String WriteForm() {
        return "writeForm";
    }

    @PostMapping("/write")
    public String write(@SessionAttribute("memberId") String memberId,
                        @ModelAttribute BoardDTO boardDTO) throws SQLException {
        int boardId = boardService.write(memberId, boardDTO);

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

        if(!boardService.checkWriter(memberId, board.getWriter())) {
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
