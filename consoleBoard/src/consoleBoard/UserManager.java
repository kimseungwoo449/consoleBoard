package consoleBoard;

import java.util.ArrayList;

public class UserManager {
	private ArrayList<User> users;
	private static UserManager instance = new UserManager();
	private Save userSave = Save.getInstance();
	
	private UserManager() {
		users = new ArrayList<User>();
		User admin = new User("admin","1");
		users.add(admin);
	}
	
	public static UserManager getInstance() {
		return instance;
	}
	
	// 유저 만들기
	public void createUser() {
		String id = ConsoleBoard.inputString("ID");
		String password = ConsoleBoard.inputString("PASSWORD");

		if (findUserIndexById(id) != -1) {
			System.err.println("이미 존재하는 ID입니다.");
			return;
		}

		User user = new User(id, password);
		users.add(user);
		System.out.println("가입이 완료되었습니다.");
		userSave.saveUserFile(users);
	}

	private int findUserIndexById(String id) {
		int index = -1;
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			if (user.getId().equals(id))
				index = i;
		}
		return index;
	}

	// 로그인
	public void login() {
		String id = ConsoleBoard.inputString("ID");
		String password = ConsoleBoard.inputString("PASSWORD");

		int userIndex = findUserIndexById(id);
		if (userIndex == -1 || !users.get(userIndex).getPassword().equals(password)) {
			System.err.println("아이디 혹은 비밀번호를 확인해 주세요.");
			return;
		}

		ConsoleBoard.log = userIndex;
		String info = String.format("로그인 성공. %s님 안녕하세요.", users.get(userIndex).getId());
		System.out.println(info);
	}

	public void logout() {
		ConsoleBoard.log = -1;
		System.out.println("로그아웃 성공.");
	}

	public String[] getUserIdAndPassword() {
		String[] info = new String[2];
		User user = users.get(ConsoleBoard.log);
		String id = user.getId();
		String password = user.getPassword();

		info[0] = id;
		info[1] = password;
		return info;
	}
	
	public User modifyUserPassword() {
		User user = users.get(ConsoleBoard.log);
		String password = ConsoleBoard.inputString("PASSWORD 재확인");
		
		if(!user.getPassword().equals(password)) {
			System.err.println("패스워드가 일치하지 않습니다.");
			return null;
		}
		String newPassword = ConsoleBoard.inputString("새로운 PASSWORD");
		user.setPassword(newPassword);
		userSave.saveUserFile(users);
		return user.clone();
	}
	
	// 유저 삭제
	public String deleteUser() {
		User user = users.get(ConsoleBoard.log);
		String password = ConsoleBoard.inputString("PASSWORD");

		if (!user.getPassword().equals(password)) {
			System.err.println("PASSWORD 재확인");
			return null;
		}

		users.remove(user);
		ConsoleBoard.log = -1;
		System.out.println("탈퇴 처리 완료.");
		userSave.saveUserFile(users);
		return user.getId();
	}
}
