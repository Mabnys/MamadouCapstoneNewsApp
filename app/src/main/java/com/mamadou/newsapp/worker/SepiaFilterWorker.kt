package com.mamadou.newsapp.worker

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import java.io.FileOutputStream

class SepiaFilterWorker(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {
    override fun doWork(): Result {
        val imagePath = inputData.getString(INPUT_IMAGE_PATH) ?: return Result.failure()
        val bitmap = BitmapFactory.decodeFile(imagePath)
        val newBitmap = ImageUtils.applySepiaFilter(bitmap)

        val outputStream = FileOutputStream(imagePath)
        outputStream.use { output ->
            newBitmap.compress(Bitmap.CompressFormat.PNG, 100, output)

            output.flush()
        }

        val output = workDataOf(INPUT_IMAGE_PATH to imagePath)
        return Result.success(output)
    }

    companion object {
        const val INPUT_IMAGE_PATH = "image_path"
    }

}