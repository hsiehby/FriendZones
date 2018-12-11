package zones.com.friendzonesv00;

import java.util.Calendar;

public class Time {
    public int hour;
    public int minute;

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getInMinutes() {
        return hour * 60 + minute;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public static boolean isAvailable(Calendar convertedTime, Time bedtime, Time getUpTime) {
        if (bedtime.hour >= 0 && bedtime.hour <= 6) {
            bedtime.hour += 24;
        }
        int convertedTimeInMinutes = 60 * convertedTime.get(Calendar.HOUR_OF_DAY) + convertedTime.get(Calendar.MINUTE);
        int bedtimeInMinutes = 60 * bedtime.hour + bedtime.minute;
        int getUpTimeInMinutes = 60 * getUpTime.hour + getUpTime.minute;

        if (convertedTimeInMinutes < bedtimeInMinutes && convertedTimeInMinutes > getUpTimeInMinutes) {
            return true;
        } else {
            return false;
        }
    }

    public static String getStatus(Calendar currentTime, Time bedtime, Time getUpTime) {
        int currentTimeInMinutes = currentTime.get(Calendar.HOUR_OF_DAY) * 60 + currentTime.get(Calendar.MINUTE);
        if (isAvailable(currentTime, bedtime, getUpTime)) {
            int minutesUntilSleep = bedtime.getInMinutes() - currentTimeInMinutes;
            Time timeUntilSleep = new Time(minutesUntilSleep / 60, minutesUntilSleep % 60);
            return String.format("%d:%02d before the usual bedtime", timeUntilSleep.hour, timeUntilSleep.minute);
        }
        else {
            int getUpTimeInMinutes = getUpTime.getInMinutes();
            if (getUpTimeInMinutes < currentTimeInMinutes) {
                getUpTimeInMinutes += 1440;
            }
            int minutesUntilGetUp = getUpTimeInMinutes - currentTimeInMinutes;
            Time timeUntilGetUp = new Time(minutesUntilGetUp / 60, minutesUntilGetUp % 60);
            return String.format("%d:%02d before the usual get-up time", timeUntilGetUp.hour, timeUntilGetUp.minute);
        }
    }

    public static long getTimeUntilGetUpInMillis(Calendar currentTime, Time getUpTime) {
        int currentTimeInMinutes = currentTime.get(Calendar.HOUR_OF_DAY) * 60 + currentTime.get(Calendar.MINUTE);
        int getUpTimeInMinutes = getUpTime.getInMinutes();
        if (getUpTimeInMinutes < currentTimeInMinutes) {
            getUpTimeInMinutes += 1440;
        }
        return 60000 * (getUpTimeInMinutes - currentTimeInMinutes);
    }
}
