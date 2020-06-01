package com.bairwa.blogre;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerviewBlog);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getData();

    }

    private void getData() {
        final Call<PostList> postListCall=BloggerApi.getService().getPost();
        postListCall.enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
PostList list=response.body();
recyclerView.setAdapter(new AdapterBlog(MainActivity.this,list.getItems()));
               Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
String s=list.getEtag();
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }



}
