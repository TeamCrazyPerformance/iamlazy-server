package com.tcp.iamlazy.review.entity;


import com.tcp.iamlazy.review.validation.ReviewValidation;
import com.tcp.iamlazy.review.validation.ReviewValidationLimit;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@ReviewValidationLimit
public class Review implements ReviewValidation {
    @NotNull(message = "User number cannot be null.")
    @ApiModelProperty(value = "userId", name = "userIdx", dataType = "integer", example = "123", required = true)
    private int userIdx;
    @NotNull(message = "todoDate cannot be null.")
    @ApiModelProperty(value = "todoDate", name = "reviewDate", dataType = "string", example = "2020-09-08", required = true)
    private String toDoDate;
    @ApiModelProperty(value = "reviewContent", name = "reviewContent", dataType = "string", example = "고생했다.", required = true, allowEmptyValue = true)
    private String reviewContent;
    @ApiModelProperty(value = "emoticon", name = "emoticon", dataType = "integer", example = "1...", required = false)
    private int emoticon;

    @Override
    public boolean isValid() {
        return true;
    }
}
