package com.soul.learn.power.bean;

/**
 * Created by chenjianhua on 2017/5/22.
 */

public class BaseResult<T> {
    public int status;
    public String message;
    public T entity;
}
