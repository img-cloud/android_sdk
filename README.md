# android_sdk

Java Client for using Image upload &amp; transformation APIs for img-cloud.

This client has dependency on Retrofit and can be used in Android

Img-cloud library which offers URL after uploading images.

<b>Configuration</b>
Each request for building a URL must have api_key which will get after signing up <a href="http://img-cloud.herokuapp.com/">Here</a> .

<b>Usage</b>

Here's an example to configure 

    ImgCloud.setApiKey(YOUR_API_KEY);
    ImgCloud imgUpload = new ImgCloud();
    imgUpload.upload("/path/to/photo.jpg", "folderName", "tag1, tag2");
    String uploadedUrl = imgUpload.uploadedUrl();
    String resizedUrl = imgUpload.getResizedUrl(200, 200);

    String resizedUrlFromExistingUrl = ImgCloud.resizedUrl(remoteUrl, 400, 400);

##  Compiling the source

To clean
    
    gradle clean

To create jar

    gradle jar

<B>License</B>
Under Liftoffllc license 
