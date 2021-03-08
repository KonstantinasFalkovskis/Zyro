package com.zyro.tests.login;

import com.zyro.login.pages.LoginPageMethods;
import com.zyro.tests.Config;
import io.qameta.allure.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static com.zyro.testdata.Constants.LOGIN;
import static com.zyro.testdata.Constants.PASSWORD;

public class LoginTest extends Config {

    @Autowired
    private LoginPageMethods loginPageMethods;

    @Description("Login to Zyro.com online website builder")
    @Test(groups = {"regression", "login"}, priority = 1)
    void logInToUserAccount() {
        loginPageMethods.navigateToLogInPage();
        loginPageMethods.acceptCookies();
        loginPageMethods.loginToZyroWithGmail();
        loginPageMethods.enterTextToEmailField(LOGIN);
        loginPageMethods.clickNextButtonAfterUserNameEntering();
        loginPageMethods.enterTextToPasswordField(PASSWORD);
        loginPageMethods.clickNextButtonAfterPasswordEntering();
        loginPageMethods.validateAccountButtonIsVisible();
        loginPageMethods.getButtonText();
        loginPageMethods.assertAccountButtonText();
        loginPageMethods.closeStartPage();
        loginPageMethods.closeWebTemplatesPage();
        loginPageMethods.pressSettingIcon();
        loginPageMethods.pressSignOutButton();

    }

}
