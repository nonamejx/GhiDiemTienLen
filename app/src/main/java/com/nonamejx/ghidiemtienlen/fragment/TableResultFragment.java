package com.nonamejx.ghidiemtienlen.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nonamejx.ghidiemtienlen.R;
import com.nonamejx.ghidiemtienlen.adapter.TableResultAdapter;
import com.nonamejx.ghidiemtienlen.common.Constants;
import com.nonamejx.ghidiemtienlen.common.DividerItemDecoration;
import com.nonamejx.ghidiemtienlen.database.DataCenter;
import com.nonamejx.ghidiemtienlen.model.Game;
import com.nonamejx.ghidiemtienlen.utils.MyUtils;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;

import java.io.File;

/**
 * Created by noname
 * on 10/11/2016.
 */
@EFragment
public class TableResultFragment extends Fragment {
    @FragmentArg
    String mGameId;

    private Game mGame;

    public static TableResultFragment newInstance(String gameId) {
        return TableResultFragment_.builder().mGameId(gameId).build();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mGame = DataCenter.getInstance().getGame(mGameId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_table_result, container, false);
        RecyclerView mRecyclerViewTableResult = (RecyclerView) v.findViewById(R.id.recyclerViewTableResult);
        mRecyclerViewTableResult.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewTableResult.setAdapter(new TableResultAdapter(getActivity(), mGame));
        mRecyclerViewTableResult.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));

        TextView[] tvPlayers = new TextView[4];
        tvPlayers[0] = (TextView) v.findViewById(R.id.tvPlayer1);
        tvPlayers[1] = (TextView) v.findViewById(R.id.tvPlayer2);
        tvPlayers[2] = (TextView) v.findViewById(R.id.tvPlayer3);
        tvPlayers[3] = (TextView) v.findViewById(R.id.tvPlayer4);
        for (int i = 0; i < Constants.NUMBER_OF_PLAYERS; i++) {
            tvPlayers[i].setText(mGame.getPlayerNames()[i]);
        }

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_share, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_share) {
            shareImage(MyUtils.saveBitmap(MyUtils.takeScreenShot(getActivity()), getResources().getString(R.string.file_name_result_screenshot)));
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareImage(File file) {
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");

        intent.putExtra(android.content.Intent.EXTRA_TEXT, new String("GhiDiemDanhBai"));
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        startActivity(Intent.createChooser(intent, "share via"));
    }
}
