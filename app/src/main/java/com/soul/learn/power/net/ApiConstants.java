package com.soul.learn.power.net;


/**
 * Created by chenjianhua on 2017/5/22.
 */

public class ApiConstants {
    public static final String STORE_HOST = "";
    public static final String VEDIO_HOST = "";
    public static final String SDK_HOST = "";

    public static final String STORE_CODE = "dk_h_test";

    /**
     * 获取对应的host
     *
     * @param hostType host类型
     * @return host
     */
    public static String getHost(int hostType) {
        String host;
        switch (hostType) {
            case HostType.STORE_API:
                host = STORE_HOST;
                break;
            case HostType.VEDIO_API:
                host = VEDIO_HOST;
                break;
            case HostType.SDK_API:
                host = SDK_HOST;
                break;
            default:
                host = "";
                break;
        }
        return host;
    }

}
