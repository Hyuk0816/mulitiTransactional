package dev.study.multitransaction.db1.user.service;


import dev.study.multitransaction.db1.user.model.dto.GetMyBoardDto;
import dev.study.multitransaction.db1.user.model.vo.request.RegisterRequestVo;

import java.util.List;

public interface UserService {

    void saveUser(RegisterRequestVo requestVo);

    List<GetMyBoardDto> getMyBoard();
}
