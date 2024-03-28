package consoleBoard;

import java.util.HashMap;
import java.util.Map;

abstract public class User {
	public String id;
	public String password;
	public Map<String, String> myBoard;		// 나의 게시판 목록
	
	public User(String id,String password) {
		this.id = id;
		this.password = password;
		this.myBoard = new HashMap<String, String>();
	}
	
	
}
