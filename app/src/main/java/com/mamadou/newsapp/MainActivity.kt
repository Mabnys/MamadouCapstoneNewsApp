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

/*

"source": {
                "id": null,
                "name": "Android Central"
            },
            "author": "andrew.myrick@futurenet.com (Andrew Myrick)",
            "title": "Samsung Galaxy Watch 5 review: The best Android smartwatch, for now",
            "description": "With the Galaxy Watch 4, there was a lot of hype surrounding the arrival of Wear OS 3. But with more competition on the horizon, it's definitely plausible to think that the Galaxy Watch 5 might not hold the crown of best Android smartwatch for too long.",
            "url": "https://www.androidcentral.com/wearables/samsung-galaxy-watch-5-review",
            "urlToImage": "https://cdn.mos.cms.futurecdn.net/LfJf2YpQpp3em3z7HdhoKa-1200-80.jpg",
            "publishedAt": "2022-08-26T12:00:00Z",
            "content": "For the past year, Samsung has enjoyed an increased market share in the smartwatch game thanks to the Galaxy Watch 4 and Watch 4 Classic being the only Wear OS 3 options available. Google even felt g… [+12291 chars]"
        },


 */


package com.mamadou.newsapp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}

