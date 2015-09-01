package com.liftoffllc.imgcloudlib;

import android.util.Log;

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



    private static String apiKey;

    private String serviceUrl = "http://img-cloud.liftoffllc.in";

    private ApiService imageService = null;
    private RestAdapter restAdapter = null;
    public ImgCloud(){
        getService();

    }

    public ImgCloud(String serviceUrl){


        this.serviceUrl = serviceUrl;
        getService();
    }

    public Map upload(String image){

        TypedFile tFile = new TypedFile("image", new File(image));
        return imageService.uploadImage(tFile, new TypedString(getApiKey()), new TypedString(null), new TypedString(null));
    }


    public Map upload(String image,String folderName, String tags){

        TypedFile tFile = new TypedFile("image", new File(image));
        return imageService.uploadImage(tFile, new TypedString(getApiKey()), new TypedString(folderName), new TypedString(tags));
    }


    private ApiService getService() {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(serviceUrl)
                .build();
        imageService = restAdapter.create(ApiService.class);
        return imageService;
    }

    public static String getResizedImg(String url,int w,int h){

        String[] splited = url.split("/");
        String resizedUrl = url.substring(0, url.lastIndexOf("/")) + "/h_" + h + "," + w + "/" + splited[splited.length - 1];
        Log.e(" ", resizedUrl);
        return resizedUrl;
        }


    public static String getApiKey() {
        return apiKey;
    }

    public static void setApiKey(String apiKey) {
        ImgCloud.apiKey = apiKey;
    }
}
