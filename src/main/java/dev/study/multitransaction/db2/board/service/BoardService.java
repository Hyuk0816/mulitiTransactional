package dev.study.multitransaction.db2.board.service;


import dev.study.multitransaction.db2.board.model.dto.FetchDetailBoardDto;
import dev.study.multitransaction.db2.board.model.vo.request.BoardCreateRequestVo;

public interface BoardService {

    void saveBoard(BoardCreateRequestVo requestVo);

    FetchDetailBoardDto getDetailBoard(Long boardId);
}
