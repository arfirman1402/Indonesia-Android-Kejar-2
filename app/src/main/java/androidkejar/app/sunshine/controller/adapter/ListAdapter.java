package androidkejar.app.sunshine.controller.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import androidkejar.app.sunshine.BaseApp;
import androidkejar.app.sunshine.DetailActivity;
import androidkejar.app.sunshine.ItemObject;
import androidkejar.app.sunshine.R;
import androidkejar.app.sunshine.controller.CloudURL;

/**
 * Created by alodokter-it on 12/11/16.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder> {

    Context context;
    List<ItemObject.ListWeather.ListOfWeather> itemObjects;

    public ListAdapter(Context context, List<ItemObject.ListWeather.ListOfWeather> itemObjects) {
        this.context = context;
        this.itemObjects = itemObjects;
    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_sunshine_item, null);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListHolder holder, int position) {
        long dateTime = itemObjects.get(position).getDatetime();
        long dateTimeSeconds = dateTime * 1000;
        Calendar calDateTime = GregorianCalendar.getInstance();
        calDateTime.setTimeInMillis(dateTimeSeconds);
        List<String> listDay = Arrays.asList(BaseApp.Dates.getListDay());
        List<String> listMonth = Arrays.asList(BaseApp.Dates.getListMonth());
        String strDateTime = listDay.get(calDateTime.get(GregorianCalendar.DAY_OF_WEEK) - 1) + ", " + calDateTime.get(GregorianCalendar.DAY_OF_MONTH) + " " + listMonth.get(calDateTime.get(GregorianCalendar.MONTH)) + " " + calDateTime.get(GregorianCalendar.YEAR);
        holder.txtDay.setText((strDateTime));
        holder.txtStatus.setText(itemObjects.get(position).getWeather().get(0).getDescription());
        holder.txtCelciusItem.setText((int) Math.round(itemObjects.get(position).getTemp().getDay()) + " \u2103");
        Glide.with(context)
                .load(CloudURL.getImageWeather(itemObjects.get(position).getWeather().get(0).getIcon()))
                .centerCrop()
                .fitCenter()
                .crossFade()
                .into(holder.imgSunshineItem);
        holder.cardviewSunshineItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("main", itemObjects.get(holder.getAdapterPosition()).getWeather().get(0).getMain());
                intent.putExtra("desc", itemObjects.get(holder.getAdapterPosition()).getWeather().get(0).getDescription());
                intent.putExtra("temperature", itemObjects.get(holder.getAdapterPosition()).getTemp().getDay());
                intent.putExtra("icon", itemObjects.get(holder.getAdapterPosition()).getWeather().get(0).getIcon());
                intent.putExtra("humidity", itemObjects.get(holder.getAdapterPosition()).getHumidity());
                intent.putExtra("pressure", itemObjects.get(holder.getAdapterPosition()).getPressure());
                intent.putExtra("speed", itemObjects.get(holder.getAdapterPosition()).getSpeed());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemObjects.size();
    }

    class ListHolder extends RecyclerView.ViewHolder {
        TextView txtDay, txtCelciusItem, txtStatus;
        CardView cardviewSunshineItem;
        ImageView imgSunshineItem;

        ListHolder(View itemView) {
            super(itemView);
            txtDay = (TextView) itemView.findViewById(R.id.txt_day);
            txtStatus = (TextView) itemView.findViewById(R.id.txt_status);
            txtCelciusItem = (TextView) itemView.findViewById(R.id.txt_celcius_item);
            imgSunshineItem = (ImageView) itemView.findViewById(R.id.img_sunshine_item);
            cardviewSunshineItem = (CardView) itemView.findViewById(R.id.cardview_sunshine_item);
        }
    }
}
