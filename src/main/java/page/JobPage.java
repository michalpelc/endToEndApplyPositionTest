package page;

import org.apache.log4j.Logger;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.log4j.Logger.getLogger;

public class JobPage extends FluentPage {

    private final static Logger log = getLogger(JobPage.class);

    @FindBy(css = "div.form-item-grid")
    private FluentWebElement applicationForm;

    @FindBy(id = "First_Name")
    private FluentWebElement firstName;

    @FindBy(id = "Last_Name")
    private FluentWebElement lastName;

    @FindBy(id = "Email")
    private FluentWebElement email;

    @FindBy(css = "input.button-cta")
    private FluentWebElement applyButton;

    @FindBy(id = "message_for_Resume")
    private FluentWebElement missingResumeError;

    public JobPage fillApplicationFormWithRequiredData(boolean isResumeAttached) {
        log.info("Filling application form with cv attaching set to: " + isResumeAttached);
        await().until(applicationForm).displayed();
        firstName.clear().fill().withText(randomAlphabetic(10));
        lastName.clear().fill().withText(randomAlphabetic(10));
        email.clear().fill().withText("test@gmail.com");

        if (isResumeAttached) {
            log.info("CV Attached");
            //TODO code here cv attaching and verification
        } else {
            log.info("CV not attached");
        }
        return this;
    }

    public JobPage submitApplicationForm() {
        log.info("Submit application ...  ");
        await().explicitlyFor(5, SECONDS).until(applyButton.click());
        applyButton.click();
        return this;
    }

    public String getMissingResumeError() {
        await().until(missingResumeError).displayed();
        return missingResumeError.text();
    }
}