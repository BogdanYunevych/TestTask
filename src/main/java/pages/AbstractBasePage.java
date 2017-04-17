package pages;

import common.Config;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.DriverSession;

import java.util.List;

public abstract class AbstractBasePage {
    private static final String TABLE_ROW = ".//tbody/tr";
    private static final String ID_CELL = ".//td[contains(@class, 'nid')]";

    protected WebDriver driver;

    public AbstractBasePage() {
        driver = DriverSession.getDriverSession();
        waitPageLoading();
    }

    protected void waitPageLoading() {
        new WebDriverWait(driver, Config.TIMEOUT).until((ExpectedCondition<Boolean>) driver ->
                (Boolean) ((JavascriptExecutor) driver).executeScript("return $.active == 0")
        );

        new WebDriverWait(driver, Config.TIMEOUT).until((ExpectedCondition<Boolean>) driver ->
                "complete".equals(((JavascriptExecutor) driver).executeScript("return document.readyState"))
        );
    }

    protected void setElementVisible(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false)", element);
        } catch (Exception e) {
            throw new RuntimeException("Unable to set element visible", e);
        }
    }

    protected WebElement getWebElement(String xpath) {
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            setElementVisible(element);
            return element;
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Element with xpath = '" + xpath + " not found.", e);
        }
    }

    protected List<WebElement> getWebElements(String xpath) {
        return driver.findElements(By.xpath(xpath));
    }

    protected boolean waitElementDisplayed(String xpath, int wait) {
        try {
            new WebDriverWait(driver, wait).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    protected void fillTextField(WebElement field, String value) {
        field.click();
        field.clear();
        field.sendKeys(value);
    }

    public void setSpecificTextField(String xpath, String value) {
        if (value == null) {
            return;
        }
        fillTextField(enableTextField(xpath), value);
    }

    public void setTextField(String xpath, String value) {
        if (value == null) {
            return;
        }
        fillTextField(getWebElement(xpath), value);
    }

    public void clickBtn(String xpath) {
        waitElementDisplayed(xpath, Config.TIMEOUT);
        getWebElement(xpath).click();
    }

    public void moveTo(String xpath) {
        waitElementDisplayed(xpath, Config.TIMEOUT);
        new Actions(driver).moveToElement(getWebElement(xpath)).build().perform();
    }

    public void setCheckBoxState(String xpath, Boolean state) {
        if (state == null) {
            return;
        }
        WebElement checkBox = getWebElement(xpath);
        if (getCheckBoxState(checkBox) != state) {
            checkBox.click();
        }
    }

    protected boolean getCheckBoxState(WebElement checkBox) {
        return checkBox.isSelected();
    }

    protected WebElement enableTextField(String xpath) {
        WebElement desField = getWebElement(String.format(xpath, "-des"));
        if (desField.isDisplayed()) {
            desField.click();
        }
        return getWebElement(String.format(xpath, StringUtils.EMPTY));
    }

    public WebElement getTableRowByID(String tableLocator, String value) {
        WebElement table = driver.findElement(By.xpath(tableLocator));

        return table.findElements(By.xpath(TABLE_ROW))
                .stream()
                .filter(element -> value.equals(element.findElement(By.xpath(ID_CELL)).getText()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("There isn't any entries with id = " + value + " in table "));
    }
}
