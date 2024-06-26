package consoleBoard;

import java.util.Scanner;

public class ConsoleBoard {
	private final int JOIN = 1;
	private final int LEAVE = 2;
	private final int MODIFY_USER = 3;
	private final int LOG_IN = 4;
	private final int LOG_OUT = 5;
	private final int BEFORE_PAGE = 6;
	private final int AFTER_PAGE = 7;
	private final int ADD_CONTENTS = 8;
	private final int DELETE_CONTENTS = 9;
	private final int MODIFY_CONTENTS = 10;
	private final int VIEW_CONTENTS = 11;
	private final int WRITE_NOTICE = 12;
	private final int EXIT = 0;
	
	public final static String USER_FILE_NAME = "users.txt";
	public final static String BOARD_FILE_NAME = "boards.txt";
	public final static String NOTICE_FILE_NAME = "notices.txt";
	
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
		System.out.println("[3] 회원정보 수정");
		System.out.println("[4] 로그	인");
		System.out.println("[5] 로그아웃");
		System.out.println("[6] 이전");
		System.out.println("[7] 이후");
		System.out.println("[8] 글 작성");
		System.out.println("[9] 글 삭제");
		System.out.println("[10] 글 수정");
		System.out.println("[11] 글 조회");
		if (log == 0) {
			System.out.println("[12] 공지 작성");
		}
		System.out.println("[0] 종	료");
	}

	private boolean isPossible(int choice) {
		if (log != -1 && (choice == JOIN || choice == LOG_IN)) {
			System.err.println("로그 아웃 후 이용가능합니다.");
			return false;
		} else if (log == -1 && (choice == LEAVE || choice == LOG_OUT || choice == ADD_CONTENTS
				|| choice == DELETE_CONTENTS || choice == MODIFY_CONTENTS || choice == MODIFY_USER)) {
			System.err.println("로그 인 후 이용가능합니다.");
			return false;
		}
		return true;
	}

	private void runMenu(int choice) { // log==0 어드민일땐 글작성,가입,탈퇴,글 수정을 막는다.
		if (!isPossible(choice))
			return;

		if (choice == JOIN && log != 0)
			userManager.createUser();
		else if (choice == LEAVE && log != 0)
			userManager.deleteUser();
		else if (choice == MODIFY_USER && log != 0)
			modifyUser();
		else if (choice == LOG_IN)
			userManager.login();
		else if (choice == LOG_OUT)
			userManager.logout();
		else if (choice == BEFORE_PAGE)
			boardManager.beforePage();
		else if (choice == AFTER_PAGE)
			boardManager.afterPage();
		else if (choice == ADD_CONTENTS && log != 0)
			addContents();
		else if (choice == DELETE_CONTENTS)
			deleteContents();
		else if (choice == MODIFY_CONTENTS && log != 0)
			modifyContents();
		else if (choice == VIEW_CONTENTS)
			boardManager.viewContents();
		else if (choice == WRITE_NOTICE && log == 0)
			boardManager.createNotification();
		else if (choice == EXIT)
			isRun = false;
	}

	private void addContents() {
		String[] idAndPassword = userManager.getUserIdAndPassword();
		String id = idAndPassword[0];
		String password = idAndPassword[1];
		boardManager.writing(id, password);
	}

	private void deleteContents() {
		String[] idAndPassword = userManager.getUserIdAndPassword();
		String id = idAndPassword[0];
		String password = idAndPassword[1];
		boardManager.deleteContents(id, password);
	}

	private void modifyContents() {
		String[] idAndPassword = userManager.getUserIdAndPassword();
		String id = idAndPassword[0];
		String password = idAndPassword[1];
		boardManager.updateContents(id, password);
	}

	private void modifyUser() {
		User user = userManager.modifyUserPassword();
		if (user == null)
			return;
		String id = user.getId();
		String password = user.getPassword();
		boardManager.modifyBoardsPassword(id, password);
	}

	public void run() {
		while (isRun) {
			boardManager.viewAllTitle();
			printMenu();
			int choice = inputNumber("Menu");
			runMenu(choice);
		}
	}
}
