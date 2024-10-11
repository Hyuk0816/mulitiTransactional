package dev.study.multitransaction.db1.user.service;

import dev.study.multitransaction.db1.user.model.dto.LoginDto;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    void login(LoginDto loginDto, HttpServletResponse response);
}
