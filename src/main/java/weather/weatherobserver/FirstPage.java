package weather.weatherobserver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FirstPage extends AppCompatActivity implements WeatherDependent {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        WeatherObserver.getInstance().subscribe(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WeatherObserver.getInstance().unSubscribe(this);
    }

    public void prev(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void next(View view) {
        startActivity(new Intent(this, SecondPage.class));
    }

    @Override
    public void weather(int weather) {
        TextView text = findViewById(R.id.weather2);
        text.setText("Погода: " + weather);
    }
}