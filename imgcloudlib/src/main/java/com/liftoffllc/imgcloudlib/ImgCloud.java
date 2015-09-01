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
public class ImgCloud {

    private String apiKey;

    private String serviceUrl = "http://img-cloud.liftoffllc.in";

    private ApiService imageService = null;
    private RestAdapter restAdapter = null;
    public ImgCloud(String apiKey){
        getService();
        this.apiKey = apiKey;
    }

    public ImgCloud(String apiKey, String serviceUrl){
        getService();
        this.apiKey = apiKey;
        this.serviceUrl = serviceUrl;
    }

    public Map upload(String image){

        TypedFile tFile = new TypedFile("image", new File(image));
        return imageService.uploadImage(tFile, new TypedString(apiKey), new TypedString(null), new TypedString(null));
    }


    public Map upload(String image,String folderName, String tags){

        TypedFile tFile = new TypedFile("image", new File(image));
        return imageService.uploadImage(tFile, new TypedString(apiKey), new TypedString(folderName), new TypedString(tags));
    }


    private ApiService getService() {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(serviceUrl)
                .build();
        return restAdapter.create(ApiService.class);
    }

}
