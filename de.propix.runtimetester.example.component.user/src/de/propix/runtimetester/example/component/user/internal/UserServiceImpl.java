package de.propix.runtimetester.example.component.user.internal;

import de.propix.runtimetester.core.IntegratedTest;
import de.propix.runtimetester.core.RuntimeTestService;
import de.propix.runtimetester.example.component.user.UserService;

public class UserServiceImpl implements UserService, RuntimeTestService {

	public String getUserName(String userId) {
		if(userId.equals("TEST-ID")){
			return "Test User Name";
		}
		// TODO: Service impl
		return null;
	}

	public void removeUser(int userId) {
		
	}
	
	@IntegratedTest
	public void testVerifyUserDatabaseAvaible(){
		//TODO: Check that the user db is avaible
	}
		
}
