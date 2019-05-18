package com.zyc.user.controller;

import com.zyc.core.model.base.vo.ResponseResult;
import com.zyc.core.model.user.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zyc
 */
@Slf4j
@RequestMapping("/user")
@RestController
@Api(tags = "用户")
public class UserController {

    @GetMapping("/getUser")
    @ApiOperation("查询用户信息")
    public ResponseResult<UserVo> getUser(@ApiParam(value = "用户姓名", required = true) @RequestParam String userName) {
        UserVo user = UserVo.builder()
                .userName(userName)
                .phoneNumber("123456789")
                .build();
        return ResponseResult.success(user);
    }
}
