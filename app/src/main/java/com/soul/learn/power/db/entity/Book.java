package com.soul.learn.power.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by chenjianhua on 2017/7/24.
 */
@Entity
public class Book {
    @Id(autoincrement = true)
    private Long Id;

    private String name;

    private long page;

    @Generated(hash = 1885730687)
    public Book(Long Id, String name, long page) {
        this.Id = Id;
        this.name = name;
        this.page = page;
    }

    @Generated(hash = 1839243756)
    public Book() {
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPage() {
        return this.page;
    }

    public void setPage(long page) {
        this.page = page;
    }

}
