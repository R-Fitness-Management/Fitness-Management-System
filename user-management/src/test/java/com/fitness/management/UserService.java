package com.fitness.management;

import java.util.HashMap;
import java.util.Map;

public class UserService {
	private Map<String, User> users = new HashMap<>();
	
	public boolean addUser(User user) {
		if (users.containsKey(user.getEmail())){
			return false;
		}
		users.put(user.getEmail(),user);
		return true;
	}

public boolean updateUser(String email, String name, String role) {	
User user = users.get(email);
if(user == null) {
	return false;
}
user.setName(name);
user.setRole(role);
return true;

}
	

public boolean detectiveUser (String email) {
	User user = users.get(email);
	if (user ==null) {
		return false;
	}
	user.setActive(false);
	return true;
}
	
	public User getUser(String email) {
		return users.get(email);
	}
	
}
