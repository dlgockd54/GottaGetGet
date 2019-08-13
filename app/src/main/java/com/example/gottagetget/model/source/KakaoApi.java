package com.example.gottagetget.model.source;

import com.example.gottagetget.model.SearchResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hclee on 2019-08-13.
 */

public interface KakaoApi {
    @GET("v2/search/image")
    Single<SearchResponse> getSearchedImageListSingle(
        @Query("query") String query,
        @Query("page") int page,
        @Query("size") int size
    );
}