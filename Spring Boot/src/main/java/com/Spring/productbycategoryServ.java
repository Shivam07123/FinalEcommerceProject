package com.Spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("procatserv")
public class productbycategoryServ
{
	@Autowired
	productbycategoryRepo procatrepo;
	
	
	public List<productbycategory> productbycategory(int catid)
	{
		procatrepo.productbycategory(catid).forEach(m->{System.out.println(m);});
		
		return procatrepo.productbycategory(catid);
	}
	

}
