package package1;

import java.util.*;
import javax.swing.JOptionPane;


public class Alarm {
    Thread t;

    public void checkAlarm(int h, int m, int s, String str) {
        final int a = h, b = m, c = s;

        t = new Thread() {
            public void run() {
                while (true) {
                    Calendar d = new GregorianCalendar();
                    int hours = d.get(Calendar.HOUR_OF_DAY);
                    int mins = d.get(Calendar.MINUTE);
                    int sec = d.get(Calendar.SECOND);
                    if (a == hours && b == mins && c == sec) {
                        JOptionPane.showMessageDialog(null, str);
                        break;
                    }
                }
            }
        };
        t.setPriority(Thread.MIN_PRIORITY);
        t.start();
    }

    public void alarmSet() {

        Scanner sr = new Scanner(System.in);
        System.out.println("Write message");
        String str = sr.nextLine();
        System.out.println("Write hour");
        int hr = sr.nextInt();
        System.out.println("Write minute");
        int mn = sr.nextInt();
        System.out.println("Write second");
        int sc = sr.nextInt();
        Alarm a = new Alarm();
        a.checkAlarm(hr, mn, sc, str);
    }
}