package com.nonamejx.ghidiemtienlen.activity;

import android.content.Context;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nonamejx.ghidiemtienlen.R;
import com.nonamejx.ghidiemtienlen.adapter.TrackingResultAdapter;
import com.nonamejx.ghidiemtienlen.common.Constants;
import com.nonamejx.ghidiemtienlen.common.DividerItemDecoration;
import com.nonamejx.ghidiemtienlen.common.RecyclerTouchListener;
import com.nonamejx.ghidiemtienlen.common.TurnResultDialogListener;
import com.nonamejx.ghidiemtienlen.fragment.dialog.ConfirmExitDialog;
import com.nonamejx.ghidiemtienlen.fragment.dialog.TurnResultDialog;
import com.nonamejx.ghidiemtienlen.model.Game;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by noname
 * on 11/11/2016.
 */
@EActivity(R.layout.activity_tracking)
public class TrackingActivity extends AppCompatActivity implements TurnResultDialogListener {
    @ViewById(R.id.tvPlayer1)
    TextView tvPlayer1;
    @ViewById(R.id.tvPlayer2)
    TextView tvPlayer2;
    @ViewById(R.id.tvPlayer3)
    TextView tvPlayer3;
    @ViewById(R.id.tvPlayer4)
    TextView tvPlayer4;
    @ViewById(R.id.btnAddTurnResult)
    FloatingActionButton btnAddTurnResult;
    @ViewById(R.id.recyclerViewTrackingResult)
    RecyclerView recyclerViewTrackingResult;

    TrackingResultAdapter adapter;

    private Game game;
    private Integer currentTurn = -1;

    @AfterViews
    void afterView() {
        game = (Game) getIntent().getSerializableExtra(Constants.INTENT_KEY_GAME_OBJECT);
        tvPlayer1.setText(game.getPlayerNames()[0]);
        tvPlayer2.setText(game.getPlayerNames()[1]);
        tvPlayer3.setText(game.getPlayerNames()[2]);
        tvPlayer4.setText(game.getPlayerNames()[3]);

        recyclerViewTrackingResult.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TrackingResultAdapter(this, currentTurn, game);
        recyclerViewTrackingResult.setAdapter(adapter);
        recyclerViewTrackingResult.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerViewTrackingResult.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerViewTrackingResult, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                TurnResultDialog.newInstance(game.getPlayerNames(), game.getResult()[position], position).show(getSupportFragmentManager(), "Title");
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
    }

    private void updateRecyclerView() {
        if (recyclerViewTrackingResult != null) {
            adapter.updateAdapter(this.currentTurn);
        }
    }

    private void addResult(int[] result) {
        currentTurn++;
        if (game != null) {
            System.arraycopy(result, 0, game.getResult()[currentTurn], 0, Constants.NUMBER_OF_PLAYERS);
        }
    }

    private void updateResult(int position, int[] result) {
        if (game != null) {
            System.arraycopy(result, 0, game.getResult()[position], 0, Constants.NUMBER_OF_PLAYERS);
        }
    }

    @Click(R.id.btnAddTurnResult)
    void addTurnResultClick() {
        // show dialog
        TurnResultDialog.newInstance(game.getPlayerNames(), null, currentTurn).show(getSupportFragmentManager(), "Title");
    }

    @Override
    public void onBackPressed() {
        // vibrate
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(100);
        // show confirm dialog
        ConfirmExitDialog.newInstance(getResources().getString(R.string.confirm_exit_tracking)).show(getSupportFragmentManager(), "Title");
    }

    @Override
    public void onReturnTurnResult(int turnResultPosition, int[] turnResult) {
        if (turnResultPosition <= currentTurn) {
            // update result
            updateResult(turnResultPosition, turnResult);
            updateRecyclerView();
        } else {
            // add result
            addResult(turnResult);
            updateRecyclerView();
        }
    }
}