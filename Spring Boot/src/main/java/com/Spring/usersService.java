package com.Spring;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userserv")
public class usersService 
{
	@Autowired
	usersRepo usrrepo;
	
	

	public users findEmail(String em) throws UserNotFoundException
	{
		users u = usrrepo.findByEmail(em).orElseThrow(			
				()->new UserNotFoundException("User Not Found !"));
		return u;
	}
	
	
	public boolean addUser(users u)
	{
		usrrepo.save(u);
		return true;
	}




	
	 
	

}
