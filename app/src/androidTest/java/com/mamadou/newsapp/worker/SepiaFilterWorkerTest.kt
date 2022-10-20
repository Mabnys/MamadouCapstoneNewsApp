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
    "/data/user/0/com.mamadou.newsapp/cache/image_manager_disk_cache/fa7539128345411c0bd8d3f66ffe80bf19eeccc3cccead1de80b04a97587cca0.0"
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
