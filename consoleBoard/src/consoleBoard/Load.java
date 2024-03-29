package consoleBoard;

public class Load {
	private static Load instance = new Load();

	private Load(){
		
	}

	public static Load getInstance() {
		return instance;
	}
}
