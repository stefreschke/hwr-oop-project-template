package hwr.oop.todo;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProjectMenu extends Menu{

	
	public void openCreate()
	{
		clearConsole();
		System.out.println("What's the name of the project?");
		
		String newProjectName = input.next();
		
		System.out.println("What's the deadline of the project? Format: YYYY-MM-DD (optional, input 'no')");
		
		String deadlineStr = input.next();
		
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
