/*
package org.example.springbootBoard.controller;

import jakarta.servlet.http.HttpServletResponse;

import org.example.springbootBoard.global.AlertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import static org.example.springbootBoard.global.AlertType.NO_AUTHORIZATION;


@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    private final FileService fileService;

    @Autowired
    public BoardController(BoardService boardService, FileService fileService) {
        this.boardService = boardService;
        this.fileService = fileService;
    }

    @GetMapping("/list")
    public ModelAndView getBoardList(@RequestParam(defaultValue = "1", value = "page") int page) throws SQLException {
        ModelAndView mav = new ModelAndView("list");
        mav.addObject("pageData", boardService.getBoards(page));
        return mav;
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

    @GetMapping("/download")
    public void download(@RequestParam("fno")int fno, HttpServletResponse response) throws IOException {
        FileDTO fileDTO = fileService.getFile(fno); // originalFile, savedPath 정보 조회함.

        String fileName = new String(fileDTO.getOriginalName().getBytes("UTF-8"), "ISO-8859-1");

        // response는 기본적으로 html을 응답하는 헤더가 설정되어 있음.
        // 하지만, 지금은 html을 응답하는게 아니라 파일 그 자체를 전송할거임. 응답 객체의 헤더정보를 파일전송 버전으로 변경하자!
        response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\""); // filename="right.png"
        response.setHeader("Content-Transfer-Encoding", "binary");

        FileInputStream fis = new FileInputStream(fileDTO.getSavedPath());
        OutputStream os = response.getOutputStream(); // response로 응답하는 스트림(문자열 단위 아니고 바이트 단위로 보내게 됨.)
        FileCopyUtils.copy(fis, os);
    }
}
*/
