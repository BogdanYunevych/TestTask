package dataproviders;

import common.Config;
import common.constants.Constants;
import dto.Idea;
import dto.User;
import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class IdeaDataProvider {
    @DataProvider(parallel = true)
    public static Object[][] getUserAndIdeaDetails() {
        User user = User.builder()
                .userName(Config.LOGIN)
                .password(Config.PASSWORD)
                .build();
        Idea travelIdea = Idea.builder().basicIdea()
                .campaign(Constants.Campaign.MODERN_TRAVEL)
                .categories(Arrays.asList(Constants.CampaignCategory.AVIATION, Constants.CampaignCategory.GREEN))
                .build();
        Idea randomIdea = Idea.builder().basicIdea()
                .campaign(Constants.Campaign.SPONTANEOUS_IDEAS)
                .isAnonymous(true)
                .isConfidential(false)
                .build();
        Idea untouchableIdea = Idea.builder().basicIdea()
                .campaign(Constants.Campaign.UNTOUCHABLE)
                .categories(Arrays.asList(Constants.CampaignCategory.CREATIVITY))
                .isAnonymous(false)
                .isConfidential(false)
                .build();
        return new Object[][]{
                {user, travelIdea},
                {user, randomIdea},
                {user, untouchableIdea}
        };
    }
}
