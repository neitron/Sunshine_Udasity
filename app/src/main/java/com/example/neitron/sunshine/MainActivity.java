package com.example.neitron.sunshine;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

// с этого активити начинается приложение
public class MainActivity extends Activity
{
    // тег для логирования (почемуто они обязательны)
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    // дополнительное теговое имя фрагмента, потом будем находить фрагмент с пощью него
    private final String FORECASTFRAGMENT_TAG = "FFTAG";
    // локация, ключ-значение
    private String mLocation;

    // при первом создании активити
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // родитель
        super.onCreate(savedInstanceState);

        // предпочтительная локация (0\ контекст этого приложения)
        mLocation = Utility.getPreferredLocation(this);
        // хмл шаблон
        setContentView(R.layout.activity_main);

        // если нет сохраненного состояния активити
        if (savedInstanceState == null)
        {
            FragmentManager fm = getFragmentManager(); // менеджер для работы с фрагментами
            FragmentTransaction ft = fm.beginTransaction(); // открываем транзакцию (как в бд перед работой напрямую)
            // добавляем свой ForecastFragment в контейнер активити
            ft.add(R.id.container, new ForecastFragment(), FORECASTFRAGMENT_TAG);
            ft.commit(); // завершаем транзакцию
        }
    }

    // перед тем как станет доступно для активности
    @Override
    protected void onResume()
    {
        super.onResume(); // родитель
        String location = Utility.getPreferredLocation( this );
        // если именили локацию
        if (location != null && !location.equals(mLocation)) {
            ForecastFragment ff = (ForecastFragment)getFragmentManager().findFragmentByTag(FORECASTFRAGMENT_TAG);
            if ( null != ff ) {
                ff.onLocationChanged();
            }
            mLocation = location;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_map) {
            openPreferredLocationInMap();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openPreferredLocationInMap() {

        String location = Utility.getPreferredLocation(this);

        // Using the URI scheme for showing a location found on a map.  This super-handy
        // intent can is detailed in the "Common Intents" page of Android's developer site:
        // http://developer.android.com/guide/components/intents-common.html#Maps
        Uri geoLocation = Uri.parse("geo:0,0?").buildUpon().appendQueryParameter("q", location).build();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(LOG_TAG, "Couldn't call " + location + ", no receiving apps installed!");
        }
    }


}
