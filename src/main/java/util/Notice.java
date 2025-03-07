package util;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;

// 显示通知气泡
public class Notice {
    public static void showBalloon(String title, String content) {
        Notification notification = new Notification("MyNoticeGroup", title, content, NotificationType.INFORMATION);
        Notifications.Bus.notify(notification);
    }
}
