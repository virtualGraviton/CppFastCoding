package cppFastCoding.services;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;

public class Notice {
    public static void showBalloon(String title, String content) {
        Notification notification = new Notification("MyNoticeGroup", title, content, NotificationType.INFORMATION);
        Notifications.Bus.notify(notification);
    }
}
