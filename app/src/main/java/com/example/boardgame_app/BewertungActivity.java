package com.example.boardgame_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class BewertungActivity extends AppCompatActivity {

    private RatingBar ratingBarCurrent;
    private TextView tvCurrentRatingValue;
    private TextView tvCurrentEventDate;
    private TextView tvCurrentEventHost;
    private TextView tvCurrentEventLocation;
    private LinearLayout layoutPastEvents;

    private Event currentRateableEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bewertung);

        ratingBarCurrent = findViewById(R.id.ratingBarCurrent);
        tvCurrentRatingValue = findViewById(R.id.tvCurrentRatingValue);
        tvCurrentEventDate = findViewById(R.id.tvCurrentEventDate);
        tvCurrentEventHost = findViewById(R.id.tvCurrentEventHost);
        tvCurrentEventLocation = findViewById(R.id.tvCurrentEventLocation);
        layoutPastEvents = findViewById(R.id.layoutPastEvents);
        Button btnSaveRating = findViewById(R.id.btnSaveRating);

        loadCurrentRateableEvent();
        loadPastEvents();

        ratingBarCurrent.setOnRatingBarChangeListener((ratingBar, rating, fromUser) ->
                tvCurrentRatingValue.setText(getString(R.string.current_rating_value, rating))
        );

        btnSaveRating.setOnClickListener(v -> {
            if (currentRateableEvent != null) {
                float rating = ratingBarCurrent.getRating();

                if (rating < 0) {
                    rating = 0;
                }
                if (rating > 5) {
                    rating = 5;
                }

                currentRateableEvent.setRating(rating);
                tvCurrentRatingValue.setText(getString(R.string.saved_rating_value, rating));
                loadPastEvents();
            }
        });
    }

    private void loadCurrentRateableEvent() {
        List<Event> pastEvents = DataManager.getInstance().getPastEvents();

        if (pastEvents.isEmpty()) {
            tvCurrentEventDate.setText(getString(R.string.no_events_available));
            tvCurrentEventHost.setText("");
            tvCurrentEventLocation.setText("");
            ratingBarCurrent.setEnabled(false);
            return;
        }

        currentRateableEvent = pastEvents.get(0);

        tvCurrentEventDate.setText(currentRateableEvent.getDate());
        tvCurrentEventHost.setText(getString(R.string.host_label, currentRateableEvent.getHost()));
        tvCurrentEventLocation.setText(getString(R.string.location_label, currentRateableEvent.getLocation()));
        ratingBarCurrent.setRating(currentRateableEvent.getRating());
        tvCurrentRatingValue.setText(getString(R.string.current_rating_value, currentRateableEvent.getRating()));
    }

    private void loadPastEvents() {
        List<Event> pastEvents = DataManager.getInstance().getPastEvents();

        layoutPastEvents.removeAllViews();

        if (pastEvents.size() <= 1) {
            return;
        }

        int count = Math.min(4, pastEvents.size() - 1);

        for (int i = 1; i <= count; i++) {
            Event event = pastEvents.get(i);

            LinearLayout itemLayout = new LinearLayout(this);
            itemLayout.setOrientation(LinearLayout.VERTICAL);
            itemLayout.setPadding(0, 0, 0, 30);

            TextView tvEvent = new TextView(this);
            tvEvent.setText(event.getDate() + " | Gastgeber: " + event.getHost());
            tvEvent.setTextSize(16);

            TextView tvStars = new TextView(this);
            tvStars.setTextSize(18);

            int rating = Math.round(event.getRating());
            if (rating < 0) {
                rating = 0;
            }
            if (rating > 5) {
                rating = 5;
            }

            StringBuilder stars = new StringBuilder();
            for (int j = 0; j < rating; j++) {
                stars.append("★");
            }
            for (int j = rating; j < 5; j++) {
                stars.append("☆");
            }

            tvStars.setText(stars.toString());

            itemLayout.addView(tvEvent);
            itemLayout.addView(tvStars);

            layoutPastEvents.addView(itemLayout);
        }
    }
}