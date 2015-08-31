# android_sdk
Android Client for using Image upload &amp; transformation APIs for img-cloud

Img-cloud library which offers url after uploading images.

<b>Configuration</b>
Each request for building a URL must have api_key which will get after signing up <a href="http://img-cloud.herokuapp.com/">Here</a> .

<b>Usage</b>

Here's an example to configure 

            onImageUploaded imgUploadResponse;
            UploadImg image;
            
            image = new UploadImg();
            image.setImage(imagePath);
            image.setApiKey(apiKey);
            image.setFolder(folderName);
            image.setTags(tag);
            new ImageUpload(image,imgUploadResponse).execute();
            imgUploadResponse = = new onImageUploaded() {
            @Override
            public void onUploadCompleted(Object result) {
                        
                        //handle response
               
            }
        };
        
<B>License</B>
Under Liftoffllc license 
