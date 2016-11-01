package com.example.nikitok.minesweeper.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nikitok.minesweeper.R;
import com.example.nikitok.minesweeper.database.dbHelper;
import com.example.nikitok.minesweeper.model.Player;

import java.util.ArrayList;
import java.util.List;



public class TopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Player> list;
    public TopAdapter(Context context){
        dbHelper helper = new dbHelper(context);
        list = new ArrayList<>();
        list.addAll(helper.getQueryManager().getPlayers(null, null, dbHelper.PLAYER_SCORE_COLUMN+ " DESC"));


    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_player, parent, false);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView score = (TextView) view.findViewById(R.id.score);

        return new PlayerViewHolder(view, name, score);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Player player = list.get(position);
        PlayerViewHolder viewHolder = (PlayerViewHolder) holder;
        viewHolder.tvScore.setText(String.valueOf(player.getScore()));
        viewHolder.tvName.setText(player.getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    protected class PlayerViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvScore;

        public PlayerViewHolder(View itemView, TextView tvName, TextView tvScore) {
            super(itemView);
            this.tvName = tvName;
            this.tvScore = tvScore;
        }



    }
}
