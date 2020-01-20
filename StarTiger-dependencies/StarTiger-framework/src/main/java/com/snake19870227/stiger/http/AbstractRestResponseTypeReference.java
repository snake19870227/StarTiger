package com.snake19870227.stiger.http;

import com.snake19870227.stiger.http.RestResponse;
import org.springframework.core.ParameterizedTypeReference;

/**
 * @author Bu HuaYang
 */
public abstract class AbstractRestResponseTypeReference<T> extends ParameterizedTypeReference<RestResponse<T>> {
}
