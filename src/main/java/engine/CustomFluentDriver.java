package engine;

import org.apache.log4j.Logger;
import org.fluentlenium.adapter.junit.FluentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.String.format;
import static java.lang.System.getProperty;
import static org.openqa.selenium.remote.DesiredCapabilities.firefox;

public class CustomFluentDriver extends FluentTest {

    private final static Logger log = Logger.getLogger(CustomFluentDriver.class);
    private final String IS_REMOTE = getProperty("isRemote");

    private boolean isRemote() {
        return IS_REMOTE.equals("true");
    }

    @Override
    public WebDriver newWebDriver() {
        log.info("Initializing WebDriver ... ");
        if (isRemote()) {
            try {
                return remoteWebDriver();
            } catch (MalformedURLException e) {
                log.error("URL format wrong", e);
            }
            log.info("Remote FireFox driver has been initialized ... ");
        }
        log.info("Initializing local instance of FF driver ... ");
        return new FirefoxDriver();
    }

    private RemoteWebDriver remoteWebDriver() throws MalformedURLException {
        String HUB_URL = getProperty("hubURL");
        String HUB_PORT = getProperty("hubPort");
        String URL_FORMAT = "http://%s:%s/wd/hub";
        DesiredCapabilities DEFAULT_BROWSER = firefox();
        return new RemoteWebDriver(new URL(format(URL_FORMAT, HUB_URL, HUB_PORT)), DEFAULT_BROWSER);
    }

}
