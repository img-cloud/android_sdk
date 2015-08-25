package com.liftoffllc.imgcloudlib.interfaces;

import com.liftoffllc.imgcloudlib.models.ImgUploadResponse;

/**
 * Created by Nikita on 24/08/15.
 */
public interface onImageUploaded {
    public void onUploadCompleted(ImgUploadResponse result);
}
