package view;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public enum LanguageManager {
    INSTANCE;
    private ResourceBundle resourceBundle;
    private String resourceName = "property.text";
    private LanguageManager() {
        resourceBundle = ResourceBundle.getBundle(resourceName, Locale.getDefault());
    }
    public void changeLanguage(Locale newLocale) {
        resourceBundle = ResourceBundle.getBundle(resourceName, newLocale);
    }
    public String getLabel(String labelKey) {
        return resourceBundle.getString(labelKey);
    }
    public Enumeration getSetSey() {
        return resourceBundle.getKeys();
    }
}
