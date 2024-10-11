package dev.study.multitransaction.db1.user.service.impl;

import dev.study.multitransaction.db1.user.constants.UserConstants;
import dev.study.multitransaction.db1.user.model.entity.User;
import dev.study.multitransaction.db1.user.model.enums.ROLE;
import dev.study.multitransaction.db1.user.model.vo.request.RegisterRequestVo;
import dev.study.multitransaction.db1.user.repository.UserRepository;
import dev.study.multitransaction.db1.user.service.UserService;
import dev.study.multitransaction.util.exception.UsersAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public void saveUser(RegisterRequestVo requestVo) {

        var checkUserVo = RegisterRequestVo.builder()
                .email(requestVo.getEmail())
                .name(requestVo.getName())
                .password(passwordEncoder.encode(requestVo.getPassword()))
                .nickName(requestVo.getNickName())
                .build();

        Optional<User> optionalUsers = userRepository.findByEmail(checkUserVo.getEmail());
        if (optionalUsers.isPresent()) {
            throw new UsersAlreadyExistsException(UserConstants.MESSAGE_ALREADY_EXISTS);
        }

        User user = User.builder()
                .name(checkUserVo.getName())
                .email(checkUserVo.getEmail())
                .password(checkUserVo.getPassword())
                .nickName(checkUserVo.getNickName())
                .build();

        user.setRole(ROLE.USER);
        userRepository.save(user);
    }
}
