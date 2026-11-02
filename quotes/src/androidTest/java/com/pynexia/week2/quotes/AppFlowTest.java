package com.pynexia.week2.quotes;
import android.content.Intent;
import androidx.test.core.app.ActivityScenario;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
public class AppFlowTest {

    @Test public void changesRestoresAndSharesCurrentQuote() throws Exception {
        androidx.test.espresso.intent.Intents.init();
        try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
            String[] value = new String[1];
            scenario.onActivity(activity -> value[0] = ((android.widget.TextView)activity.findViewById(R.id.quote)).getText().toString());
            onView(withId(R.id.next)).perform(scrollTo(), click());
            onView(withId(R.id.quote)).check(matches(org.hamcrest.Matchers.not(withText(value[0]))));
            scenario.onActivity(activity -> value[0] = ((android.widget.TextView)activity.findViewById(R.id.quote)).getText().toString());
            scenario.recreate(); onView(withId(R.id.quote)).check(matches(withText(value[0])));
            androidx.test.espresso.intent.Intents.intending(androidx.test.espresso.intent.matcher.IntentMatchers.hasAction(Intent.ACTION_CHOOSER)).respondWith(new android.app.Instrumentation.ActivityResult(0, null));
            onView(withId(R.id.share)).perform(scrollTo(), click());
            Intent chooser = androidx.test.espresso.intent.Intents.getIntents().stream().filter(i -> Intent.ACTION_CHOOSER.equals(i.getAction())).findFirst().orElseThrow();
            Intent send = chooser.getParcelableExtra(Intent.EXTRA_INTENT);
            assertNotNull(send); assertEquals(Intent.ACTION_SEND, send.getAction()); assertEquals("text/plain", send.getType());
            assertEquals(value[0] + "\n- Pynexia learning collection", send.getStringExtra(Intent.EXTRA_TEXT));
            onView(withText(R.string.eyebrow)).perform(scrollTo());
            InstrumentationRegistry.getInstrumentation().waitForIdleSync();
            Thread.sleep(400);
            android.graphics.Bitmap bitmap = InstrumentationRegistry.getInstrumentation().getUiAutomation().takeScreenshot();
            java.io.File folder = new java.io.File(InstrumentationRegistry.getInstrumentation().getTargetContext().getExternalFilesDir(null), "evidence");
            if (!folder.exists() && !folder.mkdirs()) throw new java.io.IOException("Cannot create evidence directory");
            try (java.io.FileOutputStream output = new java.io.FileOutputStream(new java.io.File(folder, "quotes-main.png"))) { assertTrue(bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, output)); }
            finally { bitmap.recycle(); }
        } finally { androidx.test.espresso.intent.Intents.release(); }
    }
}