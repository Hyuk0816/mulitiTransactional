package dev.study.multitransaction.db2.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FetchMyBoard {

    private Long boardId;
    private String title;
    private String content;

}
