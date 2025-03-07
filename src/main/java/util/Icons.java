package util;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

public class Icons {
    public static boolean isLight = false;

    public static final class Add {
        public static final Icon light = IconLoader.getIcon("icons/add.svg", Icons.class);
        public static final Icon dark = IconLoader.getIcon("icons/add-dark.svg", Icons.class);

        public static Icon getIcon() {
            return isLight ? light : dark;
        }
    }

    public static final class Run {
        public static final Icon light = IconLoader.getIcon("icons/run.svg", Icons.class);
        public static final Icon dark = IconLoader.getIcon("icons/run-dark.svg", Icons.class);

        public static Icon getIcon() {
            return isLight ? light : dark;
        }
    }

    public static final class Setting {
        public static final Icon light = IconLoader.getIcon("icons/settings.svg", Icons.class);
        public static final Icon dark = IconLoader.getIcon("icons/settings-dark.svg", Icons.class);

        public static Icon getIcon() {
            return isLight ? light : dark;
        }
    }

    public static final class Expand {
        public static final Icon light = IconLoader.getIcon("icons/expand.svg", Icons.class);
        public static final Icon dark = IconLoader.getIcon("icons/expand-dark.svg", Icons.class);

        public static Icon getIcon() {
            return isLight ? light : dark;
        }
    }

    public static final class Collapse {
        public static final Icon light = IconLoader.getIcon("icons/collapse.svg", Icons.class);
        public static final Icon dark = IconLoader.getIcon("icons/collapse-dark.svg", Icons.class);

        public static Icon getIcon() {
            return isLight ? light : dark;
        }
    }

    public static final class Delete {
        public static final Icon light = IconLoader.getIcon("icons/delete.svg", Icons.class);
        public static final Icon dark = IconLoader.getIcon("icons/delete-dark.svg", Icons.class);

        public static Icon getIcon() {
            return isLight ? light : dark;
        }
    }
}