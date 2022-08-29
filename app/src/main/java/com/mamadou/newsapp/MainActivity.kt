/**
 *
 * Homework 2

To get a Good Job (Meets Expectations) grade:

  - Data classes are created for Article and Source
  - 5 TextViews where each one includes text of the article title, author, and source name.
  - Nullable types are used for certain properties which can be null such as author, source id. Other properties can be checked from News API documentation.
  - Hardcoded news data is saved in ArrayList
  - Layout for main activity is created
 - Loops are used for iterating the list and data is shown on the screen
 - Null checks are done using if/else statements
 - Code is divided into multiple functions instead of doing everything in onCreate(). At least one new function should be created.

  Nice to have (for an Above and Beyond grade)

 - Use default values with functions
 - Use maps to store hardcoded data instead of ArrayList

 *
 */


package com.mamadou.newsapp

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }
}

