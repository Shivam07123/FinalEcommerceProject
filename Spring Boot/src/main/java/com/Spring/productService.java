package com.Spring;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("proserv")
public class productService 
{
	@Autowired
	productRepository prorepo;
	
	public List<product> getAllproduct()
	{
		return prorepo.findAll();
	}
	
	
	public Optional<product> getById(int pid)
	{
		return prorepo.findById(pid);
	}
	
	
	public void addproduct(product ct) 
	{
		prorepo.save(ct);
	}
	
	
	public void updateProduct(product ct)
	{
		prorepo.saveAndFlush(ct);
		
	}
	
	public void delById(int id)
	{
		prorepo.deleteById(id);
	}
		
}

