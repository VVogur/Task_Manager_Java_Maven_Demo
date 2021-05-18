package package1;

import java.util.Scanner;

public class PodKapotom {
    private InnerFunctions functions;

    public PodKapotom(InnerFunctions functions) {

        this.functions = functions;
        functions.loadFromFile();
    }

    Scanner scan = new Scanner(System.in);

    public void showTask() {
        System.out.println("The method of displaying tasks has been launched");
        System.out.println("Choose number:\n 1 - for show the next task;\n 2 - for show the tasks for today;" +
                "\n 3 - for show all tasks;\n 0 - return to main menu");
        cycle:
        while (true) {
            String str = scan.nextLine();
            switch (str) {
                case "1": {
                    System.out.println("next task is:");
                    functions.printNextTask();
                    break cycle;
                }
                case "2": {
                    System.out.println("tasks for today:");
                    functions.printTasksForToday();
                    break cycle;
                }
                case "3": {
                    System.out.println("all future tasks:");
                    functions.printAllTasks();
                    break cycle;
                }
                case "0": {
                    return;
                }
                default: {
                    System.out.println("Wrong, try again please");
                }
            }
        }
        System.out.println("Type smth to return to main menu");
        String str = scan.nextLine();
    }

    public void editTask() {
        System.out.println("\nThe method of editing tasks has been launched");
        functions.editTask();
    }

    public void addTask() {
        System.out.println("\nThe method of add tasks has been launched");
        functions.addTask();

    }

    public void deleteTask() {
        System.out.println("\nThe method of delete tasks has been launched");
        functions.deleteTask();
    }
}
