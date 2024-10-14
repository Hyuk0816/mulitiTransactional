package dev.study.multitransaction.db1.user.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.study.multitransaction.db1.Log.entity.QLog;
import dev.study.multitransaction.db1.user.model.dto.GetMyBoardDto;
import dev.study.multitransaction.db1.user.model.dto.UserBoardInfo;
import dev.study.multitransaction.db1.user.model.entity.QUser;
import dev.study.multitransaction.db1.user.repository.UserRepositoryCustom;
import dev.study.multitransaction.db2.board.model.dto.FetchMyBoard;
import dev.study.multitransaction.db2.board.model.entity.QBoard;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

import static dev.study.multitransaction.db1.Log.entity.QLog.*;
import static dev.study.multitransaction.db1.user.model.entity.QUser.*;
import static dev.study.multitransaction.db2.board.model.entity.QBoard.*;


public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactoryDb1;
    private final JPAQueryFactory queryFactoryDb2;

    public UserRepositoryImpl(@Qualifier("oneDBEntityManager") EntityManager em1,
                              @Qualifier("twoDBEntityManager") EntityManager em2) {
        this.queryFactoryDb1 = new JPAQueryFactory(em1);
        this.queryFactoryDb2 = new JPAQueryFactory(em2);
    }

    @Override
    public List<GetMyBoardDto> getMyBoard(Long userId) {

        List<UserBoardInfo> fetch = queryFactoryDb1
                .select(Projections.fields(UserBoardInfo.class,
                        user.email.as("email"),
                        log.regDate.as("regDate")))
                .from(user)
                .leftJoin(log).on(user.userId.eq(log.user.userId))
                .where(user.userId.eq(userId))
                .fetch();

        List<FetchMyBoard> myBoards = queryFactoryDb2
                .select(Projections.fields(FetchMyBoard.class,
                        board.boardId.as("boardId"),
                        board.title.as("title"),
                        board.content.as("content")))
                .from(board)
                .where(board.userId.eq(userId))
                .fetch();

        // GetMyBoardDto 리스트로 변환
        List<GetMyBoardDto> result = new ArrayList<>();

        if (!fetch.isEmpty() && !myBoards.isEmpty()) {
            UserBoardInfo userInfo = fetch.get(0);
            for (FetchMyBoard myBoard : myBoards) {
                GetMyBoardDto dto = GetMyBoardDto.builder()
                        .email(userInfo.getEmail())
                        .boardId(myBoard.getBoardId())
                        .title(myBoard.getTitle())
                        .content(myBoard.getContent())
                        .regDate(userInfo.getRegDate())
                        .build();
                result.add(dto);
            }
        }

        return result;
    }


}
