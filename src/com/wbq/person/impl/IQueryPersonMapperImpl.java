package com.wbq.person.impl;

import com.wbq.person.entity.Person;
import com.wbq.person.mapper.IQueryMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName IQueryPersonMapperImpl
 * @Description 处理人员查询返回数据实现类
 * @Author Administrator
 * @Date 2020/12/11 17:42
 * @Version 1.0
 */
public class IQueryPersonMapperImpl implements IQueryMapper {
    @Override
    public List<Person> makeData(ResultSet rs) {
        List<Person> list = new ArrayList<>();
        try {
            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setCode(rs.getString("code"));
                person.setName(rs.getString("name"));
                person.setAccount(rs.getInt("account"));
                list.add(person);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
