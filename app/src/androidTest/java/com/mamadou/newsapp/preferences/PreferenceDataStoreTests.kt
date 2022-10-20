package com.mamadou.newsapp.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

private const val DATASTORE_NAME_TEST = "datastore_test"

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class PreferenceDataStoreTests {
    private val testContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
    private val testCoroutineScope =
        TestCoroutineScope(TestCoroutineDispatcher() + Job())

    private val dataStoreTest: DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            scope = testCoroutineScope,
            produceFile =
            { testContext.preferencesDataStoreFile(DATASTORE_NAME_TEST) }
        )

    private val sut: PreferencesDataStore =
        PreferencesDataStoreImpl(dataStoreTest)

    @Test
    fun isDownloadOverWifiOnlyBooleanValue() = runTest {
        val isDownloadOverWifiOnly = sut.isDownloadOverWifiOnly().first()
        Assert.assertFalse(isDownloadOverWifiOnly)
    }

    @Test
    fun toggleDownloadOverWifiOnlyReturnValue() = runTest {
        val beforeDownloadOverWifiOnly = sut.isDownloadOverWifiOnly().first()
        sut.toggleDownloadOverWifiOnly()
        val afterDownloadOverWifiOnly = sut.isDownloadOverWifiOnly().first()
        Assert.assertNotEquals(beforeDownloadOverWifiOnly, afterDownloadOverWifiOnly)
    }

    @After
    fun cleanUp() {
        runTest {
            File(
                ApplicationProvider.getApplicationContext<Context>().filesDir,
                "datastore"
            ).deleteRecursively()
        }
        testCoroutineScope.cancel()
    }
}