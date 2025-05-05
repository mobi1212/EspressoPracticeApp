package com.example.espressopracticeapp.util;

import androidx.test.espresso.idling.CountingIdlingResource;

public class GlobalIdlingResource {
    public static final CountingIdlingResource networkIdling =
            new CountingIdlingResource("GlobalNetwork");
}
