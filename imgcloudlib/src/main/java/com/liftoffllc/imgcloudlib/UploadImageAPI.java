package com.liftoffllc.imgcloudlib;

import com.liftoffllc.imgcloudlib.interfaces.ApiService;

import java.io.File;
import java.util.Map;

import retrofit.RestAdapter;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedString;

/**
 * Created by Nikita on 31/08/15.
 */
public class UploadImageAPI {

    private String apiKey;
    private static final String BASE_URL = "http://img-cloud.herokuapp.com";
    private String serviceUrl = "http://img-cloud.liftoffllc.in";
    private RestAdapter restAdapter = null;
    private ApiService imageService = null;

    public UploadImageAPI(String apiKey){
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(serviceUrl)
                .build();
        imageService = restAdapter.create(ApiService.class);
        this.apiKey = apiKey;
    }



    public Map upload(String image){

        TypedFile tFile = new TypedFile("image", new File(image));
        return imageService.uploadImage(tFile, new TypedString(apiKey), new TypedString(null), new TypedString(null));
    }


    public Map upload(String image,String folderName, String tags){

        TypedFile tFile = new TypedFile("image", new File(image));
        return imageService.uploadImage(tFile, new TypedString(apiKey), new TypedString(folderName), new TypedString(tags));
    }




}
