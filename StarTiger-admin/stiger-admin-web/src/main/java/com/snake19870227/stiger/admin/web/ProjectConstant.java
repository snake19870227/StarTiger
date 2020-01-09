package com.snake19870227.stiger.admin.web;

/**
 * @author Bu HuaYang
 */
public class ProjectConstant {

    public static class UrlParamNames {
        public static final String LOGIN_ERROR = "error";
        public static final String LOGIN_EXPIRE = "expire";
    }

    public static class ViewAttrNames {
        public static final String LOGIN_ERROR_MESSAGE = "errorMessage";
    }

    public static class UrlPath {
        public static final String ROOT = "/";
        public static final String INDEX = "/index";
        public static final String LOGIN = "/login";

        public static String[] anonymousPaths() {
            return new String[] {
                    ROOT, INDEX, LOGIN
            };
        }
    }
}
