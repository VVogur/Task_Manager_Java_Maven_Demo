package package1;

import java.io.IOException;
import java.util.Scanner;

public class AppOpen {
    public void appOpenMethod() {
        Scanner sr = new Scanner(System.in);
        Runtime rs = Runtime.getRuntime();

        System.out.println("Enter the:\n" +
                "1. Notepad\n" +
                "2. Paint\n" +
                "3. Calculator\n" +
                "4. Google Chrome\n" +
                "5. Microsoft Edge\n" +
                "6. Explorer\n" +
                "0. Back to the main menu\n");
        try {
            while (true) {
                System.out.println("Write number: ");
                int a = sr.nextInt();
                switch (a) {
                    case 1:
                        rs.exec("notepad");
                        break;
                    case 2:
                        rs.exec("mspaint");
                        break;
                    case 3:
                        rs.exec("calc");
                        break;
                    case 4:
                        rs.exec(new String[]{"cmd", "/c", "start chrome"});
                        break;
                    case 5:
                        rs.exec(new String[]{"cmd", "/c", "start microsoft-edge://google.com"});
                        break;
                    case 6:
                        rs.exec("explorer");
                        break;
                    case 0:
                        return;
                    default:
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Wrong");
        }
    }
}
