package dev.study.multitransaction.db1.user.controller;

import dev.study.multitransaction.db1.user.constants.UserConstants;
import dev.study.multitransaction.db1.user.model.dto.GetMyBoardDto;
import dev.study.multitransaction.db1.user.model.vo.request.RegisterRequestVo;
import dev.study.multitransaction.db1.user.model.vo.response.UserStatusResponseVo;
import dev.study.multitransaction.db1.user.service.UserService;
import dev.study.multitransaction.util.error.ErrorResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Create User REST API",
            description = "REST API to create new User"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping(path = "/registration")
    public ResponseEntity<UserStatusResponseVo> createUsers(@Valid @RequestBody RegisterRequestVo requestVo) {
        userService.saveUser(requestVo);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new UserStatusResponseVo(UserConstants.STATUS_201, UserConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Get User Board",
            description = "REST API to Get User Board"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/my_board")
    public ResponseEntity<List<GetMyBoardDto>> getMyBoard(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getMyBoard());
    }
}
