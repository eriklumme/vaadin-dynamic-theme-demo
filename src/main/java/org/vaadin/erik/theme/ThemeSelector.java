package org.vaadin.erik.theme;

import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;

/**
 * @author erik@vaadin.com
 * @since 20.5.2021
 */
public class ThemeSelector extends Tabs {

    public ThemeSelector() {
        setOrientation(Tabs.Orientation.VERTICAL);
        addThemeVariants(TabsVariant.LUMO_MINIMAL);
        setId("tabs");
        addSelectedChangeListener(e -> {
            if (e.isFromClient()) {
                Theme theme = ComponentUtil.getData(e.getSelectedTab(), Theme.class);
                ThemeUtil.selectTheme(theme);
            }
        });

        for (Theme theme: Theme.values()) {
            Tab tab = new Tab(theme.getCaption());
            ComponentUtil.setData(tab, Theme.class, theme);
            add(tab);

            Theme currentTheme = ThemeUtil.getCurrentTheme();
            if (currentTheme == theme) {
                setSelectedTab(tab);
            }
        }
    }
}
