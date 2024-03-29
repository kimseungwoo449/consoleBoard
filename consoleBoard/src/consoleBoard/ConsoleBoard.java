package consoleBoard;

import java.util.Scanner;

public class ConsoleBoard {
	private final int JOIN = 1;
	private final int LEAVE = 2;
	private final int LOG_IN = 3;
	private final int LOG_OUT = 4;
	private final int ADD_CONTENTS = 5;
	private final int DELETE_CONTENTS = 6;
	private final int MODIFY_CONTENTS = 7;
	private final int EXIT = 0;

	public static Scanner sc = new Scanner(System.in);

	public static int log;
	private boolean isRun;

	private UserManager userManager;
	private BoardManager boardManager;

	public ConsoleBoard() {
		this.log = -1;
		this.isRun = true;
		boardManager = BoardManager.getInstance();
		userManager = UserManager.getInstance();
	}

	public static String inputString(String message) {
		System.out.print(message + " : ");
		return sc.next();
	}

	public static String inputString() {
		return sc.next();
	}

	public static int inputNumber(String message) {
		int number = -1;
		try {
			System.out.print(message + " : ");
			String input = sc.next();
			number = Integer.parseInt(input);
		} catch (Exception e) {
			System.err.println("숫자로 입력하세요.");
		}
		return number;
	}

	private void printMenu() {
		System.out.println("[1] 회원가입");
		System.out.println("[2] 회원탈퇴");
		System.out.println("[3] 로그	인");
		System.out.println("[4] 로그아웃");
		System.out.println("[5] 글 작성");
		System.out.println("[6] 글 삭제");
		System.out.println("[7] 글 수정");
		System.out.println("[0] 종	료");
	}

	private void runMenu(int choice) {
		if (choice == JOIN)
			userManager.createUser();
		else if (choice == LEAVE)
			userManager.deleteUser();
		else if (choice == LOG_IN)
			userManager.login();
		else if (choice == LOG_OUT)
			userManager.logout();
		else if (choice == ADD_CONTENTS) {
			String[] idAndPassword = userManager.getUserIdAndPassword();
			String id = idAndPassword[0];
			String password = idAndPassword[1];
			boardManager.writing(id, password);
		} else if (choice == DELETE_CONTENTS) {
			String[] idAndPassword = userManager.getUserIdAndPassword();
			String id = idAndPassword[0];
			String password = idAndPassword[1];
			boardManager.deleteContents(id, password);
		} else if (choice == MODIFY_CONTENTS) {
			String[] idAndPassword = userManager.getUserIdAndPassword();
			String id = idAndPassword[0];
			String password = idAndPassword[1];
			boardManager.updateContents(id, password);
		} else if (choice == EXIT)
			isRun = false;
	}

	public void run() {
		while (isRun) {
			boardManager.viewAllTitle();
			printMenu();
			int choice = inputNumber("Menu");
		}
	}
}
