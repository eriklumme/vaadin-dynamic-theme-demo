package org.vaadin.erik.theme;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.shared.Registration;

/**
 * @author erik@vaadin.com
 * @since 20.5.2021
 */
public class ThemeUtil {

    private static final String THEME_ATTRIBUTE = "theme";
    private static final String THEME_CHANGE_LISTENERS_ATTRIBUTE = "theme-change-listeners";

    public static void selectTheme(Theme theme) {
        VaadinSession.getCurrent().setAttribute(THEME_ATTRIBUTE, theme);
        UI.getCurrent().getElement().setAttribute("theme", theme.name().toLowerCase());
        fireThemeChangeListeners(UI.getCurrent(), theme);
    }

    public static Theme getCurrentTheme() {
        Theme theme = (Theme) VaadinSession.getCurrent().getAttribute(THEME_ATTRIBUTE);
        return theme != null ? theme : Theme.CARROT_INC;
    }

    public static Registration addThemeChangeListener(UI ui, Consumer<Theme> themeChangeListener) {
        Set<Consumer<Theme>> listeners = getThemeChangeListeners(ui);
        listeners.add(themeChangeListener);
        return () -> listeners.remove(themeChangeListener);
    }

    private static Set<Consumer<Theme>> getThemeChangeListeners(UI ui) {
        @SuppressWarnings("unchecked")
        Set<Consumer<Theme>> listeners = (Set<Consumer<Theme>>) ComponentUtil.getData(ui, THEME_CHANGE_LISTENERS_ATTRIBUTE);
        if (listeners == null) {
            listeners = new HashSet<>();
            ComponentUtil.setData(ui, THEME_CHANGE_LISTENERS_ATTRIBUTE, listeners);
        }
        return listeners;
    }

    private static void fireThemeChangeListeners(UI ui, Theme theme) {
        getThemeChangeListeners(ui).forEach(listener -> listener.accept(theme));
    }
}
