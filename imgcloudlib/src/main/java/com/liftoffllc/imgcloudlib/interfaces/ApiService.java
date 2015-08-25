package com.liftoffllc.imgcloudlib.interfaces;

import com.liftoffllc.imgcloudlib.models.ImgUploadResponse;

import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedString;

/**
 * Created by Nikita on 23/08/15.
 */
public interface ApiService {

    @Multipart
    @POST("/upload")
    public ImgUploadResponse getFeed(@Part("image") TypedFile img, @Part("apiKey")TypedString apiKey);



}
