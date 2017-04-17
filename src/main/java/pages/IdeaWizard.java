package pages;

import common.Config;
import dto.Idea;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static common.constants.Constants.CampaignCategory;

public class IdeaWizard extends AbstractBasePage {

    private final static String TITLE_FLD = "//*[@id='edit-title']";
    private final static String CATEGORY_DROPDOWN = "//*[@id = 'qm_select_2014_options']";
    private final static String CATEGORY_FIELD =
            "//div[contains(@class, 'elm-edit-taxonomy')]//div[contains(@class, 'qm_active_select_item')]";
    private final static String CATEGORY_ITEM =
            "//div[contains(@class, 'qm_select_2014_not_selected') and contains(., '%s')]";
    private final static String DESCRIPTION_FIELD = "//body";
    private final static String CONFIDENTIAL_CHKBX = "//*[@id = 'edit-confidential']";
    private final static String ANONYMOUS_CHKBX = "//*[@id = 'edit-qm-anonymous-is-anonymous']";
    private final static String NEXT_BTN = "//*[@id = 'edit-qm-node-wizard-next']";
    private final static String PUBLISH_BTN = "//*[@id = 'edit-qm-node-wizard-finish']";
    private final static String FRAME = "//iframe";

    public IdeaWizard fillIdeaField(Idea idea) {
        setTextField(TITLE_FLD, idea.getTitle());
        setDescription(idea.getDescription());
        setCategoryField(idea.getCategories());
        setCheckBoxState(CONFIDENTIAL_CHKBX, idea.getIsConfidential());
        setCheckBoxState(ANONYMOUS_CHKBX, idea.getIsAnonymous());
        return this;
    }

    public IdeaWizard clickNextBtn() {
        clickBtn(NEXT_BTN);
        return this;
    }

    public IdeaSummaryPage clickPublishBtn() {
        clickBtn(PUBLISH_BTN);
        return new IdeaSummaryPage();
    }

    public IdeaSummaryPage publishIdea(Idea idea) {
        return fillIdeaField(idea).clickNextBtn().clickPublishBtn();
    }

    private void setCategoryField(List<CampaignCategory> categories) {
        categories.stream().forEach(category -> selectCategory(category.getValue()));
        getCategoryDropdownInvisible();
    }

    private void selectCategory(String category) {
        getCategoryDropdownVisible();
        waitElementDisplayed(String.format(CATEGORY_ITEM, category), Config.TIMEOUT);
        getWebElement(String.format(CATEGORY_ITEM, category)).click();
    }

    public void setDescription(String value) {
        switchDescriptionFrame();
        WebElement desc = getWebElement(DESCRIPTION_FIELD);
        desc.click();
        desc.clear();
        desc.sendKeys(value);
        driver.switchTo().defaultContent();
    }

    private void getCategoryDropdownVisible() {
        if (getWebElements(CATEGORY_DROPDOWN).isEmpty()) {
            getWebElement(CATEGORY_FIELD).click();
        }
    }

    private void getCategoryDropdownInvisible() {
        if (!getWebElements(CATEGORY_DROPDOWN).isEmpty()) {
            getWebElement(CATEGORY_FIELD).click();
        }
    }

    private WebElement switchDescriptionFrame() {
        WebElement frame = getWebElements(FRAME)
                .stream()
                .filter(element -> element.isDisplayed())
                .collect(Collectors.toList()).get(0);
        driver.switchTo().frame(frame);
        return frame;
    }

    @Override
    protected void waitPageLoading() {
        super.waitPageLoading();
        if (!waitElementDisplayed(TITLE_FLD, Config.TIMEOUT)) {
            throw new RuntimeException("Header is not loaded");
        }
    }
}

