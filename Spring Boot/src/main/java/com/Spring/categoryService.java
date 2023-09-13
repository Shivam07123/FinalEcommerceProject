package com.Spring;

import java.util.List;
import java.util.Optional;

import org.springdoc.core.converters.models.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service("catserv")
public class categoryService 
{
	@Autowired
	categoryRepository catrepo;
	
	public List<category> getAllCategory()
	{
		return catrepo.findAll();
	}
	
	
	public category getById(int pid)
	{
		return catrepo.findById(pid).orElse(null);
	}
	
	
	public void addCategory(category ct) 
	{
		catrepo.save(ct);
	}
	
	
	public void updateCatgory(category ct,int id)
	{
		category c1 = this.getById(id);
		
		c1.setCatnm(ct.getCatnm());
			
		catrepo.save(c1);
		
	}
	
	public void delById(int id)
	{
		catrepo.deleteById(id);
	}
	
	public Page<category> allcatbypage(int pg,int size)
	{
		return catrepo.allcategories(PageRequest.of(pg, size));
	}
	
	
	
	
}
