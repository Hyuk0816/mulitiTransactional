package dev.study.multitransaction.db2.board.service.impl;

import dev.study.multitransaction.db1.Log.entity.Log;
import dev.study.multitransaction.db1.Log.repository.LogRepository;
import dev.study.multitransaction.db1.user.constants.UserConstants;
import dev.study.multitransaction.db1.user.model.entity.User;
import dev.study.multitransaction.db1.user.repository.UserRepository;
import dev.study.multitransaction.db2.board.model.entity.Board;
import dev.study.multitransaction.db2.board.model.vo.request.BoardCreateRequestVo;
import dev.study.multitransaction.db2.board.repository.BoardRepository;
import dev.study.multitransaction.db2.board.service.BoardService;
import dev.study.multitransaction.util.exception.UserNameNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final LogRepository logRepository;

    @Override
    @Transactional
    public void saveBoard(BoardCreateRequestVo requestVo) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new UserNameNotFoundException(UserConstants.STATUS_404,UserConstants.MESSAGE_404));


        Board board = Board.builder()
                .title(requestVo.getTitle())
                .content(requestVo.getContent())
                .userId(user.getUserId())
                .build();
        //Db2
        boardRepository.save(board);

        Log log = Log.builder()
                .boardId(board.getBoardId())
                .user(user)
                .regDate(LocalDateTime.now())
                .build();
        //Db1
        logRepository.save(log);
    }
}
