package androidkejar.app.sunshine.controller.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidkejar.app.sunshine.DetailActivity;
import androidkejar.app.sunshine.ItemObject;
import androidkejar.app.sunshine.R;

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
        holder.txtDay.setText(itemObjects.get(position).getWeather().get(0).getMain());
        holder.cardviewSunshineItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("judul", itemObjects.get(holder.getAdapterPosition()).getTemp().getDay() + "");
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemObjects.size();
    }

    class ListHolder extends RecyclerView.ViewHolder {
        TextView txtDay;
        CardView cardviewSunshineItem;

        ListHolder(View itemView) {
            super(itemView);
            txtDay = (TextView) itemView.findViewById(R.id.txt_day);
            cardviewSunshineItem = (CardView) itemView.findViewById(R.id.cardview_sunshine_item);
        }
    }
}
