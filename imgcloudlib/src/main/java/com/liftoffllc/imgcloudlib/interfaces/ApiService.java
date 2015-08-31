package com.liftoffllc.imgcloudlib.interfaces;

import java.util.Map;

import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedString;

/**
 * API service for retro fit
 */
public interface ApiService {




    @Multipart
    @POST("/upload")
    public Map uploadImage(@Part("image") TypedFile img, @Part("apiKey")TypedString apiKey,
                                     @Part("folder") TypedString folder,@Part("tags")TypedString tags );
}
