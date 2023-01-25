package duke.tasklist;

import duke.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Represents the list of tasks that the user has entered into the chatbot.
 */
public class TaskList {
    /** List that stores all the Task objects. */
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList instance.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Gets number of tasks in the task list.
     *
     * @return the number of tasks in the task list.
     */
    public int getSizeOfTaskList() {
        return this.tasks.size();
    }

    /**
     * Gets the task at a specified index of the list.
     *
     * @param index The index in the list from which to retrieve the task.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds a Task into the TaskList.
     */
    public void addTask(Task taskToAdd) {
        this.tasks.add(taskToAdd);
    }

    /**
     * Deletes a Task from the TaskList, at a specified index.
     *
     * @param index Index of the task which is to be deleted.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }
    /**
     * Prints out all the user tasks that have been entered by the user thus far.
     */
    public void printUserTasks() {
        Ui.printStraightLine();
        if (tasks.size() == 0) {
            System.out.println("There are currently no tasks in your list.");
            Ui.printStraightLine();
            return;
        }
        System.out.println("Here are the tasks in your list:");
        int numberOfTasks= tasks.size();
        //Process each task in the storage
        for (int i = 0; i < numberOfTasks; i = i + 1) {
            String numbering = Integer.toString(i + 1) + ". ";
            String output = numbering + tasks.get(i).getStatusOfTaskInString();
            System.out.println(output);
        }
        Ui.printStraightLine();
    }





}
