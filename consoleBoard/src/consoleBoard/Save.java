package consoleBoard;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Map;

public class Save {
	private FileWriter fw;
	private File userFile;
	private File boardFile;
	private File noticeFile;

	private static Save instance = new Save();

	private Save() {
		userFile = new File(ConsoleBoard.USER_FILE_NAME);
		boardFile = new File(ConsoleBoard.BOARD_FILE_NAME);
		noticeFile = new File(ConsoleBoard.NOTICE_FILE_NAME);
	}

	public void saveUserFile(ArrayList<User> users) {
		String allUsers = "";
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			allUsers += makeUserInfo(user);
			if (i < users.size() - 1)
				allUsers += "\n";
		}
		saveFile(userFile, allUsers);
	}

	private void saveFile(File file, String info) {
		try {
			fw = new FileWriter(file);
			fw.write(info);
			fw.close();
			System.out.println("파일 저장 완료.");
		} catch (Exception e) {
			System.err.println("파일 저장 실패");
		}
	}

	private String makeUserInfo(User user) {
		String info = String.format("%s,%s", user.getId(), user.getPassword());
		return info;
	}

	public void saveBoardFile(Map<Integer, Board> board) {
		String allBoards = "";
		for (int i = 1; i <= board.size(); i++) {
			Board targetBoard = board.get(i);
			allBoards += i + "," + makeBoardInfo(targetBoard);
			if (i < board.size())
				allBoards += "/";
		}

		saveFile(boardFile, allBoards);
	}

	private String makeBoardInfo(Board board) {
		String info = String.format("%s,%s,%s,%s", board.getId(), board.getPassword(), board.getTitle(),
				board.getDetail());
		return info;
	}

	public void saveNoticeFile(ArrayList<Notification> notices) {
		String allNotices = "";
		for (int i = 0; i < notices.size(); i++) {
			Notification notice = notices.get(i);
			allNotices += makeNoticeInfo(notice);
			if (i < notices.size()-1)
				allNotices += "/";
		}
		
		saveFile(noticeFile,allNotices);
	}

	private String makeNoticeInfo(Notification notice) {
		String info = String.format("%s,%s", notice.getTitle(), notice.getDetail());
		return info;
	}

	public static Save getInstance() {
		return instance;
	}
}
