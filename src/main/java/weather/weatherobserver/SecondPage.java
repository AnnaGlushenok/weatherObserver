package weather.weatherobserver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondPage extends AppCompatActivity implements WeatherDependent {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);
        WeatherObserver.getInstance().subscribe(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WeatherObserver.getInstance().unSubscribe(this);
    }

    public void prev(View view) {
        startActivity(new Intent(this, FirstPage.class));
    }

    public void start(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void weather(int weather) {
        TextView text = findViewById(R.id.weather3);
        text.setText("Погода: " + weather);
    }
}