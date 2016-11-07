package com.nonamejx.ghidiemtienlen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nonamejx.ghidiemtienlen.R;
import com.nonamejx.ghidiemtienlen.database.RealmHelper;
import com.nonamejx.ghidiemtienlen.model.GameResult;

import java.util.List;

/**
 * Created by noname
 * on 08/11/2016.
 */
public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {
    private final Context mContext;
    private final List<GameResult> mGameResults;

    public GameAdapter(Context context, List<GameResult> gameResults) {
        mContext = context;
        mGameResults = gameResults;
    }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_list_games, parent, false);
        return new GameViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {
        holder.tvPlayer1.setText(RealmHelper.getInstance().getPlayerName(mGameResults.get(position).getResults().get(0).getPlayerId()));
        holder.tvPlayer2.setText(RealmHelper.getInstance().getPlayerName(mGameResults.get(position).getResults().get(1).getPlayerId()));
        holder.tvPlayer3.setText(RealmHelper.getInstance().getPlayerName(mGameResults.get(position).getResults().get(2).getPlayerId()));
        holder.tvPlayer4.setText(RealmHelper.getInstance().getPlayerName(mGameResults.get(position).getResults().get(3).getPlayerId()));
        holder.tvResult1.setText(mGameResults.get(position).getResults().get(0).getResult() + "");
        holder.tvResult2.setText(mGameResults.get(position).getResults().get(1).getResult() + "");
        holder.tvResult3.setText(mGameResults.get(position).getResults().get(2).getResult() + "");
        holder.tvResult4.setText(mGameResults.get(position).getResults().get(3).getResult() + "");
    }

    @Override
    public int getItemCount() {
        return mGameResults.size();
    }

    class GameViewHolder extends RecyclerView.ViewHolder {
        final TextView tvPlayer1, tvPlayer2, tvPlayer3, tvPlayer4, tvResult1, tvResult2, tvResult3, tvResult4;

        public GameViewHolder(View itemView) {
            super(itemView);
            tvPlayer1 = (TextView) itemView.findViewById(R.id.tvPlayer1);
            tvPlayer2 = (TextView) itemView.findViewById(R.id.tvPlayer2);
            tvPlayer3 = (TextView) itemView.findViewById(R.id.tvPlayer3);
            tvPlayer4 = (TextView) itemView.findViewById(R.id.tvPlayer4);
            tvResult1 = (TextView) itemView.findViewById(R.id.tvResult1);
            tvResult2 = (TextView) itemView.findViewById(R.id.tvResult2);
            tvResult3 = (TextView) itemView.findViewById(R.id.tvResult3);
            tvResult4 = (TextView) itemView.findViewById(R.id.tvResult4);
        }
    }
}
