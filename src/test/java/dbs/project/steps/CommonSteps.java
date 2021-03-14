package dbs.project.steps;

import dbs.project.config.Configuration;
import org.openqa.selenium.WebDriver;

public class CommonSteps {

    protected WebDriver driver;
    protected Configuration setup;

    public CommonSteps() {
        this.driver = Configuration.driver;
        this.setup = new Configuration(this.driver);
    }


}
