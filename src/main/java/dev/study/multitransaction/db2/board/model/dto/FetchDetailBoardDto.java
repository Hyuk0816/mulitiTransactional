package dev.study.multitransaction.db2.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FetchDetailBoardDto {

    private String email;
    private String title;
    private String content;
    private LocalDateTime regDate;
}
