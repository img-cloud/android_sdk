package com.liftoffllc.imgcloudlib;

import com.liftoffllc.imgcloudlib.interfaces.ApiService;

import java.io.File;
import java.util.Map;

import retrofit.RestAdapter;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedString;

/**
 * To set API Key
 * <code>
 * ImgCloud.setApiKey("YOUR API KEY");
 * </code>
 *
 * <p>
 * Above needs to be done once in entire life cycle of your app.
 * Unless you want to intentionally want to change the API Key.
 * </p> 
 * 
 * <code>
 * ImgCloud uploadImg = new ImgCloud();
 * uploadImg.upload("path/to/your/file", "android1", "android1");
 * </code>
 * To get URL of uploaded image
 * <code>
 * uploadImg.uploadedUrl();
 * </code>
 * 
 * Created by Nikita on 31/08/15.
 */

public class ImgCloud {

	private static String apiKey;
	private String serviceUrl = "http://img-cloud.liftoffllc.in";
	private ApiService imageService = null;
	private RestAdapter restAdapter = null;
	private Map uploadMap = null;

	public ImgCloud() {
		getService();
	}

	public ImgCloud(String serviceUrl) {
		this.serviceUrl = serviceUrl;
		getService();
	}

	/**
	 * Use the overloaded method which returns ImgCloud object
	 * @param image
	 * @return
	 */
	@Deprecated
	public Map upload(String image) {
		TypedFile tFile = new TypedFile("image", new File(image));
		return imageService.uploadImage(tFile, new TypedString(getApiKey()),
				new TypedString(null), new TypedString(null));
	}
	
	/**
	 * Usage
	 * 
	 * @param image absolute path of the file
	 * @param folderName the folder location where image needs to be stored. If this is null, 
	 * it should default.  
	 * @param tags comma separated string of tags
	 * @return
	 * imgCloud object on successful upload
	 */
	public ImgCloud upload(String image, String folderName, String tags) {
		TypedFile tFile = new TypedFile("image", new File(image));
		this.uploadMap  = imageService.uploadImage(tFile, new TypedString(getApiKey()),
				new TypedString(folderName), new TypedString(tags));
		return this;
	}
	
	/**
	 * 
	 * @return
	 * remote URL of the file. Should be invoked only if image is already uploaded.
	 */
	public String uploadedUrl() {
		return (String) this.uploadMap.get("url");
	}
	
	/**
	 * Example Usage
	 * <code>
	 * ImgCloud.resizedUrl(currentPost.getMediaUrl(), 400, 400);
	 * </code>
	 * @param width
	 * @param height
	 * @return
	 * remote URL of given dimension
	 * 
	 */
	public String getResizedUrl(int width, int height) {
		return Util.getTransformedUrl((String)this.uploadMap.get("url"), width, height);
	}
	
	public static String resizedUrl(String url, int w, int h) {
		return Util.getTransformedUrl(url, w, h);
	}

	public static String getApiKey() {
		return apiKey;
	}

	public static void setApiKey(String apiKey) {
		ImgCloud.apiKey = apiKey;
	}
	
	private ApiService getService() {
		restAdapter = new RestAdapter.Builder().setEndpoint(serviceUrl).build();
		imageService = restAdapter.create(ApiService.class);
		return imageService;
	}
}
