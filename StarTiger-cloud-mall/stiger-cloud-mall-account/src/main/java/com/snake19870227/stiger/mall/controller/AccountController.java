package com.snake19870227.stiger.mall.controller;

import com.snake19870227.stiger.web.restful.RestResponseBuilder;
import com.snake19870227.stiger.mall.entity.dto.AccountDetailRestResponse;
import com.snake19870227.stiger.mall.entity.po.MallAccount;
import com.snake19870227.stiger.mall.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Bu HuaYang
 */
@Api(tags = "账户管理")
@RestController
@RequestMapping(path = "/account")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
        logger.debug("创建 {}", this.getClass().getName());
    }

    @ApiOperation(value = "获取账户详细信息", notes = "根据参数'accountId'获取账户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization",
                    value = "授权token", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "accountId",
                    value = "用户ID", required = true, example = "00000000000000000000000000000000")
    })
    @GetMapping(path = "/detail")
    public AccountDetailRestResponse detail(@RequestParam(name = "accountId") String accountId) {
        Optional<MallAccount> account = Optional.ofNullable(accountService.loadAccount(accountId));
        return account.map(mallAccount -> RestResponseBuilder.createSuccessRestResp(mallAccount, AccountDetailRestResponse.class))
                .orElseGet(() -> RestResponseBuilder.createRestResp("5001", null, AccountDetailRestResponse.class));
    }
}
