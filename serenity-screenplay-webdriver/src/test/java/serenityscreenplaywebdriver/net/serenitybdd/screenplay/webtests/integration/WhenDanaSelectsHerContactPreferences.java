package serenityscreenplaywebdriver.net.serenitybdd.screenplay.webtests.integration;

import net.serenitybdd.junit.runners.SerenityRunner;
import serenityscreenplay.net.serenitybdd.screenplay.Actor;
import serenityscreenplaywebdriver.net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import serenityscreenplaywebdriver.net.serenitybdd.screenplay.webtests.questions.ContactPreferences;
import serenityscreenplaywebdriver.net.serenitybdd.screenplay.webtests.questions.CountryQuestion;
import serenityscreenplaywebdriver.net.serenitybdd.screenplay.webtests.tasks.*;
import serenitycore.net.thucydides.core.annotations.Managed;
import serenitycore.net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static serenityscreenplay.net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItems;

@RunWith(SerenityRunner.class)
public class WhenDanaSelectsHerContactPreferences {

    @Managed(driver = "htmlunit")
    WebDriver firstBrowser;

    @Test
    public void danaCanSelectAdditionalContactPreferences() {

        Actor dana = new Actor("Dana");
        dana.can(BrowseTheWeb.with(firstBrowser));

        givenThat(dana).has(openedTheApplication);

        when(dana).attemptsTo(viewedHerProfile);
        then(dana).should(seeThat(ContactPreferences.nowSelected(), hasItems("Email", "Post")));

        and(dana).attemptsTo(SelectContactPreference.withText("SMS Message"));

        then(dana).should(seeThat(ContactPreferences.nowSelected(), hasItems("Email", "Post", "SMS Message")));
    }

    @Test
    public void danaCanDeselectExistingContactPreferences() {

        Actor dana = new Actor("Dana");
        dana.can(BrowseTheWeb.with(firstBrowser));

        givenThat(dana).has(openedTheApplication);

        when(dana).attemptsTo(viewedHerProfile);
        then(dana).should(seeThat(ContactPreferences.nowSelected(), hasItems("Email", "Post")));

        when(dana).attemptsTo(DeselectContactPreference.withText("Email"));
        then(dana).should(seeThat(ContactPreferences.nowSelected(), hasItems("Post")));
    }

    @Test
    public void danaCanDeselectAllContactPreferences() {

        Actor dana = new Actor("Dana");
        dana.can(BrowseTheWeb.with(firstBrowser));

        givenThat(dana).has(openedTheApplication);

        when(dana).attemptsTo(viewedHerProfile);
        then(dana).should(seeThat(ContactPreferences.nowSelected(), hasItems("Email", "Post")));

        when(dana).attemptsTo(DeselectAll.contactPreferences());
        then(dana).should(seeThat(ContactPreferences.nowSelected(), empty()));
    }
    
    @Test
    public void danaCanSelectHerCountry() {

        Actor dana = new Actor("Dana");
        dana.can(BrowseTheWeb.with(firstBrowser));

        givenThat(dana).has(openedTheApplication);

        when(dana).attemptsTo(viewedHerProfile);
        then(dana).should(seeThat(CountryQuestion.nowSelected(), hasItems("United Kingdom")));

        and(dana).attemptsTo(SelectCountry.withCountryCode("ES"));

        then(dana).should(seeThat(CountryQuestion.nowSelected(), hasItems("Spain")));
    }

    @Steps
    OpenTheApplication openedTheApplication;

    @Steps
    LegacyViewMyProfile viewHerOldProfile;

    @Steps
    ViewMyProfile viewedHerProfile;

}