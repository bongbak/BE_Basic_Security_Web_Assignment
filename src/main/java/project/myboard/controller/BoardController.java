package project.myboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.myboard.dto.BoardDto;

import jakarta.validation.Valid;
import project.myboard.service.BoardService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class BoardController {

    private final BoardService boardService;

    // 글쓰기 페이지 진입
    @GetMapping
    public String postForm() {
        return "board/post.html";
    }

    // 글 작성 후 저장
    @PostMapping
    public String save(@Valid @ModelAttribute BoardDto boardDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "board/post.html";
        }
        boardService.saveBoard(boardDto);
        return "redirect:/post/list";
    }

    // 게시글 목록
    @GetMapping("/list")
    public String list(Model model) {
        List<BoardDto> boardDtoList = boardService.findAllList();
        model.addAttribute("boardList", boardDtoList);
        return "board/list.html";
    }

    // 게시글 상세 보기
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        BoardDto boardDto = boardService.findById(id);
        model.addAttribute("boardDto", boardDto);
        return "board/detail.html";
    }

    // 게시글 수정 페이지
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        BoardDto boardDto = boardService.findById(id);
        model.addAttribute("boardDto", boardDto);
        return "board/update.html";
    }

    // 게시글 수정 반영
    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute BoardDto boardDto) {
        boardService.update(id, boardDto);
        return "redirect:/post/" + id;
    }

    // 게시글 삭제
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        boardService.deleteById(id);
        return "redirect:/post/list";
    }
}