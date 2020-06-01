package com.bairwa.blogre;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class BloggerApi {
    public static final String API_KEY="AIzaSyDhz3u1aVZ9DMEyBrBkKG_XDwouk63Cv38";
    public static final String BASE_URL="https://www.googleapis.com/blogger/v3/blogs/8341769376324501064/posts/";
    public static Postservice postservice=null;
     public static Postservice getService(){
         if (postservice==null){
             //it will create only one object i.e. Singleton object
             Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL+"/")
                     .addConverterFactory(GsonConverterFactory.create()).build();
             postservice=retrofit.create(Postservice.class);
         }
         return postservice;
     }
    public interface Postservice{
        @GET("?key="+API_KEY)
        Call<PostList>getPost();
    }
}
