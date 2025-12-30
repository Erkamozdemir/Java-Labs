package lab6;

import java.util.ArrayList;
import java.util.Scanner;

public class Lab06_Q2 {
    public static Scanner in = new Scanner(System.in);
    public static boolean isDynamic = false;

    public static String[] tasknames;
    public static int[] priorities;

    public static ArrayList<String> tasknamesList;
    public static ArrayList<Integer> prioritiesList;

    public static void full() {
        if (!isDynamic) {
            boolean isFullArray = true;
            for (int i = 0; i < tasknames.length && isFullArray; i++) {
                if (tasknames[i] == null) {
                    isFullArray = false;
                }
            }
            if (isFullArray) {
                System.out.println("Array full! Switching to dynamic ArrayList...");
                tasknamesList = new ArrayList<>();
                prioritiesList = new ArrayList<>();
                for (int i = 0; i < tasknames.length; i++) {
                    tasknamesList.add(tasknames[i]);
                    prioritiesList.add(priorities[i]);
                }
                isDynamic = true;
            }
        }
    }

    public static int findTaskIndex(String name) {
        int index = -1;
        if (isDynamic) {
            for (int i = 0; i < tasknamesList.size() && index == -1; i++) {
                if (tasknamesList.get(i).equalsIgnoreCase(name)) {
                    index = i;
                }
            }
        } else {
            for (int i = 0; i < tasknames.length && index == -1; i++) {
                if (tasknames[i] != null && tasknames[i].equalsIgnoreCase(name)) {
                    index = i;
                }
            }
        }
        return index;
    }

    public static void addTask(String name, int priority) {
        full();

        int existingIndex = findTaskIndex(name);
        boolean isAdded = false;

        if (existingIndex != -1) {
            System.out.println("Task already exists!");
            isAdded = true;
        }

        if (!isAdded) {
            if (isDynamic) {
                tasknamesList.add(name);
                prioritiesList.add(priority);
                System.out.println("Task added successfully!");
            } else {
                for (int i = 0; i < tasknames.length && !isAdded; i++) {
                    if (tasknames[i] == null) {
                        tasknames[i] = name;
                        priorities[i] = priority;
                        System.out.println("Task added successfully!");
                        isAdded = true;
                    }
                }
            }
        }
    }

    public static void removeTask(String name) {
        int index = findTaskIndex(name);

        if (index != -1) {
            if (isDynamic) {
                tasknamesList.remove(index);
                prioritiesList.remove(index);
            } else {
                tasknames[index] = null;
                priorities[index] = 0;
            }
            System.out.println("Task removed successfully.");
        } else {
            System.out.println("Task not found!");
        }
    }

    public static void updateTaskPriority(String name, int newPriority) {
        int index = findTaskIndex(name);

        if (index != -1) {
            if (isDynamic) {
                prioritiesList.set(index, newPriority);
            } else {
                priorities[index] = newPriority;
            }
        }
    }

    public static void searchTask(String name) {
        int index = findTaskIndex(name);

        if (index != -1) {
            if (isDynamic) {
                int priority = prioritiesList.get(index);
                System.out.println("Task found. Priority: " + priority);
            } else {
                int priority = priorities[index];
                System.out.println("Task found. Priority: " + priority);
            }
        } else {
            System.out.println("Task not found!");
        }
    }

    public static void viewAllTasks() {
        boolean hasTask = false;

        if (isDynamic) {
            if (!tasknamesList.isEmpty()) {
                for (int i = 0; i < tasknamesList.size(); i++) {
                    System.out.println((i + 1) + ". " + tasknamesList.get(i) +
                            " (Priority " + prioritiesList.get(i) + ")");
                }
                hasTask = true;
            }
        } else {
            for (int i = 0; i < tasknames.length; i++) {
                if (tasknames[i] != null) {
                    System.out.println((i + 1) + ". " + tasknames[i] +
                            " (Priority " + priorities[i] + ")");
                    hasTask = true;
                }
            }
        }

        if (!hasTask) {
            System.out.println("No tasks to display.");
        }
    }

    public static void exit() {
        System.out.println("Quit!");
    }

    public static void main(String[] args) {
        System.out.print("Enter initial task capacity: ");
        int capacity = in.nextInt();
        tasknames = new String[capacity];
        priorities = new int[capacity];
        boolean isQuit = false;

        while (!isQuit) {
            System.out.print(
                    "=== Task Scheduler ===\n" + "1. Add Task\n" + "2. Remove Task\n" + "3. Update Task Priority\n"
                            + "4. Search Task\n" + "5. View All Tasks\n" + "6. Exit\n" + "Choose an option: ");
            if (in.hasNextInt()) {
                int option = in.nextInt();
                if (option == 1) {
                    in.nextLine();
                    System.out.print("Enter task name: ");
                    String taskname = in.nextLine();
                    System.out.print("Enter priority (1=High, 2=Medium, 3=Low): ");
                    int priority = in.nextInt();
                    if (!(priority == 1 || priority == 2 || priority == 3)) {
                        System.out.println("Enter a valid priority!");
                    } else {
                        addTask(taskname, priority);
                        System.out.println();
                    }

                } else if (option == 2) {
                    in.nextLine();
                    System.out.print("Enter a task name to remove: ");
                    String taskname = in.nextLine();
                    removeTask(taskname);
                    System.out.println();

                } else if (option == 3) {
                    in.nextLine();
                    System.out.print("Enter task name to update: ");
                    String taskname = in.nextLine();
                    int index = findTaskIndex(taskname);

                    if (index == -1) {
                        System.out.println("Task not found!");
                    } else {
                        int currentPriority = -1;
                        if (isDynamic) {
                            currentPriority = prioritiesList.get(index);
                        } else {
                            currentPriority = priorities[index];
                        }
                        System.out.println("Current priority: " + currentPriority);
                        System.out.print("Enter new priority (1=High, 2=Medium, 3=Low): ");
                        int newPriority = in.nextInt();
                        if (!(newPriority == 1 || newPriority == 2 || newPriority == 3)) {
                            System.out.println("Enter a valid priority!");
                        } else {
                            if (newPriority == currentPriority) {
                                System.out
                                        .println("New priority cannot be the same as the current priority. Try again.");
                            } else {
                                updateTaskPriority(taskname, newPriority);
                                System.out.println("Priority updated.");
                            }
                        }

                    }
                    System.out.println();

                } else if (option == 4) {
                    in.nextLine();
                    System.out.print("Enter a task name to search: ");
                    String taskname = in.nextLine();
                    searchTask(taskname);
                    System.out.println();

                } else if (option == 5) {
                    viewAllTasks();
                    System.out.println();

                } else if (option == 6) {
                    exit();
                    isQuit = true;

                } else {
                    System.out.println("Choose a valid option!");
                }
            } else {
                in.nextLine();
                System.out.println("Enter an integer between 1-6!");
            }

        }
    }
}