package project.myboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.myboard.dto.BoardDto;
import project.myboard.entity.BoardEntity;
import project.myboard.service.BoardService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardApiController {

    private final BoardService boardService;

    // 글 작성
    @PostMapping
    public ResponseEntity<BoardEntity> save(@RequestBody BoardDto dto) {
        BoardEntity saved = boardService.save(dto);
        return ResponseEntity.ok(saved);
    }

    // 특정 글 조회
    @GetMapping("/{id}")
    public ResponseEntity<BoardDto> get(@PathVariable Long id) {
        BoardDto dto = boardService.findById(id);
        return ResponseEntity.ok(dto);
    }

    // 전체 글 조회
    @GetMapping
    public ResponseEntity<List<BoardDto>> getAll() {
        List<BoardDto> boards = boardService.findAllList();
        return ResponseEntity.ok(boards);
    }

    // 글 수정
    @PatchMapping("/{id}")
    public ResponseEntity<BoardDto> update(@PathVariable Long id, @RequestBody BoardDto dto) {
        boardService.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    // 글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boardService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
