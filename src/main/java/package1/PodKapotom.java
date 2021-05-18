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
        System.out.println("\n        ShowTask menu");
        System.out.println("Enter:\n 1 - to see next task;\n 2 - to see all tasks for today;" +
                "\n 3 - to see all the tasks;\n 0 - to return to main menu");
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
                    System.out.println("Wrong input, try again please");
                }
            }
        }
        System.out.println("Enter any key to return to main menu");
        String str = scan.nextLine();
    }

    public void editTask() {
        System.out.println("\n        EditTask menu");
        functions.editTask();
    }

    public void addTask() {
        System.out.println("\n        CreateTask menu");
        functions.addTask();

    }

    public void deleteTask() {
        System.out.println("\n        DeleteTask menu");
        functions.deleteTask();
    }
}
