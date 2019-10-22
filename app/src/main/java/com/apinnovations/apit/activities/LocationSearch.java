package com.apinnovations.apit.activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.apinnovations.apit.R;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;


public class LocationSearch extends AppCompatActivity {

    private static String TAG = LocationSearch.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_search);
       // Button nextButton = findViewById(R.id.nextButton);
        String apiKey = getString(R.string.api_key);

        /**
         * Initialize Places. For simplicity, the API key is hard-coded. In a production
         * environment we recommend using a secure mechanism to manage API keys.
         */
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), apiKey);
        }

        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place)
            {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
                Intent intentBackToSignUp = new Intent(LocationSearch.this, SignUp_Act.class);
                intentBackToSignUp.putExtra("Address",place.getName());
                setResult(Activity.RESULT_OK,intentBackToSignUp);
                finish();
            }

            @Override
            public void onError(Status status)
            {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

      //  nextButton.setOnClickListener(v -> startActivity(new Intent(AutocompleteFromFragmentActivity.this, AutocompleteFromIntentActivity.class)));
    }

    }



