package package1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Alarm w = new Alarm();
        PodKapotom podKapotom = new PodKapotom(new InnerFunctions());
        System.out.println("\nHello dear friend, I am a console task manager.\n" +
                "Hope you will enjoy using this app ;)");
        String check = "Correct input";
        while (true) {
            if(check.equals("Correct input")){
            printMainMenu();// тут похимичить надо, чтобы красиво все выводилось на экран, когда приходим в главое меню
            }
            check = "Correct input";
            String str = sc.nextLine();
            switch (str) {
                case "1": {
                    podKapotom.showTask();
                    // вызов метода showTask
                    break;
                }
                case "2": {
                    podKapotom.addTask();
                    // вызов метода addTask
                    break;
                }
                case "3": {
                    podKapotom.editTask();
                    // вызов метода editTask
                    break;
                }
                case "4": {
                    podKapotom.deleteTask();
                    // вызов метода deleteTask
                    break;
                }
                case "5": {
                    AppOpen q = new AppOpen();
                    q.appOpenMethod();
                    break;
                }
                case "6": {
                    w.alarmSet();
                    break;
                }
                case "7":{
                    WeatherManager.start();
                    break;
                }
                case "q": {
                    return;
                }
                default: {
                    System.out.println("Wrong input, try again please");
                    check = "Wrong input";
                }
            }

        }
    }

    private static void printMainMenu() {

        System.out.println("\n        Main menu");

        System.out.println("Enter '1' to see available tasks");
        System.out.println("Enter '2' to create new task");
        System.out.println("Enter '3' to edit task");
        System.out.println("Enter '4' to delete task");
        System.out.println("Enter '5' to open some desktop applications");
        System.out.println("Enter '6' to set an alarm");
        System.out.println("Enter '7' to check weather");
        System.out.println("Enter 'q' to exit");
        //Тут вывод строк главного меню с вариантами действий
    }
}
