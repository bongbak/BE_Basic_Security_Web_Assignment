package project.myboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.myboard.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
