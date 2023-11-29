package twitter.utils;

import java.util.Date;

public class DateCalculator {
    public static String getDateDiffText(Date date) {
        Date now = new Date();
        long diff = now.getTime() - date.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000);

        if (diffDays == 0) {
            // 1시간 보다 많이 남았으면 시간으로
            long diffHours = diff / (60 * 60 * 1000);
            if (diffHours > 0) {
                return diffHours + "시간 전";
            }

            // 1분 보다 많이 남았으면 분으로
            long diffMinutes = diff / (60 * 1000);
            if (diffMinutes > 0) {
                return diffMinutes + "분 전";
            }
            // 나머지는 초로
            long diffSeconds = diff / 1000;
            if (diffSeconds > 0) {
                return diffSeconds + "초 전";
            }

        } else if (diffDays == 1) {
            return "어제";
        } else if (diffDays < 7) {
            return diffDays + "일 전";
        }

        // yyyy-mm-dd
        return date.toString().substring(0, 10);

    }
}
