package com.zyc.core.model.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zyc
 */
@Getter
@Setter
@Builder
@ApiModel(description = "用户信息")
public class UserVo {

    @ApiModelProperty("用户姓名")
    private String userName;

    @ApiModelProperty("手机号码")
    private String phoneNumber;

}
