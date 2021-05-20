package org.vaadin.erik.theme;

/**
 * @author erik@vaadin.com
 * @since 20.5.2021
 */
public enum Theme {
    STANDARD("Standard"), CARROT_INC("Carrot Inc"), CLEAN("Clean");

    private final String caption;

    Theme(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }

    public String getAttribute() {
        return name().toLowerCase();
    }
}
