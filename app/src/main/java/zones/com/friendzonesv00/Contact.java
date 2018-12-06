package zones.com.friendzonesv00;

public class Contact {
    private String name;
    private String phone;
    private int timeZone;
    private Time bedTime;
    private Time getUpTime;

    public Contact(String name, String phone, int timeZone, Time bedTime, Time getUpTime) {
        this.name = name;
        this.phone = phone;
        this.timeZone = timeZone;
        this.bedTime = bedTime;
        this.getUpTime = getUpTime;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getTimeZone() {
        return timeZone;
    }

    public Time getBedTime() {
        return bedTime;
    }

    public Time getGetUpTime() {
        return getUpTime;
    }
}
