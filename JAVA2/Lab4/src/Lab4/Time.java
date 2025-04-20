package Lab4;
import java.util.Calendar;

public class Time implements Comparable<Time>, Cloneable {
    private long time;

    public Time() {
        Calendar calendar = Calendar.getInstance();
        this.time = calendar.getTimeInMillis() / 1000;
    }

    public Time(int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        this.time = calendar.getTimeInMillis() / 1000;
    }

    public Time(long elapsedTime) {
        this.time = elapsedTime;
    }

    public int getHour() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.time * 1000);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public int getMinute() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.time * 1000);
        return calendar.get(Calendar.MINUTE);
    }

    public int getSecond() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.time * 1000);
        return calendar.get(Calendar.SECOND);
    }

    public long getSeconds() {
        return this.time;
    }

    @Override
    public String toString() {
        int hour = getHour();
        int minute = getMinute();
        int second = getSecond();
        return hour + " hours " + minute + " minutes " + second + " seconds";
    }

    @Override
    public int compareTo(Time another) {
        return (int)(this.getSeconds() - another.getSeconds());
    }

    @Override
    public Time clone() {
        try {
            return (Time) super.clone();
        } catch (CloneNotSupportedException e) {
            return null; 
        }
    }

 
    public static void main(String[] args) {
        Time time1 = new Time();
        Time time2 = new Time(14, 21, 1);
        Time time3 = new Time(3600); 

        System.out.println("Time1: " + time1);
        System.out.println("Time2: " + time2);
        System.out.println("Time3: " + time3);

        System.out.println("Time1 compareTo Time2: " + time1.compareTo(time2));
        System.out.println("Time2 compareTo Time3: " + time2.compareTo(time3));

        Time time4 = time2.clone();
        System.out.println("Time4 (clone of Time2): " + time4);
    }
}