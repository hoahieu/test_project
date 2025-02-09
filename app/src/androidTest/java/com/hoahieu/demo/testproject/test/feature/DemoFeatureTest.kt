package com.hoahieu.demo.testproject.test.feature

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hoahieu.demo.testproject.R
import com.hoahieu.demo.testproject.data.database.CryptoDao
import com.hoahieu.demo.testproject.data.database.FiatDao
import com.hoahieu.demo.testproject.ui.DemoActivity
import com.hoahieu.demo.testproject.ui.currencylist.CurrencyAdapter
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.java.KoinJavaComponent.getKoin

@RunWith(AndroidJUnit4::class)
class DemoFeatureTest {
    @get:Rule
    var activityScenarioRule = activityScenarioRule<DemoActivity>()
    private val cryptoDao: CryptoDao = getKoin().get()
    private val fiatDao: FiatDao = getKoin().get()

    @Test
    fun clearAndGenerate() {
        onView(withId(R.id.demo_clear)).perform(click())
        verifyDbIsEmpty()
        onView(withId(R.id.demo_generate)).perform(click())
        verifyDbIsGenerated()
    }

    private fun verifyDbIsEmpty() {
        runBlocking {
            assertTrue(cryptoDao.getAll().isEmpty())
            assertTrue(fiatDao.getAll().isEmpty())
        }
    }

    private fun verifyDbIsGenerated() {
        runBlocking {
            assertTrue(cryptoDao.getAll().isNotEmpty())
            assertTrue(fiatDao.getAll().isNotEmpty())
        }
    }

    @Test
    fun openCurrencyListScreenWithFiats() {
        onView(withId(R.id.demo_generate)).perform(click())
        onView(withId(R.id.demo_open_fiats)).perform(click())
        onView(withId(R.id.currency_list_empty_icon)).check { view, _ ->
            assertEquals(View.GONE, view.visibility)
        }
        onView(withId(R.id.currency_list_view)).check { view, _ ->
            assertTrue(
                ((view as? RecyclerView)?.adapter as? CurrencyAdapter)?.items.orEmpty().isNotEmpty()
            )
        }
    }

    @Test
    fun openCurrencyListScreenWithCryptos() {
        onView(withId(R.id.demo_generate)).perform(click())
        onView(withId(R.id.demo_open_cryptos)).perform(click())
        onView(withId(R.id.currency_list_empty_icon)).check { view, _ ->
            assertEquals(View.GONE, view.visibility)
        }
        onView(withId(R.id.currency_list_view)).check { view, _ ->
            assertTrue(
                ((view as? RecyclerView)?.adapter as? CurrencyAdapter)?.items.orEmpty().isNotEmpty()
            )
        }
    }

    @Test
    fun openCurrencyListScreenWithAllCurrencies() {
        onView(withId(R.id.demo_generate)).perform(click())
        onView(withId(R.id.demo_open_all)).perform(click())
        onView(withId(R.id.currency_list_empty_icon)).check { view, _ ->
            assertEquals(View.GONE, view.visibility)
        }
        onView(withId(R.id.currency_list_view)).check { view, _ ->
            assertTrue(
                ((view as? RecyclerView)?.adapter as? CurrencyAdapter)?.items.orEmpty().isNotEmpty()
            )
        }
    }

    @Test
    fun openEmptyCurrencyListScreenWithFiats() {
        onView(withId(R.id.demo_clear)).perform(click())
        onView(withId(R.id.demo_open_fiats)).perform(click())
        onView(withId(R.id.currency_list_empty_icon)).check { view, _ ->
            assertEquals(View.VISIBLE, view.visibility)
        }
        onView(withId(R.id.currency_list_view)).check { view, _ ->
            assertTrue(
                ((view as? RecyclerView)?.adapter as? CurrencyAdapter)?.items.orEmpty().isEmpty()
            )
        }
    }

    @Test
    fun openEmptyCurrencyListScreenWithCryptos() {
        onView(withId(R.id.demo_clear)).perform(click())
        onView(withId(R.id.demo_open_cryptos)).perform(click())
        onView(withId(R.id.currency_list_empty_icon)).check { view, _ ->
            assertEquals(View.VISIBLE, view.visibility)
        }
        onView(withId(R.id.currency_list_view)).check { view, _ ->
            assertTrue(
                ((view as? RecyclerView)?.adapter as? CurrencyAdapter)?.items.orEmpty().isEmpty()
            )
        }
    }

    @Test
    fun openEmptyCurrencyListScreenWithAllCurrencies() {
        onView(withId(R.id.demo_clear)).perform(click())
        onView(withId(R.id.demo_open_all)).perform(click())
        onView(withId(R.id.currency_list_empty_icon)).check { view, _ ->
            assertEquals(View.VISIBLE, view.visibility)
        }
        onView(withId(R.id.currency_list_view)).check { view, _ ->
            assertTrue(
                ((view as? RecyclerView)?.adapter as? CurrencyAdapter)?.items.orEmpty().isEmpty()
            )
        }
    }

}