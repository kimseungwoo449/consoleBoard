package consoleBoard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Load {
	private static Load instance = new Load();
	
	private FileReader fr;
	private BufferedReader br;
	private File userFile;
	private File boardFile;
	private File noticeFile;

	private Load() {
		userFile = new File(ConsoleBoard.USER_FILE_NAME);
		boardFile = new File(ConsoleBoard.BOARD_FILE_NAME);
		noticeFile = new File(ConsoleBoard.NOTICE_FILE_NAME);
	}

	public ArrayList<User> loadUserData() {
		ArrayList<User> users = makeUsers(userFile);
		return users;
	}

	private ArrayList<User> makeUsers(File userFile) {
		ArrayList<User> users = null;
		String data = loadFile(userFile);
		String info[] = data.split("\n");
		
		if(info[0]!=null) {
			users = new ArrayList<User>();
			for(int i =0;i<info.length;i++) {
				String targetUser[] = info[i].split(",");
				String id = targetUser[0];
				String password = targetUser[1];
				User user = new User(id,password);
				
				users.add(user);
			}
		}
		
		return users;
	}
	
	private String loadFile(File file) {
		String data = null;
		if(file.exists()) {
			data = "";
			try {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				
				while(br.ready()) {
					data+=br.readLine();
					data+="\n";
				}
				
				if(data.length()>1)
					data=data.substring(0,data.length()-1);
				
				System.out.println("로드 성공");
			} catch (Exception e) {
				System.err.println("로드 실패");
			}
		}
		return data;
	}
	
	public static Load getInstance() {
		return instance;
	}
}
