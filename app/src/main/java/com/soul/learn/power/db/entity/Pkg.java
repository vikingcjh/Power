package com.soul.learn.power.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by chenjianhua on 2017/7/24.
 */
@Entity
public class Pkg {
    @Id(autoincrement = true)
    private long id;
    private String name;
    private int versionCode;
    private String pkg;

    @Generated(hash = 1308218048)
    public Pkg(long id, String name, int versionCode, String pkg) {
        this.id = id;
        this.name = name;
        this.versionCode = versionCode;
        this.pkg = pkg;
    }

    @Generated(hash = 1942651913)
    public Pkg() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }


}
