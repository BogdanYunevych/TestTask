package pages;

import common.Config;
import dto.User;

public class LoginPage extends AbstractBasePage {
    private static final String USER_NAME_FLD = "//*[@id = 'edit-name%s']";
    private static final String PASSWORD_FLD = "//*[@id = 'edit-pass%s']";
    private static final String LOGIN_BTN = "//*[@id='edit-submit']";

    public HomePage login(User user) {
        setSpecificTextField(USER_NAME_FLD, user.getUserName());
        setSpecificTextField(PASSWORD_FLD, user.getPassword());
        clickBtn(LOGIN_BTN);
        return new HomePage();
    }

    @Override
    protected void waitPageLoading() {
        super.waitPageLoading();
        if (!waitElementDisplayed(LOGIN_BTN, Config.TIMEOUT)) {
            throw new RuntimeException("Login page is not loaded");
        }
    }
}
