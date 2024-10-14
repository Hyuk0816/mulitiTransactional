package dev.study.multitransaction.db2.board.controller;

import dev.study.multitransaction.db2.board.model.constants.BoardConstants;
import dev.study.multitransaction.db2.board.model.dto.FetchDetailBoardDto;
import dev.study.multitransaction.db2.board.model.vo.request.BoardCreateRequestVo;
import dev.study.multitransaction.db2.board.model.vo.response.BoardStatusResponse;
import dev.study.multitransaction.db2.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;


    @PostMapping("/board")
    public ResponseEntity<BoardStatusResponse> createBoard(@RequestBody BoardCreateRequestVo requestVo){
        boardService.saveBoard(requestVo);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new BoardStatusResponse(BoardConstants.STATUS_201,BoardConstants.MESSAGE_201));
    }


    @GetMapping("/detail_board")
    public ResponseEntity<FetchDetailBoardDto> fetchDetailBoard(@RequestParam("boardId") Long boardId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(boardService.getDetailBoard(boardId));
    }
}
