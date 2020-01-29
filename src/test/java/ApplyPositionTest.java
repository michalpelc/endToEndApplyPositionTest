import engine.CustomFluentDriver;
import org.fluentlenium.core.annotation.Page;
import org.junit.Test;
import page.CareerWelcomePage;
import page.JobPage;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplyPositionTest extends CustomFluentDriver {

    @Page
    private CareerWelcomePage careerWelcomePage;

    @Page
    private JobPage jobPage;

    @Test
    public void shouldApplyForGivenPosition() {
        String departmentName = "Engineering & IT";
        String officeLocation = "Kraków";
        String position = "QA/Test Engineer";
        boolean isResumeAttached = false;

        goTo(careerWelcomePage)
                .switchToOurOpportunitiesSection()
                .selectDepartment(departmentName)
                .selectOfficeLocation(officeLocation)
                .openJobPosition(position)
                .fillApplicationFormWithRequiredData(isResumeAttached)
                .submitApplicationForm();

        verifyThatMissingResumeErrorAppears();

    }

    private void verifyThatMissingResumeErrorAppears() {
        final String errorExpected = "Please attach a resume";
        final String errorActual = jobPage.getMissingResumeError();
        assertThat(errorExpected).contains(errorActual);
    }

}
