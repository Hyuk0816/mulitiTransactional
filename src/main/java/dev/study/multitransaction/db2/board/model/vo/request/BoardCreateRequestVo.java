package dev.study.multitransaction.db2.board.model.vo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Schema(
        name = "Board Create Request",
        description = "Schema to hold successful response Board Create"
)
@Getter
@Builder
@AllArgsConstructor
public class BoardCreateRequestVo {

    @NotEmpty(message = "Title cannot be null or empty")
    @Schema(description = "Board Title", example = "테스트지롱")
    private String title;

    @Schema(description = "Board Content", example = "멀티 DB 트랜잭션 테스트")
    private String content;
}
