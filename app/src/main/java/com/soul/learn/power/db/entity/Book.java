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
    private long Id;

    private String name;

    private long page;

    @Generated(hash = 1151580970)
    public Book(long Id, String name, long page) {
        this.Id = Id;
        this.name = name;
        this.page = page;
    }

    @Generated(hash = 1839243756)
    public Book() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }
}
