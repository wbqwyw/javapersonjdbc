package com.wbq.person.entity;

/**
 * @ClassName Person
 * @Description 人员实体类
 * @Author Administrator
 * @Date 2020/12/11 17:22
 * @Version 1.0
 */
public class Person {
    private int id;
    private String code;
    private String name;
    private int account;

    public Person() {
    }

    public Person(String code, String name, int account) {
        this.code = code;
        this.name = name;
        this.account = account;
    }

    public Person(int id, String code, String name, int account) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", account=" + account +
                '}';
    }
}
