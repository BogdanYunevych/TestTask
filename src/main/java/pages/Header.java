package pages;

import common.Config;

import static common.constants.Constants.ADVANCED_SEARCH;
import static common.constants.Constants.Campaign;
import static common.constants.Constants.LOGOUT;

public class Header extends AbstractBasePage {
    private static final String SUBMIT_IDEA_BTN = "//*[@id='submit-nav']";
    private static final String SEARCH_BTN = "//*[@id = 'search-nav']";
    private static final String PROFILE_BTN = "//*[@id = 'user-nav']";
    private static final String MENU_ITEM = "//li[contains(@class, 'dropdown_navigation_item') and contains(., '%s')]";

    public IdeaWizard openIdeaWizard(Campaign campaign) {
        moveTo(SUBMIT_IDEA_BTN);
        clickBtn(String.format(MENU_ITEM, campaign.getValue()));
        return new IdeaWizard();
    }

    public AdvancedSearchPage openAdvancedSearchPage() {
        moveTo(SEARCH_BTN);
        clickBtn(String.format(MENU_ITEM, ADVANCED_SEARCH));
        return new AdvancedSearchPage();
    }

    public LoginPage logout() {
        moveTo(PROFILE_BTN);
        clickBtn(String.format(MENU_ITEM, LOGOUT));
        return new LoginPage();
    }

    @Override
    protected void waitPageLoading() {
        super.waitPageLoading();
        if (!waitElementDisplayed(SUBMIT_IDEA_BTN, Config.TIMEOUT)) {
            throw new RuntimeException("Header is not loaded");
        }
    }
}
