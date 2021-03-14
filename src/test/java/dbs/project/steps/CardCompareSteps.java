package dbs.project.steps;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.*;
import java.util.concurrent.TimeUnit;


public class CardCompareSteps extends CommonSteps {

    private static final String PAGE_URL = "https://www.dbs.com.sg/personal/default.page";

    Map<String, Map> cardProperties =null;

    public CardCompareSteps() {
        PageFactory.initElements(driver, this);
    }

    public void goToDBSPage() {
        driver.get(PAGE_URL);
        setup.forLoading(5);
    }

    public String findSelectedLink(String elementName) {

        List<WebElement> elements = driver.findElements(By.tagName("a"));

        for (int i = 0; i < elements.size(); i++) {

            String linkText = elements.get(i).getText();

            if (linkText.equals(elementName)){
                elements.get(i).click();
                break;
            }
        }
        return "";
    }

    public void selectCardContainer() {


        List<WebElement> temp = driver.findElements(By.className("cardContainer"));

        WebElement firstCreditCard = temp.get(0);
        WebElement secondCreditCard = temp.get(1);

        WebElement firstCreditCardFooter = firstCreditCard.findElement(By.className("cardcontainer-footer"));
        WebElement secondCreditCardFooter = secondCreditCard.findElement(By.className("cardcontainer-footer"));

        WebElement checkBoxDiv = firstCreditCardFooter.findElement(By.cssSelector("div[class='compare-div mTop-16 checkbox']"));
        checkBoxDiv.click();

        WebElement checkBoxDiv2 = secondCreditCardFooter.findElement(By.cssSelector("div[class='compare-div mTop-16 checkbox']"));
        checkBoxDiv2.click();


        WebElement  btn = driver.findElement(By.id("cardCompareBtn"));
        btn.click();


    }


    public void compareCreditCardValues() {


        WebElement  card1 = driver.findElement(By.id("card0"));
        System.out.println(card1.getText());
        WebElement  card2 = driver.findElement(By.id("card1"));
        System.out.println(card2.getText());


        WebElement card1SectionSeparator = card1.findElement(By.cssSelector("div[class='section-seperator']"));
        System.out.println(card1SectionSeparator.getText());
        WebElement card2SectionSeparator = card2.findElement(By.cssSelector("div[class='section-seperator']"));
        System.out.println(card2SectionSeparator.getText());


       String card1Values = card1SectionSeparator.getText();
       String card1values[] = card1Values.split("\\r?\\n");

       String card2Values = card2SectionSeparator.getText();
       String card2values[] = card2Values.split("\\r?\\n");

        cardProperties = new HashMap<String ,Map>();


        int j =0;
        for ( int i =0 ; i < card1values.length ; i= i+2,j++){

            Map<String, String> cards = new HashMap();
            cards.put("card1",card1values[i+1]);
            cards.put("card2",card2values[j]);

            String header = card1values[i];
            cardProperties.put(header,cards);

        }


    }

    public void verifyBothHasSame(String property) {

        if (cardProperties.containsKey(property)){

            Map existing = (Map) cardProperties.get(property);

            String card1Prop = existing.get("card1").toString();
            String card2Prop = existing.get("card2").toString();

            Assert.assertEquals(card1Prop.trim(),card2Prop.trim());

        }
    }

    public void verifyCardHasValues() {

        Assert.assertNotNull(cardProperties);
    }
}
