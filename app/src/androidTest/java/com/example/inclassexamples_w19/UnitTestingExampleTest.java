package com.example.inclassexamples_w19;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class UnitTestingExampleTest {

    @Rule
    public ActivityTestRule<UnitTestingExample> mActivityTestRule = new ActivityTestRule<>(UnitTestingExample.class);

    @Before
    public void setUPVariables()
    {
        Log.e("Calling Before", "setUPVariables()");

    }


    @Test
    public void myNewTest()
    {
       Button b1 = mActivityTestRule.getActivity().findViewById(R.id.button1);
       Assert.assertEquals(b1.getText().toString(), "Test 1" );
    }

    @Test
    public void unitTestingExampleTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.button1)));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.button2)));
        appCompatButton2.perform(click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.resultsWindow)));
        editText.check(matches(withText("You clicked button 2")));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.resultsWindow)));
        editText2.check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
