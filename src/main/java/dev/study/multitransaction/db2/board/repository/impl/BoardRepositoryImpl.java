package dev.study.multitransaction.db2.board.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.study.multitransaction.db1.user.model.dto.UserBoardInfo;
import dev.study.multitransaction.db2.board.model.dto.FetchDetailBoardDto;
import dev.study.multitransaction.db2.board.model.dto.FetchMyBoard;
import dev.study.multitransaction.db2.board.repository.BoardRepositoryCustom;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Objects;

import static dev.study.multitransaction.db1.Log.entity.QLog.*;
import static dev.study.multitransaction.db1.user.model.entity.QUser.*;
import static dev.study.multitransaction.db2.board.model.entity.QBoard.*;

public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactoryDb1;
    private final JPAQueryFactory queryFactoryDb2;

    public BoardRepositoryImpl(@Qualifier("oneDBEntityManager") EntityManager em1,
                              @Qualifier("twoDBEntityManager") EntityManager em2) {
        this.queryFactoryDb1 = new JPAQueryFactory(em1);
        this.queryFactoryDb2 = new JPAQueryFactory(em2);
    }
    @Override
    public FetchDetailBoardDto getThisBoard(Long boardId) {

        FetchMyBoard boardInfo = queryFactoryDb2.select(Projections.fields(FetchMyBoard.class,
                        board.title,
                        board.content))
                .from(board)
                .where(board.boardId.eq(boardId))
                .fetchOne();

        UserBoardInfo userInfo = queryFactoryDb1.select(Projections.fields(UserBoardInfo.class,
                        user.email,
                        log.regDate))
                .from(user)
                .leftJoin(log)
                .on(user.userId.eq(log.user.userId))
                .where(log.boardId.eq(boardId))
                .fetchOne();


        return FetchDetailBoardDto.builder()
                .email(Objects.requireNonNull(userInfo).getEmail())
                .title(Objects.requireNonNull(boardInfo).getTitle())
                .content(boardInfo.getContent())
                .regDate(userInfo.getRegDate())
                .build();

    }
}
