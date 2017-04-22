package com.example.nguye.rssexpress;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by nguye on 19/04/2017.
 */

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder> {
    ArrayList<Feeditem> feeditems;
    Context context;

    public Myadapter(Context context, ArrayList<Feeditem> feeditems) {
        this.feeditems = feeditems;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_row,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        YoYo.with(Techniques.FadeIn).playOn(holder.cardView);
        final Feeditem current=feeditems.get(position);
        holder.Title.setText(current.getTitle());
        holder.date.setText(current.getPubDate());
        holder.Description.setText(current.getDescription());
        Picasso.with(context).load(current.getGuid()).into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Newdeatils.class);
                intent.putExtra("Link",current.getLink());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return feeditems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Title,Description,date;
        ImageView imageView;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            Title= (TextView) itemView.findViewById(R.id.tieude);
            Description= (TextView) itemView.findViewById(R.id.noidung);
            date= (TextView) itemView.findViewById(R.id.date);
            imageView= (ImageView) itemView.findViewById(R.id.hinh);
            cardView= (CardView) itemView.findViewById(R.id.carview);

        }
    }
}
