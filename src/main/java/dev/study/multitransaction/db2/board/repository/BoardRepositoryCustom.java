package dev.study.multitransaction.db2.board.repository;

import dev.study.multitransaction.db2.board.model.dto.FetchDetailBoardDto;

public interface BoardRepositoryCustom {

    FetchDetailBoardDto getThisBoard(Long boardId);
}
