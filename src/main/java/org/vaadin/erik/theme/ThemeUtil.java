package org.vaadin.erik.theme;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinSession;

/**
 * @author erik@vaadin.com
 * @since 20.5.2021
 */
public class ThemeUtil {

    private static final String THEME_ATTRIBUTE = "theme";

    public static void selectTheme(Theme theme) {
        VaadinSession.getCurrent().setAttribute(THEME_ATTRIBUTE, theme);
        UI.getCurrent().getElement().setAttribute("theme", theme.name().toLowerCase());
    }

    public static Theme getCurrentTheme() {
        Theme theme = (Theme) VaadinSession.getCurrent().getAttribute(THEME_ATTRIBUTE);
        return theme != null ? theme : Theme.CARROT_INC;
    }
}
