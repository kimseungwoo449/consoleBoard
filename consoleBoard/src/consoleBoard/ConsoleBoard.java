package consoleBoard;

import java.util.Scanner;

public class ConsoleBoard {
	public static Scanner sc = new Scanner(System.in);
	
	public static int log;
	
	private UserManager userManager;
	private BoardManager boardManager;
	
	public ConsoleBoard() {
		this.log = -1;
		boardManager = BoardManager.getInstance();
		userManager = UserManager.getInstance();
	}
	
	public static String inputString(String message) {
		System.out.print(message +" : ");
		return sc.next();
	}
	
	public static String inputString() {
		return sc.next();
	}
	
	public static int inputNumber(String message) {
		int number = -1;
		try {
			System.out.print(message +" : ");
			String input = sc.next();
			number = Integer.parseInt(input);
		} catch (Exception e) {
			System.err.println("숫자로 입력하세요.");
		}
		return number;
	}
}
