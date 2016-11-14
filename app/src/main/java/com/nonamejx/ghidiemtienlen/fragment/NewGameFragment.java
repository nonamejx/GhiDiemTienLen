package com.nonamejx.ghidiemtienlen.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.nonamejx.ghidiemtienlen.R;
import com.nonamejx.ghidiemtienlen.activity.TrackingActivity_;
import com.nonamejx.ghidiemtienlen.common.Constants;
import com.nonamejx.ghidiemtienlen.model.Game;
import com.nonamejx.ghidiemtienlen.prefs.Setting;
import com.nonamejx.ghidiemtienlen.prefs.SharedPrefsManager;

import org.androidannotations.annotations.EFragment;

/**
 * Created by noname
 * on 09/11/2016.
 */
@EFragment
public class NewGameFragment extends Fragment {

    public static NewGameFragment newInstance() {
        return NewGameFragment_.builder().build();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_game, container, false);
        final EditText[] editTexts = new EditText[5];
        editTexts[0] = (EditText) v.findViewById(R.id.edtPlayer1);
        editTexts[1] = (EditText) v.findViewById(R.id.edtPlayer2);
        editTexts[2] = (EditText) v.findViewById(R.id.edtPlayer3);
        editTexts[3] = (EditText) v.findViewById(R.id.edtPlayer4);
        editTexts[4] = (EditText) v.findViewById(R.id.edtNumberOfTurns);
        CheckBox cbHideCurrentTurns = (CheckBox) v.findViewById(R.id.cbHideCurrentTurns);
        cbHideCurrentTurns.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPrefsManager.getInstance(getContext()).setupSetting(Setting.SHOW_NUMBER_OF_TURNS, !b);
            }
        });
        CheckBox cbHideCurrentScore = (CheckBox) v.findViewById(R.id.cbHideCurrentScore);
        cbHideCurrentScore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPrefsManager.getInstance(getContext()).setupSetting(Setting.SHOW_CURRENT_RESULT, !b);
            }
        });
        Button btnStartGame = (Button) v.findViewById(R.id.btnStartGame);
        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] players = new String[4];
                for (int i = 0; i < Constants.NUMBER_OF_PLAYERS; i++) {
                    players[i] = editTexts[i].getText().toString();
                }
                int numberOfTurns = Integer.parseInt(editTexts[4].getText().toString());
                Game game = new Game(players, numberOfTurns);
                Intent i = new Intent(getContext(), TrackingActivity_.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.INTENT_KEY_GAME_OBJECT, game);
                i.putExtras(bundle);
                startActivity(i);

                // clean data
                for (int j = 0; j < Constants.NUMBER_OF_PLAYERS; j++) {
                    editTexts[j].setText("");
                }
                editTexts[4].setText(String.valueOf(10));
            }
        });
        return v;
    }
}
