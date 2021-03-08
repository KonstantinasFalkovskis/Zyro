package com.zyro.tests;

import com.zyro.SpringConfig;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

@SpringBootTest(classes = SpringConfig.class)
public class Config extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebDriver webDriver;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        webDriver.manage().deleteAllCookies();
        webDriver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        webDriver.quit();
    }

    protected WebDriver getWebDriver() {
        return webDriver;
    }

}
