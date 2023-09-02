package arona.ui;

import arona.task.Task;
import arona.task.TaskList;

public class Ui {

    public static void showWelcomeMessage() {
        System.out.println("Hello! I'm Arona, your Virtual Assistant.");
        System.out.println("What can I do for you today?\n");
    }

    public static void showGoodbyeMessage() {
        System.out.println("Goodbye. See you soon!");
    }

    public static void showTaskList(TaskList tasks) {
        if (tasks.getTasks().isEmpty()) {
            System.out.println("Great job! No tasks right now, enjoy your day!\n");
        } else {
            System.out.println("Hello! Here is your list of tasks:");
            for (int i = 0; i < tasks.getTasks().size(); i++) {
                System.out.println((i + 1) + ". " + tasks.getTasks().get(i));
            }
            System.out.println("");
        }
    }
    public static void showTaskAdded(Task task, int totalTasks) {
        System.out.println("Got it! I've added this arona.task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + totalTasks + (totalTasks == 1 ? " task" : " tasks") + " in the list.\n");
    }
    public static void showTaskRemoved(Task task, int totalTasks) {
        System.out.println("Sure thing! I've removed this arona.task:");
        System.out.println("  " + task + "\n");
        System.out.println("Now you have " + totalTasks + (totalTasks == 1 ? " task" : " tasks") + " in the list.\n");
    }

    public static void showTaskMarkedAsDone(Task task) {
        System.out.println("Awesome! I've marked this arona.task as done:");
        System.out.println("  " + task + "\n");
    }

    public static void showTaskUnmarked(Task task) {
        System.out.println("Sure thing! I've marked this arona.task as not done yet:");
        System.out.println("  " + task + "\n");
    }
    public static void showTaskDoesNotExist(int taskIndex) {
        System.out.println("Sorry... I can't find the arona.task. Please input the arona.task number correctly!\n");
    }

    public static void showErrorMessage(Exception e) {
        System.out.println(e.getMessage() + "\n");
    }

    public static void showInvalidArgumentMessage() {
        System.out.println("Oops! I'm not quite sure what that means...\n");
    }

}