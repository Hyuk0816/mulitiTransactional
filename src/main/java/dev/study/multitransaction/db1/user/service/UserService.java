package dev.study.multitransaction.db1.user.service;


import dev.study.multitransaction.db1.user.model.vo.request.RegisterRequestVo;

public interface UserService {

    void saveUser(RegisterRequestVo requestVo);
}
