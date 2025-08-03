package project.myboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.myboard.dto.BoardDto;
import project.myboard.entity.BoardEntity;
import project.myboard.repository.BoardRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void saveBoard(BoardDto boardDto) {
        BoardEntity boardEntity = boardDto.toEntity();
        boardRepository.save(boardEntity);
    }

    public List<BoardDto> findAllList() {
        List<BoardEntity> entityList = boardRepository.findAll();
        List<BoardDto> dtoList = new ArrayList<>();
        for (BoardEntity entity : entityList) {
            dtoList.add(BoardDto.fromEntity(entity));
        }
        return dtoList;
    }

    public BoardDto findById(Long id) {
        return boardRepository.findById(id)
                .map(BoardDto::fromEntity)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다: " + id));
    }

    @Transactional
    public void update(Long id, BoardDto boardDto) {
        BoardEntity entity = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다: " + id));
        entity.updateBoard(boardDto);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!boardRepository.existsById(id)) {
            throw new IllegalArgumentException("해당 게시글이 존재하지 않아 삭제할 수 없습니다: " + id);
        }
        boardRepository.deleteById(id);
    }
    @Transactional
    public BoardEntity save(BoardDto dto) {
        BoardEntity entity = dto.toEntity();
        return boardRepository.save(entity);
    }

}