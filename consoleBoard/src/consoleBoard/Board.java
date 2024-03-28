package consoleBoard;

import java.util.HashMap;
import java.util.Map;

public class Board {
	private Map<Map<String, String>, Map<String, String>> board;
	
	private static Board instance = new Board();
	
	private Board() {
		board = new HashMap<Map<String,String>, Map<String,String>>();
		Map<String,String> idAndPassword = new HashMap<String, String>();
		Map<String,String> titleAndDetail = new HashMap<String, String>();
	
		board.put(idAndPassword, titleAndDetail);
	}
	
	public Board getInstance() {
		return instance;
	}
}
