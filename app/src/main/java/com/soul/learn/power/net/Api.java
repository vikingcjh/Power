package com.soul.learn.power.net;

import android.content.Context;
import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.soul.learn.power.PowerApplication;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chenjianhua on 2017/5/22.
 */

public class Api {
    //读超时长，单位：毫秒
    public static final int READ_TIME_OUT = 8000;
    //连接时长，单位：毫秒
    public static final int CONNECT_TIME_OUT = 8000;

    public OkHttpClient okHttpClient;
    public Retrofit retrofit;
    public ApiService apiService;
    private static SparseArray<Api> sRetrofitManager = new SparseArray<>();

    /*************************缓存设置*********************/
/*
    1. noCache 不使用缓存，全部走网络
    2. noStore 不使用缓存，也不存储缓存
    3. onlyIfCached 只使用缓存
    4. maxAge 设置最大失效时间，失效则不使用 需要服务器配合
    5. maxStale 设置最大失效时间，失效则不使用 需要服务器配合
    6. minFresh 设置有效时间
    7. FORCE_NETWORK 只走网络
    8. FORCE_CACHE 只走缓存*/

    /**
     * 设缓存有效期为两天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    /**
     * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
     * max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
     */
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    /**
     * 查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
     * (假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
     */
    private static final String CACHE_CONTROL_AGE = "max-age=0";

    //构造方法私有
    private Api(int hostType) {
        //开启Log
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //缓存
        File cacheFile = new File(PowerApplication.getGlobalContext().getCacheDir(), "okCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 20);
        //增加头部信息
        Interceptor headerInterceptor =new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request build = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .build();
                return chain.proceed(build);
            }
        };

        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(headerInterceptor)
                .addInterceptor(logInterceptor)
                .cache(cache)
                .sslSocketFactory(getTrustAllSslFactory(), (X509TrustManager) trustAllCers[0])
                .hostnameVerifier(hostnameVerifier)
                .retryOnConnectionFailure(true)
                .build();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiConstants.getHost(hostType))
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    /**
     * @param hostType
     */
    public static ApiService getDefault(int hostType) {
        Api retrofitManager = sRetrofitManager.get(hostType);
        if (retrofitManager == null) {
            retrofitManager = new Api(hostType);
            sRetrofitManager.put(hostType, retrofitManager);
        }
        return retrofitManager.apiService;
    }

    private SSLSocketFactory getTrustAllSslFactory() {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null,trustAllCers,new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private TrustManager[] trustAllCers = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }
    };

    private HostnameVerifier hostnameVerifier = new HostnameVerifier() {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    public X509TrustManager getTrustManager() {
        try
        {
            List<InputStream> certificates = new ArrayList<>();
            certificates.add(PowerApplication.getGlobalContext().getAssets().open("certfile.cer"));

            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates)
            {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));

                try
                {
                    if (certificate != null)
                        certificate.close();
                } catch (IOException e){
                }
            }


            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            return null;
//            SSLContext sslContext = SSLContext.getInstance("TLS");
//            sslContext.init(null,trustManagerFactory.getTrustManagers(),new SecureRandom());
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }

    public void getsslContextFactory(Context context) {
        try
        {
            List<InputStream> certificates = new ArrayList<>();
            certificates.add(context.getAssets().open("certfile.cer"));

            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates)
            {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));

                try
                {
                    if (certificate != null)
                        certificate.close();
                } catch (IOException e){
                }
            }

            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(null,trustManagerFactory.getTrustManagers(),new SecureRandom());
//            okHttpClient.setSslSocketFactory(sslContext.getSocketFactory());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
