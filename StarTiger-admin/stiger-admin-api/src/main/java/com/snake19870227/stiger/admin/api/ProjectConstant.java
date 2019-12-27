package com.snake19870227.stiger.admin.api;

/**
 * @author Bu HuaYang
 */
public class ProjectConstant {

    public static class RestResp {

        public static class Success {
            public static final String CODE = "0000";
            public static final String MESSAGE = "成功";
        }

        public static class Failure {
            public static final String CODE = "9999";
            public static final String MESSAGE = "失败";
        }

        public static class Code1001 {
            public static final String CODE = "1001";
            public static final String MESSAGE = "登录失败";
        }

        public static class Code2001 {
            public static final String CODE = "2001";
            public static final String MESSAGE = "接口用户认证失败";
        }

    }
}
