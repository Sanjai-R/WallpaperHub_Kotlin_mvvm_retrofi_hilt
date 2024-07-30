package com.example.wallpaper

import android.widget.Button
import android.widget.EditText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.espresso.Espresso
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import com.example.wallpaper.view.AutomationIDs.ONBOARD_TEST_TAG
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginTest {

    private lateinit var device: UiDevice
    private lateinit var uiSelector: UiSelector

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        uiSelector = UiSelector()
    }

    @Test
    fun loginTest() {
        composeTestRule.onNodeWithTag(ONBOARD_TEST_TAG).assertExists().performClick()

    }
}