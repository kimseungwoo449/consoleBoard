package consoleBoard;

public class Notification extends ContentsFrame{
	public Notification(String title,String detail) {
		super(title,detail);
	}
	
	@Override
	public void setIsNotification() {
		super.isNotification = true;
	}
	
	public String getTitle() {
		return super.getTitle();
	}
	
	public String getDetail() {
		return super.getDetail();
	}
}
