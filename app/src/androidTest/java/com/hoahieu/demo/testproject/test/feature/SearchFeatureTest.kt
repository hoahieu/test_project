package com.hoahieu.demo.testproject.test.feature

import androidx.appcompat.widget.SearchView.SearchAutoComplete
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hoahieu.demo.testproject.R
import com.hoahieu.demo.testproject.ui.DemoActivity
import com.hoahieu.demo.testproject.ui.currencylist.CurrencyAdapter
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchFeatureTest {
    @get:Rule
    var activityScenarioRule = activityScenarioRule<DemoActivity>()

    @Test
    fun searchWithDifferentQueries() {
        onView(withId(R.id.demo_generate)).perform(click())
        onView(withId(R.id.demo_open_all)).perform(click())
        onView(withId(R.id.currency_search)).perform(click())

        checkListCountWithQuery(" Coin", 2)
        checkListCountWithQuery("Eth", 2)
        checkListCountWithQuery("eTC", 1)
        checkListCountWithQuery("", 21)
    }

    private val searchEditTextMatcher = withClassName(
        CoreMatchers.containsString(SearchAutoComplete::class.simpleName)
    )

    private fun checkListCountWithQuery(query: String, expectedCount: Int) {
        onView(searchEditTextMatcher).perform(
            clearText()
        )
        onView(searchEditTextMatcher).perform(
            typeText(query)
        )
        onView(withId(R.id.currency_list_view)).check { view, _ ->
            val actualCount =
                ((view as? RecyclerView)?.adapter as? CurrencyAdapter)?.items.orEmpty().size
            Assert.assertEquals(expectedCount, actualCount)
        }
    }
}