package com.nonamejx.ghidiemtienlen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nonamejx.ghidiemtienlen.R;
import com.nonamejx.ghidiemtienlen.common.Constants;
import com.nonamejx.ghidiemtienlen.model.Game;

/**
 * Created by noname
 * on 10/11/2016.
 */
public class TableResultAdapter extends RecyclerView.Adapter<TableResultAdapter.TableResultViewHolder> {
    private final Context mContext;
    private final Game mGame;

    public TableResultAdapter(Context context, Game game) {
        mContext = context;
        mGame = game;
    }

    @Override
    public TableResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_list_table_results, parent, false);
        return new TableResultViewHolder((v));
    }

    @Override
    public void onBindViewHolder(TableResultViewHolder holder, int position) {
        for (int i = 0; i < Constants.NUMBER_OF_PLAYERS; i++) {
            holder.tvResults[i].setText(mGame.getResult()[position][i] + "");
        }
        holder.tvResults[4].setText(position + 1 + "");
    }

    @Override
    public int getItemCount() {
        return mGame.getNumberOfTurns();
    }

    class TableResultViewHolder extends RecyclerView.ViewHolder {
        final TextView[] tvResults;

        public TableResultViewHolder(View itemView) {
            super(itemView);
            tvResults = new TextView[5];
            tvResults[0] = (TextView) itemView.findViewById(R.id.tvResult1);
            tvResults[1] = (TextView) itemView.findViewById(R.id.tvResult2);
            tvResults[2] = (TextView) itemView.findViewById(R.id.tvResult3);
            tvResults[3] = (TextView) itemView.findViewById(R.id.tvResult4);
            tvResults[4] = (TextView) itemView.findViewById(R.id.tvIndex);
        }
    }
}
