package com.snake19870227.stiger.admin.entity.bo;

import cn.hutool.core.util.PageUtil;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * @date 2020/03/17
 */
public class RecordPage<T> extends ArrayList<T> implements IPage<T> {

    private long current;
    private long size;
    private long total;
    private long totalPage;

    private int[] rainbow;

    private List<OrderItem> orders = new ArrayList<>();

    public RecordPage(long current, long size) {
        this.current = current;
        this.size = size;
    }

    @Override
    public List<OrderItem> orders() {
        return orders;
    }

    @Override
    public List<T> getRecords() {
        return this;
    }

    @Override
    public IPage<T> setRecords(List<T> records) {
        this.addAll(records);
        return this;
    }

    @Override
    public long getTotal() {
        return total;
    }

    @Override
    public IPage<T> setTotal(long total) {
        this.total = total;
        this.totalPage = this.getPages();
        this.rainbow = PageUtil.rainbow(Long.valueOf(current).intValue(), Long.valueOf(totalPage).intValue(), 11);
        return this;
    }

    @Override
    public long getSize() {
        return size;
    }

    @Override
    public IPage<T> setSize(long size) {
        this.size = size;
        return this;
    }

    @Override
    public long getCurrent() {
        return current;
    }

    @Override
    public IPage<T> setCurrent(long current) {
        this.current = current;
        return this;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public int[] getRainbow() {
        return rainbow;
    }
}
