package com.bairwa.blogre;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AdapterBlog extends RecyclerView.Adapter<AdapterBlog.AdapterViewHolder> {
    private Context mcontext;
    private List<Item> itemList;

    public AdapterBlog(Context mcontext, List<Item> itemList) {
        this.mcontext = mcontext;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mcontext);
        View view=inflater.inflate(R.layout.post_layout,parent,false);

        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        final Item item=itemList.get(position); //it gives the position of every item
        holder.postTitle.setText(item.getTitle());


        /*
        main method to get images and remove html tags
        Jsoup is a library that is used to parsing th html content
        */


        Document document= Jsoup.parse(item.getContent());
        Elements elements=document.select("img");
Elements elements1=document.select("div");
        Glide.with(mcontext).load(elements.get(0).attr("src")).into(holder.postImage);

//            System.out.println(p.text());
        document.outputSettings().prettyPrint(false);
            holder.postDescription.setText(document.text());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mcontext,BlogDetails.class);
                    intent.putExtra("blogurl",item.getUrl());
                    mcontext.startActivity(intent);
                }
            });

//        Elements links=document.select("h3");
//        holder.postDescription.setText(links.text());
//        for (Element link:links) {
//            holder.postDescription.setText(link.text());
//         //   Log.d(TAG, "onBindViewHolder: "+link.attr("href")+"\n"+link.text());
//
//        }


//        Pattern p=Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
//        Matcher m=p.matcher(item.getContent()); //its a hack to retrieve blog image
//        List<String>tokens=new ArrayList<>();
//        while (m.find()){
//            String token=m.group(1);
//tokens.add(token);
//        }


    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder{
ImageView postImage;
TextView postTitle,postDescription;
        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            postImage=itemView.findViewById(R.id.postImage);
            postTitle=itemView.findViewById(R.id.postTitle);
            postDescription=itemView.findViewById(R.id.postdescription);


        }
    }



}
