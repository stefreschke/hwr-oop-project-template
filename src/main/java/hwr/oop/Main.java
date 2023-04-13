package hwr.oop;

import hwr.oop.project.Project;
import hwr.oop.task.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {


    public static void main(String[] args){

        Project p1 = new Project("Test", LocalDateTime.now(), LocalDate.now());
    }



}
