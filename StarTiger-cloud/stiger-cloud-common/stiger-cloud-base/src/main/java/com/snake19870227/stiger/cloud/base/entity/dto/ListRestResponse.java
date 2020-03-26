package com.snake19870227.stiger.cloud.base.entity.dto;

import com.snake19870227.stiger.web.restful.RestResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bu HuaYang
 */
public class ListRestResponse extends RestResponse<List<Object>> {

    public void addData(Object obj) {
        if (getData() == null) {
            setData(new ArrayList<>());
        }
        getData().add(obj);
    }
}
