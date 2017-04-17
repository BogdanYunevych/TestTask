package pages;

import common.Config;
import dto.Idea;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static common.constants.Constants.ANONYMOUS;
import static common.constants.Constants.CONFIDENTIAL;
import static common.constants.Constants.Campaign;

public class AdvancedSearchPage extends AbstractBasePage {
    private static final String TABLE = "//div[contains(@class, 'view-content-advanced-search-basic')]//table";
    private static final String TITLE_CELL = "./td[contains(@class, 'title')]";
    private static final String CONFIDENTIAL_CELL = "./td[contains(@class, 'confidential')]";
    private static final String DESCRIPTION_CELL = "./td[contains(@class, 'description')]";
    private static final String USER_NAME_CELL = "./td[contains(@class, 'users-name')]";
    private static final String DATA_TITLE_CELL = "./td[contains(@class, 'data-title')]";

    public Idea getIdeaFromSearchResultTable(String id) {
        WebElement row = getTableRowByID(TABLE, id);
        String title = row.findElement(By.xpath(TITLE_CELL)).getText();
        String confidential = row.findElement(By.xpath(CONFIDENTIAL_CELL)).getText();
        String author = row.findElement(By.xpath(USER_NAME_CELL)).getText();
        String description = row.findElement(By.xpath(DESCRIPTION_CELL)).getText();
        String campaign = row.findElement(By.xpath(DATA_TITLE_CELL)).getText();

        return Idea.builder()
                .title(title)
                .description(description)
                .campaign(Campaign.getByName(campaign))
                .isConfidential(CONFIDENTIAL.equalsIgnoreCase(confidential))
                .isAnonymous(ANONYMOUS.equalsIgnoreCase(author))
                .build();
    }

    @Override
    protected void waitPageLoading() {
        super.waitPageLoading();
        if (!waitElementDisplayed(TABLE, Config.TIMEOUT)) {
            throw new RuntimeException("Header is not loaded");
        }
    }
}
