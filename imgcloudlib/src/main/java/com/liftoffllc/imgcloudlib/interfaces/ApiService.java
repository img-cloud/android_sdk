package com.liftoffllc.imgcloudlib.interfaces;

import com.liftoffllc.imgcloudlib.models.ImgUploadResponse;

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
    public ImgUploadResponse getFeed(@Part("image") TypedFile img, @Part("apiKey")TypedString apiKey);



}
