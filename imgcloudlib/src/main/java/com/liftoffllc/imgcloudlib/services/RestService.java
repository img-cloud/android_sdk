package com.liftoffllc.imgcloudlib.services;


import com.liftoffllc.imgcloudlib.interfaces.ApiService;

import retrofit.RestAdapter;

/**
 * Class to call rest service
 */
public class RestService {

    public static final String BASE_URL = "http://img-cloud.herokuapp.com";


    private RestService(){}

    public static ApiService getService(){
        RestAdapter restAdapter = null;

            restAdapter = new RestAdapter.Builder()
                    .setEndpoint(BASE_URL)
                    .build();



        return restAdapter.create(ApiService.class);
    }


}
