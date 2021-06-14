package org.vaadin.erik.views.main;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tabs;
import org.vaadin.erik.theme.ThemeVariant;
import org.vaadin.erik.theme.ThemeSelector;
import org.vaadin.erik.theme.ThemeUtil;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainView extends AppLayout {

    public MainView() {
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        addToDrawer(createDrawerContent(new ThemeSelector()));
    }

    private Component createHeaderContent() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setId("header");
        layout.getThemeList().set("dark", true);
        layout.setWidthFull();
        layout.setSpacing(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.add(new DrawerToggle());
        H1 viewTitle = new H1("Master-detail view");
        layout.add(viewTitle);
        layout.add(new Avatar());
        return layout;
    }

    private Component createDrawerContent(Tabs menu) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.getThemeList().set("spacing-s", true);
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);
        HorizontalLayout logoLayout = new HorizontalLayout();
        logoLayout.setId("logo");
        logoLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        Image logo = new Image(getLogoSrc(ThemeUtil.getCurrentThemeVariant()), "Dynamic Theme Demo logo");
        ThemeUtil.addThemeChangedListener(UI.getCurrent(), e -> logo.setSrc(getLogoSrc(e.getThemeVariant())));

        logoLayout.add(logo);
        logoLayout.add(new H1("Dynamic Theme Demo"));
        layout.add(logoLayout, menu);
        return layout;
    }

    private String getLogoSrc(ThemeVariant themeVariant) {
        if (themeVariant == ThemeVariant.CARROT_INC) {
            return "images/carrot_inc.png";
        }
        return "images/logo.png";
    }
}
