package hwr.oop.todo;

import java.util.Scanner;

public class Menu {
	protected final Scanner input;
	
	public Menu()
	{
		this.input = new Scanner(System.in);
	}
	
	public void clearConsole()
	{
		System.out.println("\n\n\n\n\n\n");
	}
}
