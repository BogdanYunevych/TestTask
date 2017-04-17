package pages;

import common.Config;
import org.openqa.selenium.WebElement;

public class IdeaSummaryPage extends AbstractBasePage {
    private static final String IDEA_ID = "//div[@sec_id = 'node_header']//h2[@class = 'art-postheader']";

    public String getIdeaNumber() {
        WebElement ideaID = getWebElements(IDEA_ID)
                .stream()
                .filter(element -> element.isDisplayed())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Idea Id isn't displayed on IdeaSummaryPage."));

        return ideaID.getText().split("#")[1].replace(")", "");
    }

    @Override
    protected void waitPageLoading() {
        super.waitPageLoading();
        if (!waitElementDisplayed(IDEA_ID, Config.TIMEOUT)) {
            throw new RuntimeException("Header is not loaded");
        }
    }
}
