package hwr.oop.todo;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProjectMenu{

	
	public void openCreate()
	{
		Console.clear();
		System.out.println("What's the name of the project?");
		
		String newProjectName = Console.input.next();
		
		System.out.println("What's the deadline of the project? Format: YYYY-MM-DD (optional, input 'no')");
		
		String deadlineStr = Console.input.next();
		
		LocalDate dealine = null;
		
		System.out.println("input: " + deadlineStr);
		
		if(deadlineStr != null)
		{
			dealine = LocalDate.parse(deadlineStr);			
		}
		else
		{
			System.out.println("input was null!");
		}
		
		
		
		Project testproject = new Project(newProjectName, new ArrayList<Task>(), dealine); // TODO: Save project in Application class
		
	}
	
}
