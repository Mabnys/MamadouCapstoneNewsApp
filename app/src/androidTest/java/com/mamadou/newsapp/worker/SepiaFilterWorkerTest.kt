package com.mamadou.newsapp.worker

import android.content.Context
import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkManager
import androidx.work.impl.utils.SynchronousExecutor
import androidx.work.testing.TestListenableWorkerBuilder
import androidx.work.testing.WorkManagerTestInitHelper
import androidx.work.workDataOf
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SepiaFilterWorkerTest {

    private val IMAGE_PATH =
        "/data/user/0/com.mamadou.newsapp/cache/image_manager_disk_cache/1612256f6995b6023d59d4706e0953b98bd7aa3c02fc48ff2ef82409aaf85f05.0"
    private lateinit var workManager: WorkManager
    private lateinit var context: Context
    private lateinit var configuration: Configuration

    @Before
    fun setup() {
        configuration = Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setExecutor(SynchronousExecutor())
            .build()

        context = InstrumentationRegistry.getInstrumentation().targetContext
        WorkManagerTestInitHelper.initializeTestWorkManager(context, configuration)
        workManager = WorkManager.getInstance(context)
    }

    @Test
    fun testSepiaFilterWork() {
        val worker = TestListenableWorkerBuilder<SepiaFilterWorker>(
            context = context,
            inputData = workDataOf("image_path" to IMAGE_PATH)
        ).build()

        val result = worker.startWork().get()
        Assert.assertEquals(ListenableWorker.Result.success().javaClass, result.javaClass)
    }

}
