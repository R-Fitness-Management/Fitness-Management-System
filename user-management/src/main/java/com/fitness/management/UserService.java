package com.fitness.management;
import java.util.Collection;
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
	
	 public boolean approveUser(String email) {
	        User user = users.get(email);
	        if (user != null && !user.isActive()) {
	            user.setActive(true);
	            return true;
	        }
	        return false;
	    }
	 
	 public Collection<User> getAllUsers() {
		    return users.values(); 
		}

	    public Map<String, Boolean> getUserActivityStats() {
	        Map<String, Boolean> activityStats = new HashMap<>();
	        for (Map.Entry<String, User> entry : users.entrySet()) {
	            activityStats.put(entry.getKey(), entry.getValue().isActive());
	        }
	        if (activityStats.isEmpty()) {
	            activityStats.put("defaultUser@example.com", true); 
	        }
	        
	        return activityStats;
	    }

	    public void setUsers(Map<String, User> users) {
	        this.users = users;
	    }
	
}