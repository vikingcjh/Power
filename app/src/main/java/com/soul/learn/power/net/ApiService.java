package com.soul.learn.power.net;



import com.soul.learn.power.bean.AppDetailsModel;
import com.soul.learn.power.bean.BaseResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by chenjianhua on 2017/5/22.
 */

public interface ApiService {

    @GET("api/apps")
    @Headers({Params.HEADER_ACCEPT_JSON,
            Params.HEADER_ENCODE_GZIP} )
    Observable<BaseResult<List<AppDetailsModel>>> getAppList(
            @Query(Params.STORE_CODE) String code,
            @Query(Params.APP_SIZE) int size,
            @Query(Params.APP_PAGE) int page);




}
