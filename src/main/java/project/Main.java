package project;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {


    public static void main(String[] args){

        Project p1 = new Project("Test", LocalDateTime.now(), LocalDate.now());
        p1.addTask(new Task());
        for (Task t : p1.tasks){
            System.out.println(t.name);
        }
    }



}
