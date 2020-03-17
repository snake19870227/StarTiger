package com.snake19870227.stiger.admin.entity.bo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;

/**
 * @author Bu HuaYang (buhuayang1987@foxmail.com)
 * @date 2020/03/17
 */
public class RecordPage<T> extends ArrayList<T> implements IPage<T> {

    private long page;
    private long pageSize;
    private long total;
    private long totalPage;

    private List<OrderItem> orders = new ArrayList<>();

    public RecordPage(long page, long pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public boolean hasPrevious() {
        return this.page > 1;
    }

    public boolean hasNext() {
        return this.page < this.getPages();
    }

    public <M extends RecordPage<T>> M addOrder(OrderItem... items) {
        orders.addAll(Arrays.asList(items));
        return (M) this;
    }

    public <M extends RecordPage<T>> M addOrder(List<OrderItem> items) {
        orders.addAll(items);
        return (M) this;
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
        return this;
    }

    @Override
    public long getSize() {
        return pageSize;
    }

    @Override
    public IPage<T> setSize(long size) {
        this.pageSize = size;
        return this;
    }

    @Override
    public long getCurrent() {
        return page;
    }

    @Override
    public IPage<T> setCurrent(long current) {
        this.page = current;
        return this;
    }
}
