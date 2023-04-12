package hwr.oop;

import hwr.oop.project.Project;
import hwr.oop.task.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {


    public static void main(String[] args){

        Project p1 = new Project("Test", LocalDateTime.now(), LocalDate.now());
        Task t =  new Task();
        p1.addTask(t);
        for (Task z : p1.tasks){
            System.out.println(z.name);
        }
        p1.removeTask(t);
        for (Task z : p1.tasks) {
            System.out.println(z.name);
        }
    }



}
