package com.liftoffllc.imgcloudlib;

import android.os.AsyncTask;

import com.liftoffllc.imgcloudlib.Error.ImageUploadError;
import com.liftoffllc.imgcloudlib.interfaces.ApiService;
import com.liftoffllc.imgcloudlib.interfaces.onImageUploaded;
import com.liftoffllc.imgcloudlib.models.ImgUploadResponse;
import com.liftoffllc.imgcloudlib.services.RestService;

import java.io.File;
import java.util.concurrent.ExecutionException;

import retrofit.RetrofitError;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedString;

/**
 * Created by Nikita on 23/08/15.
 */
public class ImageUpload {


    String path;
    File nPath;
    String apikey;
    ApiService service;
    ImgUploadResponse uploadResponse;
    onImageUploaded taskComplete ;

    public ImageUpload(String path, String apikey, onImageUploaded task){

        this.apikey = apikey;
        this.path = path;
        this.taskComplete = task;


    }

    public void execute() {


        try {
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
                        nPath = new File(path);

                        service = RestService.getService();
                        TypedFile tf = new TypedFile("image", nPath);
                        TypedString ts = new TypedString(apikey);
                        feed = service.getFeed(tf, ts);
                        return feed;
                    } catch (RetrofitError error) {
                        error.printStackTrace();
                        err = new ImageUploadError();
                        err.setMessage(error.getMessage().toString());
                        return null;
                    } catch (ImageUploadError ex) {
                        ex.printStackTrace();
                        err = ex;
                        return null;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        err = new ImageUploadError();
                        err.setMessage(ex.getMessage().toString());
                        return null;
                    }

                }

                @Override
                protected void onPostExecute(ImgUploadResponse imgUploadResponse) {
                    super.onPostExecute(imgUploadResponse);

                    taskComplete.onUploadCompleted(imgUploadResponse == null ? err : imgUploadResponse);

                }
            }.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
