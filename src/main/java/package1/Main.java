package package1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Alarm w = new Alarm();
        PodKapotom podKapotom = new PodKapotom(new InnerFunctions());
        System.out.println("\nHello dear friend, I am a console task manager.\n" +
                "Hope you will enjoy using this app.");
        while (true) {
            printMainMenu(); // тут похимичить надо, чтобы красиво все выводилось на экран, когда приходим в главое меню
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
                    GetWeather q = new GetWeather();
                    q.weather();
                }
                case "exit": {
                    return;
                }
                default: {
                    System.out.println("Wrong, try again please");
                }
            }

        }
    }

    private static void printMainMenu() {

        System.out.println("\n        Main menu\n");

        System.out.println("Enter '1' for show tasks");
        System.out.println("Enter '2' for add tasks");
        System.out.println("Enter '3' for edit tasks");
        System.out.println("Enter '4' for delete tasks");
        System.out.println("Enter '5' for open another app");
        System.out.println("Enter '6' for add pop-up notification");
        System.out.println("Enter '7' for show weather");
        System.out.println("Write 'exit' to turn off app");
        //Тут вывод строк главного меню с вариантами действий
    }
}
