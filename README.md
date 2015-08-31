# android_sdk
Android Client for using Image upload &amp; transformation APIs for img-cloud

Img-cloud library which offers url after uploading images.

<b>Configuration</b>
Each request for building a URL must have api_key which will get after signing up <a href="http://img-cloud.herokuapp.com/">Here</a> .

<b>Usage</b>

Here's an example to configure 

            UploadImageAPI imageAPI = = new UploadImageAPI(yourApiKey);
            try {
                    //call api to upload the image
                    Map imgResponse = imageAPI.upload(imagePath, folderName,tags);
                    return imgResponse;
                } catch (RetrofitError error) {
                       //handle the error
                   return imgResponse;
                }
        
<B>License</B>
Under Liftoffllc license 
