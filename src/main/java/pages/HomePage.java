package pages;

import common.Config;

import static common.constants.Constants.Campaign;

public class HomePage extends AbstractBasePage {
    private static final String SUBMIT_RANDOM_IDEA_BTN = "//*[@id='random-campaign']//a[contains(., 'Submit')]";
    private static final String RECENT_IDEAS_TABLE = "//ul[@class='pinterest-ul']";

    public IdeaWizard openIdeaWizard(Campaign campaign) {
        return Campaign.SPONTANEOUS_IDEAS.equals(campaign) ? openIdeaWizard() : new Header().openIdeaWizard(campaign);
    }

    public IdeaWizard openIdeaWizard() {
        clickBtn(SUBMIT_RANDOM_IDEA_BTN);
        return new IdeaWizard();
    }

    @Override
    protected void waitPageLoading() {
        super.waitPageLoading();
        if (!waitElementDisplayed(RECENT_IDEAS_TABLE, Config.TIMEOUT)) {
            throw new RuntimeException("Home page is not loaded");
        }

    }
}
