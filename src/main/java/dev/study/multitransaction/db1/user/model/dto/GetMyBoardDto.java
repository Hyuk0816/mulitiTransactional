package dev.study.multitransaction.db1.user.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetMyBoardDto {

    private String email;
    private Long boardId;
    private String title;
    private String content;
    private LocalDateTime regDate;
}
