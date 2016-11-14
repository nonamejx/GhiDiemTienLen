package com.nonamejx.ghidiemtienlen.activity;

import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.nonamejx.ghidiemtienlen.R;
import com.nonamejx.ghidiemtienlen.database.DataCenter;
import com.nonamejx.ghidiemtienlen.fragment.GameFragment;
import com.nonamejx.ghidiemtienlen.fragment.dialog.ConfirmDeleteGameDialog;

import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity implements ConfirmDeleteGameDialog.IDialogReturn {

    private GameFragment gameFragment;

    @Override
    protected Fragment createFragment() {
        gameFragment = GameFragment.newInstance();
        return gameFragment;
    }

    @Override
    public void afterView() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear_all_data:
                DataCenter.getInstance().deleteAllGames();
                gameFragment.updateRecyclerView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onReturnOK() {
        if (gameFragment != null) {
            gameFragment.updateRecyclerView();
        }
    }
}
