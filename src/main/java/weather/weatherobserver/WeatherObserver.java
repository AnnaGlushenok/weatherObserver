package weather.weatherobserver;

import android.os.Handler;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WeatherObserver implements Runnable {
    private static WeatherObserver observer;
    private ArrayList<WeatherDependent> weatherDependents;
    private Thread timer;
    Handler handler;

    private WeatherObserver() {
        this.weatherDependents = new ArrayList<>();
        this.handler = new Handler();
        this.timer = new Thread(this);
        this.timer.setDaemon(true);
        this.timer.start();
    }

    public static synchronized WeatherObserver getInstance() {
        if (observer == null)
            observer = new WeatherObserver();
        return observer;
    }

    public void subscribe(WeatherDependent weatherDependent) {
        weatherDependents.add(weatherDependent);
    }

    public void unSubscribe(WeatherDependent weatherDependent) {
        weatherDependents.remove(weatherDependent);
    }

    private int cyclone() {
        return (int) (System.currentTimeMillis() / 1000l) % 35;
    }

    private void notifyWeatherDependent(int weather) {
        weatherDependents.forEach(d -> d.weather(weather));
    }

    @Override
    public void run() {
        while (true) {
            int weather = cyclone();
            System.out.println(weather);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    notifyWeatherDependent(weather);
                }
            });
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
