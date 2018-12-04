package zones.com.friendzonesv00;

public class Contact {
    private String name;
    private String phone;
    private int timeZone;

    public Contact(String name, String phone, int timeZone) {
        this.name = name;
        this.phone = phone;
        this.timeZone = timeZone;
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
}
