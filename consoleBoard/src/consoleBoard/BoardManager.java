package consoleBoard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardManager {
	private final String EXIT = "*";

	private final int TITLE = 0;
	private final int DETAIL = 1;
	private final int RECENT = 1;

	private final int VIEW_CONTENTS_NUMBER = 5;

	private int curPageNumber;
	private int startRow;
	private int endRow;
	private int pageCount;

	private ArrayList<Notification> notifications;
	private Map<Integer, Board> board; // 키값 글번호, value값 board
	private int contentsNumber;
	private static BoardManager instance = new BoardManager();

	private BoardManager() {
		board = new HashMap<>();
		notifications = new ArrayList<Notification>();
		this.contentsNumber = 1;
		this.curPageNumber = 1;
		this.pageCount = 0;
		this.startRow = 1;
		this.endRow = 5;
	}

	public static BoardManager getInstance() {
		return instance;
	}

	// 게시글 작성
	public void writing(String id, String password) {
		Board contents = createNewContents(id, password);

		pushOldContents();
		board.put(RECENT, contents);
		calculatePageCount();
		System.out.println("게시글 등록 완료.");
	}

	private void pushOldContents() {
		board.put(contentsNumber++, null);
		for (int i = board.size(); i >= 1; i--) {
			Board oldBoard = board.get(i - 1);
			board.replace(i, oldBoard);
		}
	}

	private Board createNewContents(String id, String password) {
		String title = ConsoleBoard.inputString("제목");
		String detail = "";
		System.out.println("내용 (*입력시 종료): ");
		while (true) {
			String info = ConsoleBoard.inputString();
			if (info.equals(EXIT))
				break;
			detail += info;
			detail += "\n";
		}
		if(detail.length()>0)
			detail = detail.substring(0,detail.length()-1);

		Board contents = new Board(id, password, title, detail);
		return contents;
	}

	private String[] createNewContents() {
		String title = ConsoleBoard.inputString("제목");
		String detail = "";
		System.out.println("내용 (*입력시 종료): ");
		while (true) {
			String info = ConsoleBoard.inputString();
			if (info.equals(EXIT))
				break;
			detail += info;
			detail += "\n";
		}
		if(detail.length()>0)
			detail = detail.substring(0,detail.length()-1);
		String info[] = new String[2];
		info[TITLE] = title;
		info[DETAIL] = detail;
		return info;
	}

	private void calculatePageCount() {
		if (board.size() % this.VIEW_CONTENTS_NUMBER != 0) {
			this.pageCount = board.size() / this.VIEW_CONTENTS_NUMBER + 1;
		} else
			this.pageCount = board.size() / this.VIEW_CONTENTS_NUMBER;
	}

	// 읽기
	public void viewContents() {
		int number = ConsoleBoard.inputNumber("조회하려는 글 번호");

		if (number < 1)
			return;

		String contents = createStringInfo(number);
		if (contents == null) {
			System.err.println("찾으시는 글번호는 존재하지 않습니다.");
			return;
		}

		System.out.println(contents);
	}

	private Board findContentsByKey(int number) {
		Board temp = null;
		for (Integer key : board.keySet()) {
			if (key == number) {
				temp = board.get(key);
			}
		}
		return temp;
	}

	private String createStringInfo(int number) {
		String info = null;

		Board contents = findContentsByKey(number);

		if (contents == null)
			return info;

		String title = contents.getTitle();
		String detail = contents.getDetail();
		System.out.println(detail);
		info = String.format("제목 : %s\n내용 : %s", title, detail);

		return info;
	}

	public void viewAllTitle() {
		List keySet = new ArrayList(board.keySet());

		Collections.sort(keySet);
		if (notifications.size() > 0) {
			System.out.println("-----------------");
			System.out.println("[공지]");
			for (Notification notification : notifications) {
				String notice = String.format("제목 : %s\n내용 : %s", notification.getTitle(), notification.getDetail());
				System.err.println(notice);
			}
			System.out.println("-----------------");
		}
		System.out.println("-----------------");
		System.out.println("[게시판]");
		for (int i = startRow; i <= endRow; i++) {
			int key = i;
			Board contents = board.get(key);
			if (contents != null) {
				String title = contents.getTitle();
				String id = contents.getId();
				String info = String.format("%d. %s [작성자 : %s]", key, title, id);
				System.out.println(info);
			}
		}
		String data = String.format("[%d/%dpage](%d)", curPageNumber, pageCount, board.size());
		System.out.println(data);
		System.out.println("-----------------");
	}

	// 삭제
	public void deleteContents(String id, String password) {
		int number = ConsoleBoard.inputNumber("삭제하려는 글 번호");

		if (!isPossible(id, password, number))
			return;
		refineBoard(number);
		this.contentsNumber = this.board.size() + 1;
		calculatePageCount();
		System.out.println("게시글 삭제 완료.");
	}

	private void refineBoard(int number) {
		Map<Integer, Board> temp = this.board;
		board = new HashMap<Integer, Board>();

		for (int i = 1; i <= temp.size(); i++) {
			if (i < number) {
				Board board = temp.get(i);
				this.board.put(i, board);
			} else if (i > number) {
				Board board = temp.get(i);
				this.board.put(i - 1, board);
			}
		}
	}

	private boolean isPossible(String id, String password, int number) {
		if (number < 1)
			return false;
		Board contents = findContentsByKey(number);
		if (contents == null) {
			System.err.println("찾으시는 글번호가 존재하지 않습니다.");
			return false;
		}
		if (ConsoleBoard.log == 0)	//어드민은 삭제권한을 지님
			return true;

		String targetId = contents.getId();
		String targetPassword = contents.getPassword();
		if (!(targetId.equals(id) && targetPassword.equals(password))) {
			System.err.println("ID 혹은 PASSWORD가 달라 권한이 없습니다.");
			return false;
		}
		return true;
	}

	// 게시글 수정
	public void updateContents(String id, String password) {
		int number = ConsoleBoard.inputNumber("수정하려는 글 번호");

		if (!isPossible(id, password, number))
			return;

		Board newContents = createNewContents(id, password);
		board.put(number, newContents);
		System.out.println("게시글 수정 완료.");
	}

	// 이전
	public void beforePage() {
		if (curPageNumber - 1 < 1) {
			System.err.println("이전 페이지를 찾을 수 없습니다.");
			return;
		}

		curPageNumber--;
		calculateStartRowAndEndRow();
	}

	private void calculateStartRowAndEndRow() {
		this.startRow = this.curPageNumber * this.VIEW_CONTENTS_NUMBER - 4;
		this.endRow = this.startRow + 4;
	}

	// 이후
	public void afterPage() {
		if (curPageNumber + 1 > pageCount) {
			System.err.println("이후 페이지를 찾을 수 없습니다.");
			return;
		}
		curPageNumber++;
		calculateStartRowAndEndRow();
	}

	public void createNotification() {
		String[] data = createNewContents();
		String title = data[TITLE];
		String detail = data[DETAIL];

		Notification notification = new Notification(title, detail);
		notifications.add(notification);
	}

}
