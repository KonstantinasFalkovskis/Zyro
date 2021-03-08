package com.zyro.utils;

import com.browserstack.local.Local;
import java.net.URL;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class BrowserStackDriverFactory implements DriverFactory {

    private final DesiredCapabilities capabilities;
    private final URL url;
    private final Local browserStackLocal;

    public BrowserStackDriverFactory(Environment environment) throws Exception {

        browserStackLocal = new Local();
        HashMap<String, String> bsLocalArgs = new HashMap<>();
        bsLocalArgs.put("key", environment.getRequiredProperty("webDriver.browserStack.key"));
        bsLocalArgs.put("forcelocal", environment.getRequiredProperty("webDriver.browserStack.local"));
        browserStackLocal.start(bsLocalArgs);

        capabilities = new DesiredCapabilities();
        capabilities.setCapability("os", environment.getRequiredProperty("webDriver.browserStack.os"));
        capabilities.setCapability("os_version", environment.getRequiredProperty("webDriver.browserStack.osVersion"));
        capabilities.setCapability("browser", environment.getRequiredProperty("webDriver.browser"));
        capabilities.setCapability(
                "browser_version", environment.getRequiredProperty("webDriver.browserStack.browserVersion"));
        capabilities.setCapability(
                "browserstack.local", environment.getRequiredProperty("webDriver.browserStack.local"));
        capabilities.setCapability("browserstack.networkLogs", "true");
        capabilities.setCapability("browserstack.sendKeys", "true");
        capabilities.setCapability("browserstack.idleTimeout", 300);
        capabilities.setCapability("resolution", "1920x1080");

        String USERNAME = environment.getRequiredProperty("webDriver.browserStack.username");
        String AUTOMATE_KEY = environment.getRequiredProperty("webDriver.browserStack.key");
        String DOMAIN = environment.getRequiredProperty("webDriver.browserStack.domain");
        url = new URL("https://" + USERNAME + ":" + AUTOMATE_KEY + "@" + DOMAIN);
    }

    @Override
    public WebDriver newInstance() {
        RemoteWebDriver webDriver = new RemoteWebDriver(url, capabilities);
        webDriver.setFileDetector(new LocalFileDetector());
        return webDriver;
    }

    @Override
    public String name() {
        return "browserStack";
    }}