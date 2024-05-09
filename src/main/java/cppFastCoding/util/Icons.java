package cppFastCoding.util;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public enum Icons {
    Delete("icons/delete-svgrepo-com.png"),
    Collapse("icons/collapse-svgrepo-com.png"),
    Expand("icons/expand-svgrepo-com.png"),
    Add("icons/add-svgrepo-com.png"),
    Run("icons/run-svgrepo-com.png"),
    Settings("icons/settings-svgrepo-com.png"),
    Stop("icons/stop-svgrepo-com.png"),
    ;
    ImageIcon icon;

    Icons(String resourceName) {
        URL imageURL = getClass().getClassLoader().getResource(resourceName);
        if (imageURL != null) {
            icon = new ImageIcon(imageURL);
        }
    }

    public ImageIcon getIcon(Dimension size) {
        if (icon != null)
            return new ImageIcon(icon.getImage().getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH));
        return null;
    }
}
