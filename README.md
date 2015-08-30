# android_sdk
Android Client for using Image upload &amp; transformation APIs for img-cloud

Img-cloud library which offers url after uploading images.

<b>Configuration</b>
Each request for building a URL must have api_key which will get after signing up <a href="http://img-cloud.herokuapp.com/">Here</a> .

<b>Usage</b>

Here's an example to configure 

            onImageUploaded imgUploadResponse;

            new ImageUpload(imagPath,your_apiKey,imgUploadResponse).execute();
            imgUploadResponse = = new onImageUploaded() {
            @Override
            public void onUploadCompleted(Object result) {

                if(result instanceof ImgUploadResponse) {

                    //handle response
                    response = (ImgUploadResponse) result;

                }else{
                   //handle error
                   ImageUploadError error = (ImageUploadError) result;
                }
            }
        };
        
<B>License</B>
Under Liftoffllc license 
