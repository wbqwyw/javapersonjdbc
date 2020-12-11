package com.wbq.person.mapper;

import java.sql.ResultSet;
import java.util.List;

/**
 * @author Administrator
 */
public interface IQueryMapper<T> {
    /**
     * 处理查询返回的数据
     *
     * @param rs
     * @param <T>
     * @return
     */
    public <T> List<T> makeData(ResultSet rs);
}
