package com.example.neitron.sunshine;

import android.app.Activity;
import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import com.example.neitron.sunshine.data.WeatherContract;

public class DetailActivity extends Activity {

    final private static String LOG_TEST = DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailFragment())
                    .commit();
        }
        Log.d(LOG_TEST,"onCrete");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TEST,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TEST,"onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TEST,"onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TEST,"onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TEST,"onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TEST,"onDestroy");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
