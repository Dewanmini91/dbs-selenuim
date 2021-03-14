package dbs.project.definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import dbs.project.steps.CardCompareSteps;

public class CardCompareStepsDefinitions {


    private CardCompareSteps cardCompareSteps;

    public CardCompareStepsDefinitions() {
        this.cardCompareSteps = new CardCompareSteps();
    }

    @Given("A user navigates to DBS Home page")
    public void aUserNavigatesToDBSHomePage() {
        this.cardCompareSteps.goToDBSPage();
    }


    @Then("page contain link as {string}")
    public void pageContainLinkAs(String elementName) {
        String selectedHeader = this.cardCompareSteps.findSelectedLink(elementName);

    }

    @And("select card container")
    public void selectCardContainer() {

       this.cardCompareSteps.selectCardContainer();
    }

    @Then("compare credit card values")
    public void compareCreditCardValues() {

        this.cardCompareSteps.compareCreditCardValues();
    }

    @Then("verify both has same {string}")
    public void verifyBothHasSame(String cardProperty) {

        this.cardCompareSteps.verifyBothHasSame(cardProperty);
    }

    @Then("verify card has values")
    public void verifyCardHasValues() {
        this.cardCompareSteps.verifyCardHasValues();
    }
}

