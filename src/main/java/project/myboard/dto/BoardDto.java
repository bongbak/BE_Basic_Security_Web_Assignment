package project.myboard.dto;

import lombok.*;
import project.myboard.entity.BoardEntity;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {
    private Long id;
    private String writer;
    private String password;
    private String title;
    private String content;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;

    public BoardEntity toEntity() {
        return BoardEntity.builder()
                .id(id)
                .writer(writer)
                .password(password)
                .title(title)
                .content(content)
                .build();
    }

    public static BoardDto fromEntity(BoardEntity entity) {
        return BoardDto.builder()
                .id(entity.getId())
                .writer(entity.getWriter())
                .password(entity.getPassword())
                .title(entity.getTitle())
                .content(entity.getContent())
                .createdTime(entity.getCreatedTime())
                .modifiedTime(entity.getModifiedTime())
                .build();
    }
}
