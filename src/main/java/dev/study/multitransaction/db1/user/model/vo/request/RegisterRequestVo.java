package dev.study.multitransaction.db1.user.model.vo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Schema(
        name = "UsersDetailsRequest",
        description = "Schema to hold successful response Users Detail information"
)
@Getter
@Builder
@AllArgsConstructor
public class RegisterRequestVo {

    @Email
    @NotEmpty(message = "Email cannot be null or empty")
    @Schema(description = "Email is used for login", example = "rlawogur816@test.com")
    private String email;

    @NotEmpty(message = "Name cannot be null or empty")
    @Schema(description = "User's name", example = "김재혁")
    private String name;

    @NotEmpty(message = "Password cannot be null or empty")
    @Schema(description = "User's password", example = "test")
    private String password;


    @NotEmpty(message = "nickName cannot be null or empty")
    @Schema(description = "User's nickName", example = "요렌테")
    private String nickName;
}
