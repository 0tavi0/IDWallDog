package com.jonasvieira.iddog.Presentations.Login;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.jonasvieira.iddog.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void stage1_temQueExibirViews(){
        onView(withId(R.id.txtIdDog)).check(matches(isDisplayed()));
        onView(withId(R.id.txtInfoLogin)).check(matches(isDisplayed()));
        onView(withId(R.id.textInputLogin)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextEmail)).check(matches(isDisplayed()));
        onView(withId(R.id.buttonLogin)).check(matches(isDisplayed()));
    }

    @Test
    public void stage2_temQueExibirErroEmailVazio(){
        onView(withId(R.id.editTextEmail)).perform(typeText(""));
        onView(withId(R.id.buttonLogin)).perform(click());
        onView(withId(R.id.textinput_error)).check(matches(withText("Campo obrigatório, insira um e-mail válido")));
    }

    @Test
    public void stage3_temQueExibirErroEmailInvalido(){
        onView(withId(R.id.editTextEmail)).perform(typeText("teste"));
        onView(withId(R.id.buttonLogin)).perform(click());
        onView(withId(R.id.textinput_error)).check(matches(withText("E-mail invalido, verifique o endereço digitado e tente novamente")));
    }

    @Test
    public void stage4_temQueExibirLoadingEOcultarBotao(){
        onView(withId(R.id.editTextEmail)).perform(typeText("jonas_vcom@teste.com"));
        onView(withId(R.id.buttonLogin)).perform(click());
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()));
        onView(withId(R.id.buttonLogin)).check(matches(not(isDisplayed())));
    }
}
