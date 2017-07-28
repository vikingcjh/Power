package com.soul.learn.power.bean;

/**
 * Created by chenjianhua on 2017/7/14.
 */

public class SdkUpdateBean {


    /**
     * entity : {"isUpdate":1,"versionCode":112,"versionName":"11","apkUrl":"http://g3.letv.cn/262/49/89/letv-hdtv/0/upload/tmp/20170707180920794/LeInitPluginSdk.apk?b=123456&platid=5&splatid=500","md5":"e01b9401c8dfa5a0c50f5edcf82694eb"}
     * httpStatusCode : 200
     * code : OK
     * msg : SUCESS
     * serverTime : 1500019910281
     * status : 1
     */

    private EntityBean entity;
    private int httpStatusCode;
    private String code;
    private String msg;
    private long serverTime;
    private int status;

    public EntityBean getEntity() {
        return entity;
    }

    public void setEntity(EntityBean entity) {
        this.entity = entity;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getServerTime() {
        return serverTime;
    }

    public void setServerTime(long serverTime) {
        this.serverTime = serverTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class EntityBean {
        /**
         * isUpdate : 1
         * versionCode : 112
         * versionName : 11
         * apkUrl : http://g3.letv.cn/262/49/89/letv-hdtv/0/upload/tmp/20170707180920794/LeInitPluginSdk.apk?b=123456&platid=5&splatid=500
         * md5 : e01b9401c8dfa5a0c50f5edcf82694eb
         */

        private int isUpdate;
        private int versionCode;
        private String versionName;
        private String apkUrl;
        private String md5;

        public int getIsUpdate() {
            return isUpdate;
        }

        public void setIsUpdate(int isUpdate) {
            this.isUpdate = isUpdate;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getApkUrl() {
            return apkUrl;
        }

        public void setApkUrl(String apkUrl) {
            this.apkUrl = apkUrl;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }
    }
}
