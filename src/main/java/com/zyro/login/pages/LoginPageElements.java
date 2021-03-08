package com.zyro.login.pages;

import org.springframework.stereotype.Component;

@Component
public class LoginPageElements {

    protected static final String GMAIL_SIGN_BTN = "//*[@data-qa='signin-btn-g']";
    protected static final String LOGIN = "//*[@name='identifier']";
    protected static final String LOGIN_NEXT_BTN = "//*[@id='identifierNext']/div/button/div[2]";
    protected static final String PASSWORD = "//*[@name='password']";
    protected static final String PASSWORD_NEXT_BTN = "//*[@id='passwordNext']/div/button/div[2]";
    protected static final String ACCEPT_COOKIES = "//*[@data-qa='cookiesbanner-button-acceptall']";
    protected static final String GET_STARTED = "//*[@data-qa='welcome-btn-getstarted']";
    protected static final String FIRST_CLOSE = "//a[@class='button-close btn-close']";
    protected static final String SECOND_CLOSE = "//div[@class='button-close__container " +
            "button-close__container--regular']";
    protected static final String ACCOUNT_SETTINGS = "//body/div[@id='__nuxt']/div[@id='__layout']/div[@data-qa='nuxt" +
            "-loaded']/div[2]";
    protected static final String SIGN_OUT = "//*[@class='link'][@data-qa='popupwindow-link-signout'][contains(text(),'Sing Out')]";

}