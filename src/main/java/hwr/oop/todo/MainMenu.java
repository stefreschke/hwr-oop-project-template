package hwr.oop.todo;

public class MainMenu {
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
		
		promptInput();
	}
	
	/**
	 * Prompts and waits for user console input and handles invalid input (= a not integer input).
	 */
	private void promptInput()
	{
		System.out.print("> ");
		int inputID = 0;
		while(inputID == 0) {
			try {
				inputID = Integer.parseInt(Console.input.nextLine());			
			}
			catch(NumberFormatException ex) {
				System.out.println("Invalid input. Please try again");
			}			
		}
		
		handleInput(inputID);
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
				System.out.println("Invalid ID. Please enter a valid ID!");
				promptInput();
				break;
		}
	}

}