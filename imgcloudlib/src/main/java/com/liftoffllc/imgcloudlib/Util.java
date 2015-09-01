package com.liftoffllc.imgcloudlib;

public class Util {
	/**
	 * 
	 * @param url
	 * @param width
	 * @param height
	 * @return
	 */
	public static String getTransformedUrl(String url, int width, int height) {
		String[] splited = url.split("/");
		String resizedUrl = url.substring(0, url.lastIndexOf("/")) + "/h_" + height
				+ "," + width + "/" + splited[splited.length - 1];
		return resizedUrl;
	}
}
