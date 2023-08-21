import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("    Hello! I'm Arona, your Virtual Assistant.\n    What can I do for you today?\n");

        Scanner scanner = new Scanner(System.in);
        String echo = scanner.nextLine().toLowerCase().trim();

        while (!echo.equals("bye")) {
            try {
                String[] strings = echo.split(" ");
                if (!echo.isEmpty()) { // Skips processing when echo is empty.
                    if (echo.equals("list")) { // Lists all current tasks.
                        System.out.println("    " +
                                (tasks.size() == 0
                                        ? "Great job! No tasks right now, enjoy your day!"
                                        : (tasks.size() == 1
                                        ? "Good news! You only have one task:"
                                        : "Hello! Here is your list of tasks:")));
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println("    " + (i + 1) + ". " + tasks.get(i));
                        }
                        System.out.println("");

                    } else if (strings[0].equals("mark")) { // Marks a task.
                        if (strings.length == 1) {
                            throw new DukeException("    Whoops! Don't forget to specify the task.\n");
                        }
                        int index = Integer.valueOf(strings[1]) - 1;
                        if (index < 0 || index >= tasks.size()) {
                            throw new DukeException("    Uh-oh, that task does not exist.\n");
                        }
                        Task task = tasks.get(index);
                        task.mark();

                        System.out.println("    Awesome! I've marked this task as done:");
                        System.out.println("      " + task + "\n");

                    } else if (strings[0].equals("unmark")) { // Unmarks a task.
                        if (strings.length == 1) {
                            throw new DukeException("    Whoops! Don't forget to specify the task.\n");
                        }
                        int index = Integer.valueOf(strings[1]) - 1;
                        if (index < 0 || index >= tasks.size()) {
                            throw new DukeException("    Uh-oh, that task does not exist.\n");
                        }
                        Task task = tasks.get(index);
                        task.unmark();

                        System.out.println("    Sure thing! I've marked this task as not done yet:");
                        System.out.println("      " + task + "\n");

                    } else if (strings[0].equals("todo")) { // Adds a todo task.
                        if (strings.length == 1) {
                            throw new DukeException("    Oh no! You forgot to specify the task!\n");
                        }
                        String description = String.join(" ", Arrays.copyOfRange(strings, 1, strings.length));
                        Todo todo = new Todo(description);
                        tasks.add(todo);

                        System.out.println("    Got it! I've added this task:");
                        System.out.println("      " + todo);
                        System.out.println("    Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.\n");

                    } else if (strings[0].equals("deadline")) { // Adds a deadline task.
                        if (strings.length == 1) {
                            throw new DukeException("    Oh no! You forgot to specify the task!\n");
                        }
                        if (!echo.contains("/")) {
                            throw new DukeException("    Whoopsie! The deadline seems a bit confused. Please use '/by' to set it.\n");
                        }
                        int index = -1;
                        for (int i = 0; i < strings.length; i++) {
                            if (strings[i].charAt(0) == '/') {
                                index = i;
                                strings[i] = strings[i].substring(1) + ":";
                                break;
                            }
                        }
                        String description = String.join(" ", Arrays.copyOfRange(strings, 1, index));
                        String by = String.join(" ", Arrays.copyOfRange(strings, index, strings.length));
                        Deadline deadline = new Deadline(description, by);
                        tasks.add(deadline);

                        System.out.println("    Got it! I've added this task:");
                        System.out.println("      " + deadline);
                        System.out.println("    Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.\n");

                    } else if (strings[0].equals("event")) { // Adds an event task.
                        if (strings.length == 1) {
                            throw new DukeException("    Oh no! You forgot to specify the event!\n");
                        }
                        int count = 0;
                        for (int i = 0; i < echo.length(); i++) {
                            if (echo.charAt(i) == '/') {
                                count++;
                            }
                        }
                        if (count != 2) { //echo should contain two '/'.
                            throw new DukeException("    Uh-oh! This event seems to have lost its way. Please use '/from' and '/to' to set it.\n");
                        }
                        int indexStart = -1;
                        int indexEnd = -1;
                        boolean first = true;
                        for (int i = 0; i < strings.length; i++) {
                            if (strings[i].charAt(0) == '/') {
                                if (first) {
                                    indexStart = i;
                                    first = false;
                                    strings[i] = strings[i].substring(1) + ":";
                                } else {
                                    indexEnd = i;
                                    strings[i] = strings[i].substring(1) + ":";
                                    break;
                                }
                            }
                        }
                        String description = String.join(" ", Arrays.copyOfRange(strings, 1, indexStart));
                        String from = String.join(" ", Arrays.copyOfRange(strings, indexStart, indexEnd));
                        String to = String.join(" ", Arrays.copyOfRange(strings, indexEnd, strings.length));
                        Event event = new Event(description, from, to);
                        tasks.add(event);

                        System.out.println("    Got it! I've added this task:");
                        System.out.println("      " + event);
                        System.out.println("    Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.\n");
                    } else if (strings[0].equals("delete")) { // Deletes a task.
                        if (strings.length == 1) {
                            throw new DukeException("    Whoops! Don't forget to specify the task to be deleted.\n");
                        }
                        int index = Integer.valueOf(strings[1]) - 1;
                        if (index < 0 || index >= tasks.size()) {
                            throw new DukeException("    Uh-oh, that task does not exist.\n");
                        }
                        Task task = tasks.get(index);
                        tasks.remove(index);

                        System.out.println("    Sure thing! I've removed this task:");
                        System.out.println("      " + task + "\n");
                        System.out.println("    Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.\n");

                    } else { // Invalid arguments
                        throw new DukeException("    Oops! I'm not quite sure what that means...\n");
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                echo = scanner.nextLine().toLowerCase().trim(); // Reads next line
            }
        }
        System.out.println("    Goodbye. See you soon!"); // Exits the programme.
    }
}

