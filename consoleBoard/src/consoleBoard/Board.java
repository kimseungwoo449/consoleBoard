package consoleBoard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
	private final String EXIT = "*";
	
	private final int CONTENTS_NUMBER = 0;
	private final int ID = 1;
	private final int PASSWORD = 2;
	private final int TITLE = 3;
	
	
	private Map<String[], String> board;
	private int contentsNumber;
	private static Board instance = new Board();

	private Board() {
		board = new HashMap<String[], String>();
		this.contentsNumber = 1;
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
		
		String[] keyValues = new String[4];
		keyValues[CONTENTS_NUMBER] = contentsNumber+"";
		keyValues[ID]= id;
		keyValues[PASSWORD] = password;
		keyValues[TITLE] = title;
		
		contentsNumber++;
		board.put(keyValues, detail);
		System.out.println("게시글 등록 완료.");
	}
	
	// 읽기
	public void viewContents() {
		String number = ConsoleBoard.inputString("조회하려는 글 번호");
		
		String contents = createStringInfo(number);
		if(contents==null) {
			System.err.println("찾으시는 글번호는 존재하지 않습니다.");
			return;
		}
	}
	
	private String createStringInfo(String number) {
		String info = null;
		
		for(String[] key:board.keySet()) {
			String keyNumber = key[CONTENTS_NUMBER];
			if(number.equals(keyNumber)) {
				String title = key[TITLE];
				String detail = board.get(key);
				info = String.format("제목 : %s\n내용 : %s",title,detail );
				return info;
			}
		}
		return info;
	}
	
//	public void viewAllTitle() {
//		List keySet = new ArrayList(board.keySet());
//		Collections.sort(keySet, );;
//	}
}
