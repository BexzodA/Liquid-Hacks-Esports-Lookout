package com.example.liquidlookout;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class MyCustomAdapter extends ArrayAdapter<Match> {
    Context context;
    List<Match> matches;

    public MyCustomAdapter(Context context, int resource, List<Match> matches) {
        super(context,resource, matches);
        this.context = context;
        this.matches = matches;
    }
    @SuppressLint("ViewHolder")
    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView= inflater.inflate(R.layout.upcoming_match_layout, parent, false);
        final Button subscribe = (Button) convertView.findViewById(R.id.subscribeButton);
        final Match m = matches.get(pos);
        updateButton(subscribe, m);
        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(m.isSubscribed()) {
                    Toast.makeText(context, "Unsubscribed!", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(context, "Subscribed!", Toast.LENGTH_SHORT).show();
                m.subscribe();
                updateButton(subscribe, m);
            }
        });
        TextView text = (TextView) convertView.findViewById(R.id.teamMatchText);
        ImageView im1 = (ImageView) convertView.findViewById(R.id.team1Image);
        ImageView im2 = (ImageView) convertView.findViewById(R.id.team2Image);
        TextView time = (TextView) convertView.findViewById(R.id.timeDateText);
        ZonedDateTime matchTime = m.getBegin().withZoneSameLocal(ZoneId.systemDefault());
        time.setText(matchTime.getMonth().toString() + ", " + matchTime.getDayOfMonth() + " " + matchTime.getYear() + "\n" + getHourAndMinute(matchTime));
        text.setText(m.getMatchName());

        Bitmap logo1 = m.getTeams().get(0).getLogo();
        Bitmap logo2 = m.getTeams().get(1).getLogo();
        im1.setImageBitmap(logo1);
        im2.setImageBitmap(logo2);

        return convertView;
    }

    private String getHourAndMinute(ZonedDateTime time) {
        boolean am = time.getHour() - 12 < 0;
        String result = "";
        if(am) {
            result += time.getHour();
            result += time.getMinute() == 0 ? "" : ":" + time.getMinute();
            result += " am";
        } else {
            result += Math.abs(time.getHour() - 12);
            result += time.getMinute() == 0 ? "" : ":" + time.getMinute();
            result += " pm";
        }
        return result;
    }

    private void updateButton(Button b, Match m) {
        if(m.isSubscribed()){
            b.setText("Subscribed!");
        } else {
            b.setText("Subscribe");
        }
    }

}