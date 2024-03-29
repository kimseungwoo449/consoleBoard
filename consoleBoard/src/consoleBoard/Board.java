package consoleBoard;

public class Board extends ContentsFrame{
	private String id;
	private String password;

	public Board(String id, String password, String title, String detail) {
		super(title,detail);
		this.id = id;
		this.password = password;
	}

	public String getTitle() {
		return super.getTitle();
	}
	
	public String getDetail() {
		return super.getDetail();
	}

	public String getId() {
		return this.id;
	}

	public String getPassword() {
		return this.password;
	}
	
	@Override
	public void setIsNotification() {
		super.isNotification = false;
	}
}
