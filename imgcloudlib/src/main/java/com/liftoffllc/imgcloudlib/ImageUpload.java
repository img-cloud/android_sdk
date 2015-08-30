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
                        try {
                            error.printStackTrace();
                            err = new ImageUploadError();

                            err.setMessage(error.getMessage().toString());
                        } catch(Exception er){
                            err = new ImageUploadError();

                            err.setMessage("invalid api key");
                            }
                        return null;
                    } catch (ImageUploadError ex) {
                        ex.printStackTrace();
                        err = ex;
                        return null;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        err = new ImageUploadError();

                        err.setMessage("Value can not be null");
                        return null;
                    }

                }

                @Override
                protected void onPostExecute(ImgUploadResponse imgUploadResponse) {
                    super.onPostExecute(imgUploadResponse);

                    taskComplete.onUploadCompleted(imgUploadResponse == null ? err : imgUploadResponse);

                }
            }.execute();


    }

}
