package consoleBoard;

import java.util.HashMap;
import java.util.Map;

public class Board {
	private final String EXIT = "*";
	private Map<String[], String> board;
	
	private static Board instance = new Board();

	private Board() {
		board = new HashMap<String[], String>();
	}

	public Board getInstance() {
		return instance;
	}

	// 게시글 작성
	public void writing(String id,String password) {
		String title = ConsoleBoard.inputString("제목");
		String detail = "";
		System.out.println("내용 (*입력시 종료): ");
		while (true) {
			String info = ConsoleBoard.inputString();
			if(info.equals(EXIT))
				break;
			detail += info;
			detail+="\n";
		}
		
		String[] idAndPasswordAndTitle = new String[3];
		idAndPasswordAndTitle[0]= id;
		idAndPasswordAndTitle[1] = password;
		idAndPasswordAndTitle[2] = title;
		
		
		board.put(idAndPasswordAndTitle, detail);
		System.out.println("게시글 등록 완료.");
	}
	
	// 읽기
	public void viewContents(String title) {
		
	}
	
	private String[] findContentsByTitle(String title) {
		String[] info = null;
		
		
		return info;
	}
}
