package dev.study.multitransaction.db1.user.userDetails;


import dev.study.multitransaction.db1.user.constants.UserConstants;
import dev.study.multitransaction.db1.user.model.entity.User;
import dev.study.multitransaction.db1.user.repository.UserRepository;
import dev.study.multitransaction.util.exception.UserNameNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Slf4j
public class CustomUserDetails implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("loadUserByUsername member ID : " + email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNameNotFoundException(UserConstants.STATUS_404, UserConstants.MESSAGE_404));
        return new UserDetailsImpl(user);
    }
}
