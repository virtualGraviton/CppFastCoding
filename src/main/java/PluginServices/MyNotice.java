package PluginServices;
import com.esotericsoftware.kryo.NotNull;
import com.intellij.notification.*;
public class MyNotice {
    public static void ShowBalloon(String title, String content) {
        Notification notification = new Notification("MyNoticeGroup", title, content, NotificationType.INFORMATION);
        Notifications.Bus.notify(notification);
    }
}

