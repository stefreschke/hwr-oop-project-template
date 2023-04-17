package hwr.oop.todo;

public class MainMenu implements IMenu {

	@Override
	public void open() {
		System.out.println("Welcome to the ultimate-u-never-forget ToDo List");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\n[MainMenu]");
		System.out.println("What is it that you want to do? To proceed further, enter the action code given inside the [ ]");
	}

}
