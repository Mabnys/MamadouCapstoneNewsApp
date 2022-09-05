package com.mamadou.newsapp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.view.children
import com.mamadou.newsapp.databinding.ActivityMainBinding
import com.mamadou.newsapp.models.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

 private val  article = listOf(
 Article(
     description = "U.S. stock index futures slipped on Friday as investors worried about hawkish signals from Federal Reserve Chair Jerome Powell at the annual Jackson Hole symposium amid fears of slowing economic growth.",
    url = "https://www.reuters.com/markets/europe/futures-ebb-powells-speech-nears-2022-08-26/",
    ),
 Article(
     description = "",
    url = "Breakingviews - China-U.S. audit deal success is a long way off - Reuters",
    ),
 Article(
     description = "aweinman@businessinsider.com (Aaron Weinman)",
    url = "Doctored bank statements and a decade-long graft. This banker has been accused of stealing \$50 million in a lawsuit that claims the money went toward hotels in New York, Miami, and land in the Catskills.",
    ),
 Article(
     description = "Alberto Martín",
    url = "El iPhone 14 morado se deja ver en un vídeo real",
    ),
 Article(
     description = "news@appleinsider.com (William Gallagher)",
    url = "Elon Musk and T-Mobile announce universal cell coverage via satellite",
    ),
 )

    private val source: List<Source> = listOf(
        Source(
            id ="cnn",
            name = "CNN News",
            article = listOf(
            Article(
                description = "U.S. stock index futures slipped on Friday as investors worried about hawkish signals from Federal Reserve Chair Jerome Powell at the annual Jackson Hole symposium amid fears of slowing economic growth.",
                url = "https://www.reuters.com/markets/europe/futures-ebb-powells-speech-nears-2022-08-26/",
            )),
            category = CategoryType.GENERAL,
            language = LanguageType.EN,
            country = CountryType.US
        ),
        Source(
            id ="africa",
            name = "AFRICA News",
            article = emptyList(),
            category = CategoryType.BUSINESS,
            language = LanguageType.FR,
            country = CountryType.FR
        ),
        Source(
            id ="",
            name = "FOX News",
            article = listOf(
                Article(
                    description = "",
                    url = "Breakingviews - China-U.S. audit deal success is a long way off - Reuters",
                ),
                Article(
                    description = "",
                    url = "Doctored bank statements and a decade-long graft. This banker has been accused of stealing \$50 million in a lawsuit that claims the money went toward hotels in New York, Miami, and land in the Catskills.",
                ),
            ),
            category = CategoryType.TECHNOLOGY,
            language = LanguageType.EN,
            country = CountryType.US
        ),
        Source(
            id ="",
            name = "TNT News",
            article = listOf(
                Article(
                    description = "Alberto Martín",
                    url = "El iPhone 14 morado se deja ver en un vídeo real",
                    ),
                Article(
                    description = "news@appleinsider.com (William Gallagher)",
                    url = "Elon Musk and T-Mobile announce universal cell coverage via satellite",
                    ),
                ),
            category = CategoryType.ENTERTAINMENT,
            language = LanguageType.EN,
            country = CountryType.US
        ),
        Source(
            id ="",
            name = "NY1 News",
            article = emptyList(),
            category = CategoryType.SPORTS,
            language = LanguageType.EN,
            country = CountryType.US
        )
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setNewsListText()
    }

    private fun setNewsListText() {
        val view = binding.root
        setContentView(view)

        val mainGroup = binding.mainGroup
        var count = 0

        for (i in mainGroup.children) {
            if (i is TextView) {
                var content = "\n - {"

                if (source[count].id != null) {
                    content += "\n" + "    id:" + source[count].id
                }
                content += "\n" + "    name:" + source[count].name
                content += "\n " + "    description: " + article[count].description
                content += "\n " + "    url: " + article[count].url
                content += "\n " + "    category: " + source[count].category.name
                content += "\n " + "    language: " + source[count].language.name
                content += "\n " + "    country: " + source[count].country.name
                ("$content\n   },").apply { i.text = this }
                count++
            }
        }
    }
}
