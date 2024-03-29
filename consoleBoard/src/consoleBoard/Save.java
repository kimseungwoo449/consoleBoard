package consoleBoard;

public class Save {
	private static Save instance = new Save();
	
	private Save(){
		
	}
	
	public static Save getInstance() {
		return instance;
	}
}
