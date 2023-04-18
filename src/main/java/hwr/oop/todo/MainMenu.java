package hwr.oop.todo;

public class MainMenu extends Menu {
	private final ProjectMenu projectMenu;
	
	public MainMenu()
	{
		projectMenu = new ProjectMenu();
	}
	
	public void open() {
		System.out.println("Welcome to the ultimate-u-never-forget ToDo List");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\n[MainMenu]");
		System.out.println("What is it that you want to do? To proceed further, enter the action code given inside the [ ]");
		System.out.println("[1] Create Project");
		System.out.println("[2] Delete Project");
		System.out.println("[3] Create Tasks (quick create)");
		System.out.println("[4] Create Task (detailed)");
		
		handleInput(input.nextInt());
	}
	
	private void handleInput(int input)
	{
		switch(input)
		{
			case 1:
				projectMenu.openCreate();
				break;
			case 2:
				break;
				
			case 3:
				break;
				
			default:
				break;
		}
	}

}