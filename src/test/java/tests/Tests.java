package tests;

import dataproviders.IdeaDataProvider;
import dto.Idea;
import dto.User;
import org.testng.annotations.Test;
import pages.Header;
import pages.LoginPage;

import static org.testng.Assert.assertTrue;
import static pages.PageFactory.getPage;

public class Tests extends AbstractBaseTest {
    @Test(dataProvider = "getUserAndIdeaDetails", dataProviderClass = IdeaDataProvider.class)
    public void testCreateAndSearchIdea(User user, Idea idea) {
        String ideaId = getPage(LoginPage.class).login(user)
                .openIdeaWizard(idea.getCampaign())
                .publishIdea(idea)
                .getIdeaNumber();

        Idea actualIdea = getPage(Header.class)
                .openAdvancedSearchPage()
                .getIdeaFromSearchResultTable(ideaId);

        assertTrue(idea.equals(actualIdea),
                String.format("Idea record in Result table contains unexpected values. Expected: %s. But was: %s",
                        idea.toString(), actualIdea.toString()));

        getPage(Header.class).logout();
    }
}
