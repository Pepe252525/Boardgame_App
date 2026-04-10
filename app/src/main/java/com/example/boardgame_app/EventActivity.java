package com.example.boardgame_app;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class EventActivity extends AppCompatActivity {

    private TextView tvNextDate;
    private TextView tvNextHost;
    private TextView tvNextLocation;
    private LinearLayout layoutUpcomingEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        tvNextDate = findViewById(R.id.tvNextDate);
        tvNextHost = findViewById(R.id.tvNextHost);
        tvNextLocation = findViewById(R.id.tvNextLocation);
        layoutUpcomingEvents = findViewById(R.id.layoutUpcomingEvents);

        loadEvents();
    }

    private void loadEvents() {
        List<Event> upcomingEvents = DataManager.getInstance().getUpcomingEvents(5);

        if (upcomingEvents.isEmpty()) {
            tvNextDate.setText(getString(R.string.no_events_available));
            tvNextHost.setText("");
            tvNextLocation.setText("");
            return;
        }

        Event nextEvent = upcomingEvents.get(0);
        tvNextDate.setText(nextEvent.getDate());
        tvNextHost.setText(getString(R.string.host_label, nextEvent.getHost()));
        tvNextLocation.setText(getString(R.string.location_label, nextEvent.getLocation()));

        layoutUpcomingEvents.removeAllViews();

        for (int i = 1; i < upcomingEvents.size(); i++) {
            Event event = upcomingEvents.get(i);

            LinearLayout eventBlock = new LinearLayout(this);
            eventBlock.setOrientation(LinearLayout.VERTICAL);
            eventBlock.setPadding(0, 0, 0, 32);

            TextView tvDate = new TextView(this);
            tvDate.setText(event.getDate());
            tvDate.setTextSize(18);

            TextView tvHost = new TextView(this);
            tvHost.setText(getString(R.string.host_label, event.getHost()));
            tvHost.setTextSize(16);

            TextView tvLocation = new TextView(this);
            tvLocation.setText(getString(R.string.location_label, event.getLocation()));
            tvLocation.setTextSize(16);

            eventBlock.addView(tvDate);
            eventBlock.addView(tvHost);
            eventBlock.addView(tvLocation);

            layoutUpcomingEvents.addView(eventBlock);
        }
    }
}