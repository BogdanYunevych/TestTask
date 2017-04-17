package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

import static common.constants.Constants.Campaign;
import static common.constants.Constants.CampaignCategory;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"categories", "attachment"})
@ToString
public class Idea {
    private Campaign campaign;
    private String title;
    private String description;
    private List<CampaignCategory> categories;
    private String attachment;
    private Boolean isConfidential;
    private Boolean isAnonymous;

    private static String getRandomString(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public List<CampaignCategory> getCategories() {
        return categories == null ? categories = new ArrayList<CampaignCategory>() : categories;
    }

    public static class IdeaBuilder {
        public IdeaBuilder basicIdea() {
            return this.campaign(Campaign.SPONTANEOUS_IDEAS)
                    .title(getRandomString(10))
                    .description(getRandomString(40))
                    .isConfidential(true)
                    .isAnonymous(true);
        }
    }
}
