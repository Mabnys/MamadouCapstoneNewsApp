package com.mamadou.newsapp
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.view.children
import com.mamadou.newsapp.databinding.ActivityMainBinding
import com.mamadou.newsapp.models.Article
import com.mamadou.newsapp.models.Source

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val  article = arrayListOf(
        Article("",
            "Futures ebb as Powell's speech nears - Reuters",
            "U.S. stock index futures slipped on Friday as investors worried about hawkish signals from Federal Reserve Chair Jerome Powell at the annual Jackson Hole symposium amid fears of slowing economic growth.",
            "https://www.reuters.com/markets/europe/futures-ebb-powells-speech-nears-2022-08-26/",
            "https://www.reuters.com/resizer/IqS0WNIjHDHAF8xN8gg1AkrhcAY=/1200x628/smart/filters:quality(80)/cloudfront-us-east-2.images.arcpublishing.com/reuters/3KJYECNYSBOUXE4E2FFPLOSLUM.jpg",
            "2022-08-26T11:35:00Z",
            "Aug 26 (Reuters) - U.S. stock index futures slipped on Friday as investors worried about hawkish signals from Federal Reserve Chair Jerome Powell at the annual Jackson Hole symposium amid fears of sl… [+2556 chars]",
            ),
        Article("",
            "Breakingviews - China-U.S. audit deal success is a long way off - Reuters",
            "Sometimes the hardest yards are put in agreeing deal principles; other times, the real work only starts after that point. American and Chinese officials are nearing an accord allowing U.S. inspections in Hong Kong of mainland audit papers, the Wall Street Jou…",
            "https://www.reuters.com/breakingviews/china-us-audit-deal-success-is-long-way-off-2022-08-26/",
        "https://www.reuters.com/resizer/Z10kPQ9m23PxivSgHwWaRRMoOYU=/1200x628/smart/filters:quality(80)/cloudfront-us-east-2.images.arcpublishing.com/reuters/VI6DJLGGCJJRNKWAQQNCEUBENA.jpg",
            "2022-08-26T03:55:00Z",
        "HONG KONG, Aug 26 (Reuters Breakingviews) - Sometimes the hardest yards are put in agreeing deal principles; other times, the real work only starts after that point. American and Chinese officials ar… [+3364 chars]",
            ),
        Article("aweinman@businessinsider.com (Aaron Weinman)",
            "Doctored bank statements and a decade-long graft. This banker has been accused of stealing \$50 million in a lawsuit that claims the money went toward hotels in New York, Miami, and land in the Catskills.",
            "The Gupta family from India who control shipping company Astra Global have accused Ganesh Narayan in a lawsuit of doctoring bank statements and concealing a long-running grift that robbed the family of millions of dollars.",
            "https://www.businessinsider.com/wealth-lawsuit-astra-global-graft-ganesh-narayan-2022-8",
            "https://i.insider.com/6307f69e830ed800183fd9fb?width=1200&format=jpeg",
            "2022-08-26T11:35:00Z",
            "Hi. Aaron Weinman here. Today's top story digs into a wealth-management banker who is being accused of stealing \$50 million and investing that money in luxury hotels.\\r\\nOver some 10 years, Dubai-based… [+4947 chars]",
            ),
        Article("Alberto Martín",
            "El iPhone 14 morado se deja ver en un vídeo real",
            "El iPhone 14 está a la vuelta de la esquina. Apple ya ha enviado las invitaciones para su evento de presentación y, a diferencia de otros años, los rumores nos han traído pocas imágenes filtradas de cómo podrá ser el nuevo terminal de Apple. No obstante, con …",
            "https://hipertextual.com/2022/08/iphone-14-morado-filtrado",
            "https://imgs.hipertextual.com/wp-content/uploads/2022/07/iPhone-14-Pro-Purple-Side-by-Side-Black-scaled.jpg",
            "2022-08-26T10:19:47Z",
            "El iPhone 14 está a la vuelta de la esquina. Apple ya ha enviado las invitaciones para su evento de presentación y, a diferencia de otros años, los rumoresnos han traído pocas imágenes filtradas de c… [+1852 chars]",
            ),
        Article("news@appleinsider.com (William Gallagher)",
            "Elon Musk and T-Mobile announce universal cell coverage via satellite",
            "SpaceX and T-Mobile are partnering on a long-term plan to bring cellphone coverage to even the most remote US locations, currently unreachable by traditional cell signals.Elon MuskAccording to the companies, over half a million square miles of US land — plus …",
            "https://appleinsider.com/articles/22/08/26/elon-musk-and-t-mobile-announce-universal-cell-coverage-via-satellite",
            "https://photos5.appleinsider.com/gallery/48232-94214-Musk-Tesla-xl.jpg",
            "2022-08-26T10:31:44Z",
            "AppleInsider is supported by its audience and may earn commission as an Amazon Associate and affiliate partner on qualifying purchases. These affiliate partnerships do not influence our editorial con… [+2823 chars]",
            ),
    )

    private val source = arrayListOf(
        Source("cnn", "CNN News"),
        Source("africa","AFRICA News"),
        Source("","FOX News"),
        Source("","TNT News"),
        Source("","NY1 News")
    )

    private val newsArticle = mapOf(
        Pair("cnn", "CNN News") to arrayOf(
        "",
        "Futures ebb as Powell's speech nears - Reuters",
        "U.S. stock index futures slipped on Friday as investors worried about hawkish signals from Federal Reserve Chair Jerome Powell at the annual Jackson Hole symposium amid fears of slowing economic growth.",
        "https://www.reuters.com/markets/europe/futures-ebb-powells-speech-nears-2022-08-26/",
        "https://www.reuters.com/resizer/IqS0WNIjHDHAF8xN8gg1AkrhcAY=/1200x628/smart/filters:quality(80)/cloudfront-us-east-2.images.arcpublishing.com/reuters/3KJYECNYSBOUXE4E2FFPLOSLUM.jpg",
        "2022-08-26T11:35:00Z",
        "Aug 26 (Reuters) - U.S. stock index futures slipped on Friday as investors worried about hawkish signals from Federal Reserve Chair Jerome Powell at the annual Jackson Hole symposium amid fears of sl… [+2556 chars]",
        ),
    Pair("africa","AFRICA News") to arrayOf(
    "",
    "Breakingviews - China-U.S. audit deal success is a long way off - Reuters",
    "Sometimes the hardest yards are put in agreeing deal principles; other times, the real work only starts after that point. American and Chinese officials are nearing an accord allowing U.S. inspections in Hong Kong of mainland audit papers, the Wall Street Jou…",
    "https://www.reuters.com/breakingviews/china-us-audit-deal-success-is-long-way-off-2022-08-26/",
    "https://www.reuters.com/resizer/Z10kPQ9m23PxivSgHwWaRRMoOYU=/1200x628/smart/filters:quality(80)/cloudfront-us-east-2.images.arcpublishing.com/reuters/VI6DJLGGCJJRNKWAQQNCEUBENA.jpg",
    "2022-08-26T03:55:00Z",
    "HONG KONG, Aug 26 (Reuters Breakingviews) - Sometimes the hardest yards are put in agreeing deal principles; other times, the real work only starts after that point. American and Chinese officials ar… [+3364 chars]",
    ),
        Pair("","FOX News") to arrayOf(
            "aweinman@businessinsider.com (Aaron Weinman)",
            "Doctored bank statements and a decade-long graft. This banker has been accused of stealing \$50 million in a lawsuit that claims the money went toward hotels in New York, Miami, and land in the Catskills.",
            "The Gupta family from India who control shipping company Astra Global have accused Ganesh Narayan in a lawsuit of doctoring bank statements and concealing a long-running grift that robbed the family of millions of dollars.",
            "https://www.businessinsider.com/wealth-lawsuit-astra-global-graft-ganesh-narayan-2022-8",
            "https://i.insider.com/6307f69e830ed800183fd9fb?width=1200&format=jpeg",
            "2022-08-26T11:35:00Z",
            "Hi. Aaron Weinman here. Today's top story digs into a wealth-management banker who is being accused of stealing \$50 million and investing that money in luxury hotels.\\r\\nOver some 10 years, Dubai-based… [+4947 chars]",
            ),
        Pair("","TNT News") to arrayOf(
            "Alberto Martín",
            "El iPhone 14 morado se deja ver en un vídeo real",
            "El iPhone 14 está a la vuelta de la esquina. Apple ya ha enviado las invitaciones para su evento de presentación y, a diferencia de otros años, los rumores nos han traído pocas imágenes filtradas de cómo podrá ser el nuevo terminal de Apple. No obstante, con …",
            "https://hipertextual.com/2022/08/iphone-14-morado-filtrado",
            "https://imgs.hipertextual.com/wp-content/uploads/2022/07/iPhone-14-Pro-Purple-Side-by-Side-Black-scaled.jpg",
            "2022-08-26T10:19:47Z",
            "El iPhone 14 está a la vuelta de la esquina. Apple ya ha enviado las invitaciones para su evento de presentación y, a diferencia de otros años, los rumoresnos han traído pocas imágenes filtradas de c… [+1852 chars]",


            ),
        Pair("","NY1 News") to arrayOf(
            "news@appleinsider.com (William Gallagher)",
            "Elon Musk and T-Mobile announce universal cell coverage via satellite",
            "SpaceX and T-Mobile are partnering on a long-term plan to bring cellphone coverage to even the most remote US locations, currently unreachable by traditional cell signals.Elon MuskAccording to the companies, over half a million square miles of US land — plus …",
            "https://appleinsider.com/articles/22/08/26/elon-musk-and-t-mobile-announce-universal-cell-coverage-via-satellite",
            "https://photos5.appleinsider.com/gallery/48232-94214-Musk-Tesla-xl.jpg",
            "2022-08-26T10:31:44Z",
            "AppleInsider is supported by its audience and may earn commission as an Amazon Associate and affiliate partner on qualifying purchases. These affiliate partnerships do not influence our editorial con… [+2823 chars]",
            )
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setNewsListText()
    }

    @SuppressLint("SetTextI18n")
    private fun setNewsListText() {

        newsArticle.forEach { _ ->
            println(newsArticle)
        }

        val view = binding.root
        setContentView(view)

        val mainGroup = binding.mainGroup
        var count = 0

        for (i in mainGroup.children) {
            if (i is TextView) {
                var content = "\nsource"

                if(source[count].id != null)
                {
                    content +=  "\n"+"    id:" + source[count].id
                }
                content +=  "\n"+"    name:" + source[count].name

                if (article[count].author != null) {
                    content +=  "\n "+ "author: \n" + article[count].author
                }
                content += "\n "+ "title: \n" +article[count].title
                content += "\n " + "description:\n" + article[count].description
                content += "\n " + "url: \n" + article[count].url
                content += "\n " + "urlToImage: \n" + article[count].urlToImage
                content += "\n " +"publishedAt: \n" + article[count].publishedAt
                if (article[count].content != null) {
                    content += "\n " +"content:\n" + article[count].content
                }
                (content+"\n").apply { i.text = this }
                count++
            }
        }
    }
}
