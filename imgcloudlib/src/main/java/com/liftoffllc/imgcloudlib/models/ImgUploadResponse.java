package com.liftoffllc.imgcloudlib.models;

/**
 * Created by Nikita on 24/08/15.
 */
public class ImgUploadResponse {

    private String url;
    private String folder;

    public String getUrl() {
        return url;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}