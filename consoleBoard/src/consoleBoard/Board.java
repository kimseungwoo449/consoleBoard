package consoleBoard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
	private String id;
	private String password;
	private String title;
	private String detail;
	
	public Board(String id,String password,String title,String detail) {
		this.id = id;
		this.password = password;
		this.title = title;
		this.detail = detail;
	}
}
