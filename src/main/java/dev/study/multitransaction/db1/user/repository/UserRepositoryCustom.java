package dev.study.multitransaction.db1.user.repository;

import dev.study.multitransaction.db1.user.model.dto.GetMyBoardDto;

import java.util.List;

public interface UserRepositoryCustom {

    List<GetMyBoardDto> getMyBoard(Long userId);

}
