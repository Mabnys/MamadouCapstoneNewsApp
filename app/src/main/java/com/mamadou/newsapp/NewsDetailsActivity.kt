package com.mamadou.newsapp

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.bumptech.glide.Glide
import com.mamadou.newsapp.databinding.ActivityNewsDetailsBinding
import com.mamadou.newsapp.models.Article
import com.mamadou.newsapp.worker.DownloadWorker
import com.mamadou.newsapp.worker.FileClearWorker
import kotlinx.coroutines.*

class NewsDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =  ActivityNewsDetailsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val article =  intent.extras?.getParcelable<Article>(INTENT_EXTRA_ARTICLE)

        if (article != null) {
            binding.articleTitleTextView.text = article.title
            Glide.with(this)
                .load(article.urlToImage)
                .into(binding.articleUrlImageView)

            //downloadImage() call
            downloadImage()

            binding.articleAuthorTextView.text = article.author
            binding.articleDescriptionTextView.text = article.description
            binding.articleUrlTextView.text = article.url
            binding.articlePublishAtTextView.text = article.publishedAt
            binding.articleSourceNameTextView.text = article.source.name
        }
    }


    private fun downloadImage() {
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .setRequiredNetworkType(NetworkType.NOT_ROAMING)
            .build()

        val clearFilesWorker = OneTimeWorkRequestBuilder<FileClearWorker>()
            .build()

        val downloadRequest = OneTimeWorkRequestBuilder<DownloadWorker>()
            .setConstraints(constraints)
            .build()

        val workManager = WorkManager.getInstance(this)
        workManager.beginWith(clearFilesWorker)
            .then(downloadRequest)
            .enqueue()

        workManager.getWorkInfoByIdLiveData(downloadRequest.id).observe(this, Observer { info ->
            if (info.state.isFinished) {
                val imagePath = info.outputData.getString("image_path")

                if (!imagePath.isNullOrEmpty()) {
                    displayImage(imagePath)
                }
            }
        })
    }

    private fun displayImage(imagePath: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val bitmap = loadImageFromFile(imagePath)

//            image.setImageBitmap(bitmap)
        }
    }

    private suspend fun loadImageFromFile(imagePath: String) = withContext(Dispatchers.IO) {
        BitmapFactory.decodeFile(imagePath)
    }


    companion object {
        const val INTENT_EXTRA_ARTICLE = "article"
    }
}