package page;

import org.apache.log4j.Logger;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

import static java.lang.String.format;
import static org.apache.log4j.Logger.getLogger;
import static org.openqa.selenium.By.xpath;

public class CareerOpportunitiesSection extends FluentPage {

    private final static Logger log = getLogger(CareerOpportunitiesSection.class);

    @Page
    JobPage jobPage;

    @FindBy(id = "department-select")
    private FluentWebElement selectDepartmentLookup;

    @FindBy(id = "office-select")
    private FluentWebElement selectOfficeLookup;

    @FindBy(css = "div.jobs-grid")
    private FluentWebElement positionsList;

    public CareerOpportunitiesSection selectDepartment(String departmentName) {
        log.info("Selecting department ... " + departmentName);
        await().until(selectDepartmentLookup).not().stale();
        await().until(selectDepartmentLookup).clickable();
        selectDepartmentLookup.click();
        await().until(getSelectedDepartment(departmentName)).displayed();
        getSelectedDepartment(departmentName).click();
        return this;
    }

    public CareerOpportunitiesSection selectOfficeLocation(String officeLocation) {
        log.info("Selecting office ... " + officeLocation);
        await().until(selectOfficeLookup).not().stale();
        await().until(selectOfficeLookup).clickable();
        selectOfficeLookup.click();
        await().until(getSelectedOffice(officeLocation)).displayed();
        getSelectedOffice(officeLocation).click();
        return this;
    }

    public JobPage openJobPosition(String positionName) {
        log.info("Selecting position ... " + positionName);
        await().until(positionsList).displayed();
        await().until(getSelectedPosition(positionName)).not().stale();
        await().until(getSelectedPosition(positionName)).clickable();
        getSelectedPosition(positionName).click();
        return jobPage;
    }

    private FluentList<FluentWebElement> getSelectedDepartment(String departmentName) {
        return $(xpath(format("//*[@id='department-select']/option[contains(text(), '%s')]", departmentName)));
    }

    private FluentList<FluentWebElement> getSelectedOffice(String officeLocation) {
        return $(xpath(format("//*[@id='office-select']/option[contains(text(), '%s')]", officeLocation)));
    }

    private FluentList<FluentWebElement> getSelectedPosition(String positionName) {
        return $(xpath(format("//*[@class='jobs-grid']//strong[contains(text(), '%s')]", positionName)));
    }

}
