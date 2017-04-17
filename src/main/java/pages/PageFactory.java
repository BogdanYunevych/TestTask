package pages;

public class PageFactory {
    public static <T extends AbstractBasePage> T getPage(Class<T> clazz) {
        try {
            return clazz.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(String.format("Unable to get %s page instance", clazz));
        }
    }
}
