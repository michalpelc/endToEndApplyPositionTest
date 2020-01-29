package page;

import org.apache.log4j.Logger;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.annotation.PageUrl;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

import static org.apache.log4j.Logger.getLogger;

@PageUrl("https://www.worldremit.com/en/careers")
public class CareerWelcomePage extends FluentPage {

    private final static Logger log = getLogger(CareerWelcomePage.class);

    @Page
    CareerOpportunitiesSection careerOpportunitiesSection;

    @FindBy(css = "a.button-cta")
    private FluentWebElement seeAllOurRolesButton;

    public CareerOpportunitiesSection switchToOurOpportunitiesSection() {
        log.info("Switching to our opportunities section ... ");
        await().until(seeAllOurRolesButton).clickable();
        seeAllOurRolesButton.click();
        return careerOpportunitiesSection;
    }

}
