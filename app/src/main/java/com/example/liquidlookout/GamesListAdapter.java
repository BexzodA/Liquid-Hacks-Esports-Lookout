package com.example.liquidlookout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

public class GamesListAdapter extends ArrayAdapter<Games> {
    private String TAG = "gamesAdapter";
    private Context context;
    private int res;

    public GamesListAdapter(Context context, int resource, ArrayList<Games> list){
        super(context,resource,list);
        this.context=context;
        res=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String nameIn = getItem(position).getName();
        String imageUrl = getItem(position).getImgUrl();

        Games game = new Games(nameIn,imageUrl);

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(res,parent,false);
        TextView vieName = convertView.findViewById(R.id.nameOfGame);
        ImageView imagePic = convertView.findViewById(R.id.gamePic);
        vieName.setText(nameIn);
        imagePic.setImageResource(R.drawable.teamliquid);


        return convertView;
    }


}
