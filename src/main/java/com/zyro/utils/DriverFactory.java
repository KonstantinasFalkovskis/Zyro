package com.zyro.utils;

import org.openqa.selenium.WebDriver;

public interface DriverFactory {
    WebDriver newInstance();

    String name();
}
