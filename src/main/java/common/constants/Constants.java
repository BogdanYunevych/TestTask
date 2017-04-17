package common.constants;

import lombok.AllArgsConstructor;

public class Constants {
    public static final String CONFIDENTIAL = "Confidential";
    public static final String ANONYMOUS = "Anonymous";
    public static final String ADVANCED_SEARCH = "Advanced Search";
    public static final String LOGOUT = "Logout";

    @AllArgsConstructor
    public enum Campaign {
        MODERN_TRAVEL("Modern Travel"),
        SPONTANEOUS_IDEAS("Spontaneous Ideas"),
        UNTOUCHABLE("Untouchable");

        private String value;

        public static Campaign getByName(String name) {
            return valueOf(name.toUpperCase().replace(" ", "_"));
        }

        public String getValue() {
            return value;
        }
    }

    @AllArgsConstructor
    public enum CampaignCategory {
        AVIATION("Aviation"),
        BIKES("Bikes"),
        CARS("Cars"),
        GREEN("Green"),
        INTERNATIONAL("International"),
        LOCAL("Local"),
        MISC("Misc"),
        TRAINS("Trains"),
        BRAND("Brand"),
        CREATIVITY("Creativity"),
        INVESTMENT("Investment"),
        PROCESSES("Processes"),
        REVENUE("Revenue"),
        STRATEGY("Strategy");

        private String value;

        public String getValue() {
            return value;
        }
    }
}
