package consoleBoard;

public class Board {
	private String id;
	private String password;
	private String title;
	private String detail;

	public Board(String id, String password, String title, String detail) {
		this.id = id;
		this.password = password;
		this.title = title;
		this.detail = detail;
	}

	public String getTitle() {
		return this.title;
	}
	
	public String getDetail() {
		return this.detail;
	}

	public String getId() {
		return this.id;
	}

	public String getPassword() {
		return this.password;
	}
}
