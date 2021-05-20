package org.vaadin.erik.theme;

import com.vaadin.flow.component.UI;

/**
 * @author erik@vaadin.com
 * @since 20.5.2021
 */
public class ThemeUtil {

    public enum Theme {
        STANDARD("Standard"), CARROT_INC("Carrot Inc"), CLEAN("Clean");

        private final String name;

        Theme(String name) {
            this.name = name;
        }

        public String getThemeName() {
            return name;
        }
    }

    public static void selectTheme(Theme theme) {
        UI.getCurrent().getElement().setAttribute("theme", theme.name().toLowerCase());
    }
}
