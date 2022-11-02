package com.mamadou.newsapp

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.work.*
import com.mamadou.newsapp.databinding.ActivityNewsDetailsBinding
import com.mamadou.newsapp.models.Article
import com.mamadou.newsapp.worker.DownloadWorker
import com.mamadou.newsapp.worker.FileClearWorker
import com.mamadou.newsapp.worker.SepiaFilterWorker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
@AndroidEntryPoint
class NewsDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityNewsDetailsBinding
    private val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       binding =  ActivityNewsDetailsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val article =  intent.extras?.getParcelable<Article>(INTENT_EXTRA_ARTICLE)

        if (article != null) {
            binding.articleTitleTextView.text = article.title

            article.urlToImage?.let { downloadImage(it) }

            binding.articleAuthorTextView.text = article.author
            binding.articleDescriptionTextView.text = article.description
            binding.articleUrlTextView.text = article.url
            binding.articlePublishAtTextView.text = article.publishedAt
            binding.articleSourceNameTextView.text = article.source.name

            overridePendingTransition(R.anim.fade_out, R.anim.fade_in)
        } else {
            Log.e(TAG, "No news available for now!!!")
        }
    }

    private fun downloadImage(articleURL: String) {
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .setRequiredNetworkType(NetworkType.NOT_ROAMING)
            .build()

        val clearFilesWorker = OneTimeWorkRequestBuilder<FileClearWorker>()
            .build()

        val data = Data.Builder()
            .putString("articleUrl", articleURL)
            .build()

        val downloadRequest = OneTimeWorkRequestBuilder<DownloadWorker>()
            .setConstraints(constraints)
            .setInputData(data)
            .build()

        val sepiaFilterWorker = OneTimeWorkRequestBuilder<SepiaFilterWorker>()
            .setConstraints(constraints)
            .build()

        val workManager = WorkManager.getInstance(this)
        workManager.beginWith(clearFilesWorker)
            .then(downloadRequest)
            .then(sepiaFilterWorker)
            .enqueue()

        workManager.getWorkInfoByIdLiveData(sepiaFilterWorker.id).observe(this, Observer { info ->
            if (info.state.isFinished) {
                val imagePath = info.outputData.getString(INFO_IMAGE_PATH)

                if (!imagePath.isNullOrEmpty()) {
                    displayImage(imagePath)
                }
            }
        })
    }

    private fun displayImage(imagePath: String) {
        lifecycleScope.launch(Dispatchers.Main) {
            val bitmap = loadImageFromFile(imagePath)
            binding.articleUrlImageView.setImageBitmap(bitmap)
        }
    }

    private suspend fun loadImageFromFile(imagePath: String) = withContext(Dispatchers.IO) {
        BitmapFactory.decodeFile(imagePath)
    }


    companion object {
        const val INTENT_EXTRA_ARTICLE = "article"
        const val INFO_IMAGE_PATH = "image_path"
    }
}