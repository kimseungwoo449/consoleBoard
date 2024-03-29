package consoleBoard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Load {
	private static Load instance = new Load();

	private FileReader fr;
	private BufferedReader br;
	private File userFile;
	private File boardFile;
	private File noticeFile;

	private Load() {
		userFile = new File(ConsoleBoard.USER_FILE_NAME);
		boardFile = new File(ConsoleBoard.BOARD_FILE_NAME);
		noticeFile = new File(ConsoleBoard.NOTICE_FILE_NAME);
	}

	public ArrayList<User> loadUserData() {
		ArrayList<User> users = null;
		String data = loadFile(userFile);
		if (data == null)
			return users;
		String info[] = data.split("\n");

		if (info[0] != null) {
			users = new ArrayList<User>();
			for (int i = 0; i < info.length; i++) {
				String targetUser[] = info[i].split(",");
				String id = targetUser[0];
				String password = targetUser[1];
				User user = new User(id, password);

				users.add(user);
			}
		}

		return users;
	}

	private String loadFile(File file) {
		String data = null;
		if (file.exists()) {
			data = "";
			try {
				fr = new FileReader(file);
				br = new BufferedReader(fr);

				while (br.ready()) {
					data += br.readLine();
					data += "\n";
				}

				if (data.length() > 1)
					data = data.substring(0, data.length() - 1);

				System.out.println("로드 성공");
			} catch (Exception e) {
				System.err.println("로드 실패");
			}
		}
		return data;
	}

	public Map<Integer, Board> loadBoardData() {
		Map<Integer, Board> board = null;
		String data = loadFile(boardFile);
		if (data == null)
			return board;
		String[] info = data.split("\\/");

		if (info[0] != null) {
			board = new HashMap<Integer, Board>();
			for (int i = 0; i < info.length; i++) {
				String[] boardData = info[i].split(",");
				int numberOfContents = Integer.parseInt(boardData[0]);
				String id = boardData[1];
				String password = boardData[2];
				String title = boardData[3];
				String detail = boardData[4];
				Board targetBoard = new Board(id, password, title, detail);
				board.put(numberOfContents, targetBoard);
			}
		}
		return board;
	}

	public ArrayList<Notification> loadNoticeData() {
		ArrayList<Notification> notices = null;
		String data = loadFile(noticeFile);
		if (data == null)
			return notices;
		String[] info = data.split("\\/");

		if (info[0] != null) {
			notices = new ArrayList<Notification>();
			for (int i = 0; i < info.length; i++) {
				String[] noticeData = info[i].split(",");
				String title = noticeData[0];
				String detail = noticeData[1];
				Notification notice = new Notification(title, detail);
				notices.add(notice);
			}
		}
		return notices;
	}

	public static Load getInstance() {
		return instance;
	}
}
