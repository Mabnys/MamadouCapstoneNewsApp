package com.mamadou.newsapp

import com.mamadou.newsapp.models.*

class NewsService {

    private val  article = arrayListOf(

        Article(
            "CNN News",
            "Futures ebb as Powell's speech nears - Reuters",
            "U.S. stock index futures slipped on Friday as investors worried about hawkish signals from Federal Reserve Chair Jerome Powell at the annual Jackson Hole symposium amid fears of slowing economic growth.",
            "https://www.reuters.com/markets/europe/futures-ebb-powells-speech-nears-2022-08-26/",
            "https://www.reuters.com/resizer/Z10kPQ9m23PxivSgHwWaRRMoOYU=/1200x628/smart/filters:quality(80)/cloudfront-us-east-2.images.arcpublishing.com/reuters/VI6DJLGGCJJRNKWAQQNCEUBENA.jpg" ,
            "2022-08-26T03:55:00Z",
            "Aug 26 (Reuters) - U.S. stock index futures slipped on Friday as investors worried about hawkish signals from Federal Reserve Chair Jerome Powell at the annual Jackson Hole symposium amid fears of sl… [+2556 chars]",
            Source (
                "reuters",
                "REUTERS",
                CategoryType.GENERAL,
                LanguageType.EN,
                CountryType.US,
            )
        ),

        Article (
            null,
            "Ben Lovejoy",
            "Ben Lovejoy",
            "https://9to5mac.com/2022/08/26/twitter-covid/",
            "https://i0.wp.com/9to5mac.com/wp-content/uploads/sites/6/2022/08/Twitter-COVID.jpg?resize=1200%2C628&quality=82&strip=all&ssl=1",
            "2022-08-26T12:08:54Z",
            "Twitter COVID-19 misinformation labeling has got the company more into trouble as it made literally dozens of embarrassing errors this week. It mistakenly applied the label to factually correct tweet… [+3614 chars]",
            Source (
                "BBC",
                "9to5Mac",
                CategoryType.ENTERTAINMENT,
                LanguageType.EN,
                CountryType.CA,
            )
        ),

        Article (
            "Space Explored",
            "SpaceX & T-Mobile to bring direct satellite connection to cell phones with ‘Coverage Above and Beyond’",
            "Yesterday, SpaceX and T-Mobile announced they would be holding a big event. With Musk hyping up the event as “something special” and “big news,” speculation was rife on Twitter with guesses as to what the big announcement could be. Now, it is clear that the a…\"",
            "https://spaceexplored.com/2022/08/25/starlink-direct-cell-phone-connection/",
            "https://i0.wp.com/spaceexplored.com/wp-content/uploads/sites/10/2022/08/SpaceX-T-Mobile.jpg?resize=1200%2C628&ssl=1",
            "2022-08-26T00:25:52Z",
            "Yesterday, SpaceX and T-Mobile announced they would be holding a big event. With Musk hyping up the event as something special and big news, speculation was rife on Twitter with guesses as to what th… [+3129 chars]",
            Source (
                null,
                "Spaceexplored.com",
                CategoryType.ENTERTAINMENT,
                LanguageType.EN,
                CountryType.CA,
                    )
         ),

        Article (
            "Phillip Moyer",
            "How to update to the latest version of Google Chrome",
            "Yes, app updates are annoying, but you don't want to skip them. Here's how to update your browser to prevent security and privacy vulnerabilities.",
            "https://www.androidpolice.com/how-to-update-google-chrome/",
            "https://static1.anpoimages.com/wordpress/wp-content/uploads/2022/08/Update-Google-Chrome.jpg",
            "2022-08-26T10:52:13Z",
            "Google Chrome, like many modern browsers, can automatically update itself. This increases the browser's security to keep you safe and makes sure that everyone has access to the latest web features. H… [+2882 chars]",
            Source (
                null,
                "Android Police",
                CategoryType.ENTERTAINMENT,
                LanguageType.EN,
                CountryType.CA,
            )
        ),

        Article (
            "stephen.warwick@futurenet.com (Stephen Warwick)",
            "You could soon get a payout from Apple's \$95 million AppleCare lawsuit",
            "Members of a class action lawsuit over AppleCare are receiving notifications that they will soon get a payment from the company.",
            "https://www.imore.com/apple/you-could-soon-get-a-payout-from-apples-dollar95-million-applecare-lawsuit",
            "https://cdn.mos.cms.futurecdn.net/FA8dGkxTzCqndyXYLeD5c5-1200-80.jpg",
            "2022-08-26T10:32:36Z",
            "Members of a class action lawsuit against Apple that settled to the tune of \$95 million are starting to receive notifications that they may soon get a payment from the company as part of the claim. \\r… [+1754 chars]",
            Source (
                null,
                "iMore",
                CategoryType.ENTERTAINMENT,
                LanguageType.EN,
                CountryType.CA,
            )
        )
    )

    fun getArticles(): List<Article> {
        return article
    }
}