package consoleBoard;


public class User {
	private String id;
	private String password;
	
	public User(String id,String password) {
		this.id = id;
		this.password = password;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String newPassword) {
		this.password = newPassword;
	}
	
	public User clone() {
		return new User(this.id,this.password);
	}
}
