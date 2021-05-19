package package1;

import java.util.Calendar;

public class Task implements Comparable<Task> {

    private String info;
    private Calendar calendar;

    public Task(String info, Calendar calendar) {
        this.info = info;
        this.calendar = calendar;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public String toString() {
        return String.format("%02d.%02d %02d:%02d   Task description: \"%s\"", (calendar.get(Calendar.MONTH) + 1),
                calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), info);
    }

    public String toStringFileFormat() {
        return calendar.get(Calendar.MONTH) + " " +
                calendar.get(Calendar.DAY_OF_MONTH) + " " +
                calendar.get(Calendar.HOUR_OF_DAY) + " " +
                calendar.get(Calendar.MINUTE) + "\n" +
                info;
    }

    @Override
    public int compareTo(Task o) {
        return this.calendar.compareTo(o.calendar);
    }

}
