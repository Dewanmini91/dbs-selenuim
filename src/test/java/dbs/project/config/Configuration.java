package dbs.project.config;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Configuration {

    public static WebDriver driver;

    public Configuration(){}

    public Configuration(WebDriver driver) {
        this.driver = driver;
    }

    @Before
    public void setWebDriver() throws Exception {

        System.setProperty("webdriver.chrome.driver", "chromedriver");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("['start-maximized']");
        driver = new ChromeDriver(chromeOptions);

    }

    @After
    public void quitDriver(Scenario scenario){
        if(scenario.isFailed()){
            saveScreenshotsForScenario(scenario);
        }
    }

    private void saveScreenshotsForScenario(final Scenario scenario) {

        final byte[] screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
    }


    private void waitUntilCondition(ExpectedCondition condition, String timeoutMessage, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage(timeoutMessage);
        wait.until(condition);
    }

    public void forLoading(int timeout){
        ExpectedCondition<Object> condition = ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";");
        String timeoutMessage = "Page didn't load after " + Integer.toString(timeout) + " seconds.";
        waitUntilCondition(condition, timeoutMessage, timeout);
    }

}
