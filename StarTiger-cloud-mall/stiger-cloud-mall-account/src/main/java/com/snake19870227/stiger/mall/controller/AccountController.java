package com.snake19870227.stiger.mall.controller;

import com.snake19870227.stiger.http.RestResponseBuilder;
import com.snake19870227.stiger.mall.entity.dto.AccountDetailRestResponse;
import com.snake19870227.stiger.mall.entity.po.MallAccount;
import com.snake19870227.stiger.mall.service.AccountService;
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
@RestController
@RequestMapping(path = "/account")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
        logger.debug("创建 {}", this.getClass().getName());
    }

    @GetMapping(path = "/detail")
    public AccountDetailRestResponse detail(@RequestParam(name = "accountId") String accountId) {
        Optional<MallAccount> account = Optional.ofNullable(accountService.loadAccount(accountId));
        return account.map(mallAccount -> RestResponseBuilder.createSuccessRestResp(mallAccount, AccountDetailRestResponse.class))
                .orElseGet(() -> RestResponseBuilder.createRestResp("5001", null, AccountDetailRestResponse.class));
    }
}
