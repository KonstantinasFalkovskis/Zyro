package com.zyro.login.pages;

import com.zyro.utils.SeleniumMethods;
import io.qameta.allure.Step;
import org.springframework.stereotype.Component;

import static com.zyro.login.pages.LoginPageElements.ACCEPT_COOKIES;
import static com.zyro.login.pages.LoginPageElements.ACCOUNT_SETTINGS;
import static com.zyro.login.pages.LoginPageElements.FIRST_CLOSE;
import static com.zyro.login.pages.LoginPageElements.GET_STARTED;
import static com.zyro.login.pages.LoginPageElements.GMAIL_SIGN_BTN;
import static com.zyro.login.pages.LoginPageElements.LOGIN;
import static com.zyro.login.pages.LoginPageElements.LOGIN_NEXT_BTN;
import static com.zyro.login.pages.LoginPageElements.PASSWORD;
import static com.zyro.login.pages.LoginPageElements.PASSWORD_NEXT_BTN;
import static com.zyro.login.pages.LoginPageElements.SECOND_CLOSE;
import static com.zyro.login.pages.LoginPageElements.SIGN_OUT;
import static com.zyro.testdata.Constants.GET_STARTED_TEXT_EN;
import static org.assertj.core.api.Assertions.assertThat;

@Component
public class LoginPageMethods extends SeleniumMethods {

    @Step("Navigate to Log in page")
    public void navigateToLogInPage() {
        navigateToURL(startPage, "/signin");
    }

    @Step("Accept cookies")
    public void acceptCookies() {
        click(ACCEPT_COOKIES);
    }

    @Step("Login with Gmail")
    public void loginToZyroWithGmail() {
        click(GMAIL_SIGN_BTN);
    }

    @Step("Enter text to Email field: {email}")
    public void enterTextToEmailField(String userName) {
        enterText(LOGIN, userName);
    }

    @Step("Click Next button after user name is entered")
    public void clickNextButtonAfterUserNameEntering() {
        click(LOGIN_NEXT_BTN);
    }

    @Step("Enter text to Password field: {password}")
    public void enterTextToPasswordField(String password) {
        enterText(PASSWORD, password);
    }

    @Step("Click Next button after password is entered")
    public void clickNextButtonAfterPasswordEntering() {
        click(PASSWORD_NEXT_BTN);
    }

    @Step("Account visibility")
    public void validateAccountButtonIsVisible() {
        waitForElementToBeVisible(GET_STARTED);
        isElementDisplayed(GET_STARTED);
    }

    @Step("Get button text")
    public void getButtonText() {
        getText(GET_STARTED);
    }

    @Step("Validate button text")
    public void assertAccountButtonText() {
        waitForElementToBeVisible(GET_STARTED);
        assertThat(getText(GET_STARTED)).isEqualTo(GET_STARTED_TEXT_EN);
    }

    @Step("Expand settings menu")
    public void pressSettingIcon() {
        mouseHover(ACCOUNT_SETTINGS);
    }

    @Step("Press sign out button")
    public void pressSignOutButton() {
        click(SIGN_OUT);
    }

    public void closeStartPage() {
        click(FIRST_CLOSE);
    }

    public void closeWebTemplatesPage() {
        click(SECOND_CLOSE);
    }
}
