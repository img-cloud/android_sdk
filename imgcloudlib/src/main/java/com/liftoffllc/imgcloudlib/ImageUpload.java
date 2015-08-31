package com.liftoffllc.imgcloudlib;

import android.os.AsyncTask;
import android.util.Log;

import com.liftoffllc.imgcloudlib.Error.ImageUploadError;
import com.liftoffllc.imgcloudlib.interfaces.ApiService;
import com.liftoffllc.imgcloudlib.interfaces.onImageUploaded;
import com.liftoffllc.imgcloudlib.models.ImgUploadResponse;
import com.liftoffllc.imgcloudlib.models.UploadImg;
import com.liftoffllc.imgcloudlib.services.RestService;

import java.io.File;
import java.util.concurrent.ExecutionException;

import retrofit.RetrofitError;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedString;

/**
 * Created by Nikita on 23/08/15.
 */
public class ImageUpload {


    String folder,tags;
    String path;
    File nPath;
    String apikey;
    ApiService service;
    ImgUploadResponse uploadResponse;
    onImageUploaded taskComplete ;
    UploadImg img, img1;

    public ImageUpload(UploadImg img, onImageUploaded task){

        this.img = img;
        this.taskComplete = task;

    }

    public void execute() {



            new AsyncTask<ImgUploadResponse, Integer, ImgUploadResponse>() {
                ImgUploadResponse feed;
                ImageUploadError err;

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                }

                @Override
                protected ImgUploadResponse doInBackground(ImgUploadResponse... longs) {
                    try {


                        service = RestService.getService();
                        feed = service.getFeed(new TypedFile("image",new File(img.getImage())),new TypedString(img.getApiKey())
                        , new TypedString(img.getFolder()),new TypedString(img.getTags()));
                        return feed;
                    } catch (RetrofitError error) {


                        Log.e("err", error.getMessage());
                        error.printStackTrace();
                        feed = new ImgUploadResponse();
                        if(error.getResponse().equals(null)) {
                            feed.setErrorMsg(error.getMessage().toString());
                        }else{
                            String json =  new String(((TypedByteArray)error.getResponse().getBody()).getBytes());
                            feed.setErrorMsg(json);
                        }
                        return feed;
                    }

                }

                @Override
                protected void onPostExecute(ImgUploadResponse imgUploadResponse) {
                    super.onPostExecute(imgUploadResponse);

                    taskComplete.onUploadCompleted(imgUploadResponse);

                }
            }.execute();


    }

}
