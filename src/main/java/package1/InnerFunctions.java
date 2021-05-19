package package1;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class InnerFunctions {

    private List<Task> listOfTasks = new LinkedList<>();
    private Scanner scan = new Scanner(System.in);

    public void loadFromFile() {

        listOfTasks.clear();
        try (Scanner sc = new Scanner(new File("qweqwe1.txt"))) {
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                String[] arr = str.split(" ");

                // месяц день часы минуты name
                int month = Integer.parseInt(arr[0]);
                int day = Integer.parseInt(arr[1]);
                int hour = Integer.parseInt(arr[2]);
                int minute = Integer.parseInt(arr[3]);
                String info = sc.nextLine();

                listOfTasks.add(new Task(info, new GregorianCalendar(2021, month, day, hour, minute)));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Collections.sort(listOfTasks);
        //System.out.println(listOfTasks);
    }

    public void writeToFile() {
        //System.out.println("file with DataBase was updated");

        File file = new File("src/main/resources/qweqwe1.txt");
        file.delete();

        try (FileWriter writer = new FileWriter("src/main/resources/qweqwe1.txt")) {
            for (Task task : listOfTasks) {
                writer.write(task.toStringFileFormat() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Calendar createDateOfTask() {
        Calendar gc = null;
        while (true) {
            String str = scan.nextLine();
            if (str.equals("0")) {
                return gc;
            }
            String[] input = str.split("[ :.]");

            try {
                int month = Integer.parseInt(input[0]);
                int day = Integer.parseInt(input[1]);
                int hour = Integer.parseInt(input[2]);
                int minute = Integer.parseInt(input[3]);
                if (month < 1 || month > 12 || day < 1 || day > 28 || hour < 0 || hour > 25 || minute < 0 || minute > 59 || input.length > 4)
                    throw new NumberFormatException();
                gc = new GregorianCalendar(2021, month-1, day, hour, minute);
                break;
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Wrong input, try again please");
            }

        }
        return gc;
    }

    public void printNextTask() {
        for (Task task : listOfTasks) {
            if (task.getCalendar().after(new GregorianCalendar())) {
                System.out.println(task);
                break;
            }
        }

    }

    public void printTasksForToday() {
        for (Task task : listOfTasks) {
            GregorianCalendar gc = new GregorianCalendar();
            if (task.getCalendar().after(gc) &&
                    task.getCalendar().get(Calendar.MONTH) == gc.get(Calendar.MONTH) &&
                    task.getCalendar().get(Calendar.DATE) == gc.get(Calendar.DATE)) {

                System.out.println(task);
            }
        }
    }

    public int printAllTasks() {
        int index = 0;
        for (Task task : listOfTasks) {
            if (task.getCalendar().after(new GregorianCalendar())) {
                index++;
                System.out.println(index + ") "+task);

            }
        }
        return index;
    }

    public void editTask() {
        System.out.println("(Enter '0' to return to main menu)\n");

        int index = printAllTasks();
        System.out.println("\nEnter number of task, that you want to edit");
        String str = scan.nextLine();
        if (str.equals("0")) return;
        int numberOfTaskToEdit;
        try{
            numberOfTaskToEdit = Integer.parseInt(str);
            if(numberOfTaskToEdit <1 || numberOfTaskToEdit > index)
                throw new NumberFormatException();
        } catch (NumberFormatException e){
            System.out.println("Wrong input");
            return;
        }

        index =0;
        for (Task task : listOfTasks) {
            if (task.getCalendar().after(new GregorianCalendar())) {
                index++;
            }
            if (index == numberOfTaskToEdit) {
                System.out.println("Editing task: \n" + task);
                System.out.println("Do you want to change date('1') or description('2') of a task?");
                String answer = scan.nextLine();
                switch (answer) {
                    case "1": {
                        System.out.println("Enter new date and time of a task: mm.dd hh:mm");
                        Calendar gc = createDateOfTask();
                        if (gc == null) return;
                        task.setCalendar(gc);
                        System.out.println("Task was successfully edited");
                        break;
                    }
                    case "2": {
                        System.out.println("Enter new task description");
                        String info = scan.nextLine();
                        task.setInfo(info);
                        System.out.println("Task was successfully edited");
                        break;
                    }
                    case "0": {
                        return;
                    }
                    default: {
                        System.out.println("Wrong input");
                    }
                }
            }
        }

        Collections.sort(listOfTasks);
        writeToFile();
    }

    public void addTask() {
        System.out.println("(Enter '0' to return to main menu)\n");
        System.out.println("Enter date and time of a task: mm.dd hh:mm");

        Calendar gc = createDateOfTask();
        if (gc == null) return;

        System.out.println("Enter task description");
        String info = scan.nextLine();
        listOfTasks.add(new Task(info, gc));
        Collections.sort(listOfTasks);
        writeToFile();
        System.out.println("New task was successfully created");
    }

    public void deleteTask() {

        int index = printAllTasks();
        System.out.println("\nEnter number of task, that you want to delete");
        String str = scan.nextLine();
        int numberOfTaskToDelete;
        try{
            numberOfTaskToDelete = Integer.parseInt(str);
            if(numberOfTaskToDelete <1 || numberOfTaskToDelete > index)
                throw new NumberFormatException();
        } catch (NumberFormatException e){
            System.out.println("Wrong input");
            return;
        }

        index =0;
        for (Task task : listOfTasks) {
            if (task.getCalendar().after(new GregorianCalendar())) {
                index++;
            }
            if (index == numberOfTaskToDelete) {
                System.out.println("Are you sure that you want to delete this task:\n" + task + "\n y/n ?");
                String answer = scan.nextLine();
                switch (answer) {
                    case "y": {
                        listOfTasks.remove(task);
                        writeToFile();
                        System.out.println("Task was successfully deleted");
                        return;
                    }
                    case "n": {
                        break;
                    }
                    default: {
                        System.out.println("Wrong input");
                    }
                }
            }
        }
    }

}
