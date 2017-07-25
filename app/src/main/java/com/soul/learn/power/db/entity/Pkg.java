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
    private Long id;
    private String name;
    private int versionCode;
    private String pkg;
    @Generated(hash = 1421751308)
    public Pkg(Long id, String name, int versionCode, String pkg) {
        this.id = id;
        this.name = name;
        this.versionCode = versionCode;
        this.pkg = pkg;
    }
    @Generated(hash = 1942651913)
    public Pkg() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getVersionCode() {
        return this.versionCode;
    }
    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }
    public String getPkg() {
        return this.pkg;
    }
    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

}
