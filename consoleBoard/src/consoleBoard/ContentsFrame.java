package consoleBoard;

abstract public class ContentsFrame {
	private String title;
	private String detail;
	public boolean isNotification;
	
	public ContentsFrame(String title, String detail) {
		this.title = title;
		this.detail = detail;
	}

	public String getTitle() {
		return this.title;
	}

	public String getDetail() {
		return this.detail;
	}
	
	abstract public void setIsNotification();
	
	public boolean isNotification() {
		return isNotification;
	}
}
