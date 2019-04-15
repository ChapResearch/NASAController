package com.lukekaufman48gmail.controller;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CompetitionsAdapter extends RecyclerView.Adapter<CompetitionsAdapter.MyViewHolder> {

    private List<Competition> competitionList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView code, city, venue, state;

        public MyViewHolder(View view) {
            super(view);
            code = (TextView) view.findViewById(R.id.code);
            city = (TextView) view.findViewById(R.id.city);
            venue = (TextView) view.findViewById(R.id.venue);
            state = view.findViewById(R.id.state);
        }
    }


    public CompetitionsAdapter(List<Competition> cList, Context context) {
        this.competitionList = cList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.complist_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Competition comp = competitionList.get(position);
        holder.city.setText(comp.getCity());
        holder.venue.setText(comp.getVenue());
        holder.code.setText(comp.getCode());
        switch(comp.getState()){
            case 0:
                holder.state.setTextColor(ContextCompat.getColor(context, R.color.greyed_out));
                holder.state.setText("Finished"); break;
            case 1:
                holder.state.setTextColor(ContextCompat.getColor(context, R.color.green_tint));
                holder.state.setText("Current"); break;
            case 2: holder.state.setText("Upcoming"); break;
            case 3: holder.state.setText("Unknown"); break;
        }

    }

    @Override
    public int getItemCount() {
        return competitionList.size();
    }
}
