package weather.weatherobserver;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements WeatherDependent {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WeatherObserver.getInstance().subscribe(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WeatherObserver.getInstance().unSubscribe(this);
    }

    public void next(View view) {
        startActivity(new Intent(this, FirstPage.class));
    }

    @Override
    public void weather(int weather) {
        TextView text = findViewById(R.id.weather1);
        text.setText("Погода: " + weather);
    }
}