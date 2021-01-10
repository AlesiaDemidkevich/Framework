package test;

import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.ElemaHomePage;
import service.UserCreator;

public class UserDataTests extends CommonConditions {
    private static final String HOMEPAGE_URL = "https://elema.by/";

    @Test
    public void checkInvalidPasswordTest() {
        User user = UserCreator.withCredentialsFromProperty("first");

        ElemaHomePage authorizationInfo = new ElemaHomePage(driver)
                .openPage(HOMEPAGE_URL)
                .clickAuthorizationButton()
                .inputUserLogin(user.getUserLogin())
                .inputUserPassword(user.getUserPassword())
                .clickEnterButton();

        Assert.assertEquals(authorizationInfo.getNoCorrectInfo(),"Неверный логин или пароль.");
    }
}
