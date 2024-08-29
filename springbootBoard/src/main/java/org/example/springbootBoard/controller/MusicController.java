package org.example.springbootBoard.controller;


import org.example.springbootBoard.model.dto.MusicDTO;
import org.example.springbootBoard.model.service.MusicService;
import org.example.springbootBoard.util.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/music")
public class MusicController {

    private final MusicService musicService;

    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @GetMapping("/list")
    public ModelAndView getMusicList(@RequestParam(defaultValue = "1", value = "page")int page) {
        ModelAndView mav = new ModelAndView("list");
        mav.addObject("musicPageInfo", musicService.getMusics(page));
        return mav;
    }

    @GetMapping("/write")
    public String writeForm() {
        return "writeForm";
    }

    @PostMapping("/write")
    public String write(@SessionAttribute("memberId") String memberId,
                        @ModelAttribute MusicDTO musicDTO,
                        @RequestPart(name = "file", required = false) MultipartFile file) throws IOException {

        int musicId = musicService.write(memberId, musicDTO, file);

        return "redirect:/music/" + musicId;
    }

    @GetMapping("/{musicId}")
    public ModelAndView getMusicDetail(@PathVariable("musicId")int musicId) {
        ModelAndView mav = new ModelAndView("detail");
        mav.addObject("music", musicService.getMusicInfo(musicId));

        return mav;
    }
}
