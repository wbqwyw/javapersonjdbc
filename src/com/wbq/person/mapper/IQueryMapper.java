package com.wbq.person.mapper;

import java.sql.ResultSet;
import java.util.List;

/**
 * @author Administrator
 */
public interface IQueryMapper<T> {
    /**
     * �����ѯ���ص�����
     *
     * @param rs
     * @param <T>
     * @return
     */
    public <T> List<T> makeData(ResultSet rs);
}
