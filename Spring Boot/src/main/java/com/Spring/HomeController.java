package com.Spring;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController  							 //Spring RestController annotation is used to create RESTful web services using Spring MVC.
@RequestMapping("api") 						 // used to map HTTP requests to handler methods of MVC and REST controllers.
@CrossOrigin(origins = "*", allowedHeaders = "*")  	// its allows all origins, all headers, and the HTTP methods specified in the @RequestMapping annotation.  
public class HomeController 
{
	@Autowired 								 // @Autowired  enables you to inject the object dependency implicitly
	categoryService catserv;
	
	
	@Autowired
	productService proserv;
	
	
	
	@Autowired
	productbycategoryServ procatserv;
	
	
	@Autowired
	usersService userserv;
	
	@Autowired
	EmailService emserv;
	
	
	@Autowired
	private orderServices orderservices;

	
	
	/*****************************  Category Details *********************************/
	
	
	@GetMapping("allcat")
	@CrossOrigin(origins = "http://localhost:4200/")
	public List<category> getAllCat()
	{
		return catserv.getAllCategory();
	}
	
	
	@GetMapping("catbyid/{id}")
	@CrossOrigin(origins = "http://localhost:4200/")
	public category getById(@PathVariable int id)
	{
		return catserv.getById(id);
	}
	
	
	@PostMapping("addcat")  // insert the new records
	@CrossOrigin(origins = "http://localhost:4200/")
	public void addCategory(@RequestBody category c)
	{
		catserv.addCategory(c);		
	}
	
	@PutMapping("updatecat/{id}")
	@CrossOrigin(origins = "http://localhost:4200/")
	public void updateCategory(@RequestBody category c,@PathVariable int id)
	{
		catserv.updateCatgory(c, id);
	}
	
		
	
	@DeleteMapping("deletecat/{id}")
	@CrossOrigin(origins = "http://localhost:4200/")
	public void deleteCategory(@PathVariable int id)
	{
		catserv.delById(id);
	}
		
	// Pagination 
	
	
	@GetMapping("allcatbypage")	
	public Page<category> allCatByPage(@RequestParam(name="page",defaultValue="0") int page,
			@RequestParam(name="size",defaultValue="5") int size)
	{
		return catserv.allcatbypage(page, size);
	}
	
	
	/***************************************  Product Details *************************************/
	
	
	

	@PostMapping("addprdt")  // insert the new records
	@CrossOrigin(origins = "http://localhost:4200/")
	public void addProduct(@RequestParam("pic")MultipartFile file,
			@RequestParam("prnm")String nm,
			@RequestParam("price")int pr,
			@RequestParam("qty")int qty,
			@RequestParam("descrip")String de,
			@RequestParam("ct_catid")int ct,
			@RequestParam("reorder")int r) throws IOException
	{
		product p = new product();
		category c = catserv.getById(ct);
		
		p.setPrnm(nm);
		p.setPrice(pr);
		p.setQty(qty);
		p.setDescrip(de);
		p.setReorder(r);
		p.setCt(c);
		p.setPic(file.getBytes());
		
		
		proserv.addproduct(p);	
	}
	
	
	@GetMapping("allprdt")
	@CrossOrigin(origins = "http://localhost:4200/")
	public List<product> getAllPrdt()
	{
		return proserv.getAllproduct();
	}
	
	@DeleteMapping("deleteprdt/{id}")
	@CrossOrigin(origins = "http://localhost:4200/")
	public void deleteProduct(@PathVariable int id)
	{
		proserv.delById(id);
	}
	
	
	/***************************  User Authentication *******************************/
	
	@PostMapping("adduser") 
	@CrossOrigin(origins = "http://localhost:4200/")
	public boolean addusers(@RequestBody users u)
	{
		return userserv.addUser(u);
	}
	
	
	@PostMapping("checkuser")
	@CrossOrigin(origins = "http://localhost:4200/")
	public boolean checkuser(@RequestBody users u) throws UserNotFoundException
	{
		users u1 =userserv.findEmail(u.getEm());
		if(!u1.getEm().isEmpty())
				return true;
		else
				return false;
		
		
	}
	
	
	
	/********************************  Spring Boot Email Service *******************************************/
	
	@RequestMapping("/sendmail")
	@CrossOrigin(origins = "http://localhost:4200/")
	public ResponseEntity<String> checkEmail(@RequestBody String em)
	{
	emserv.sendEmail(em, "Crazy Deals", "\"🎉 Great news! 🎉\r\n"
			+ "\r\n"
			+ "Crazy Deals is back with another round of unbeatable discounts and mind-blowing offers just for you! 🛍️ Don't miss out on the chance to grab your favorite products at jaw-dropping prices. 🤑\r\n"
			+ "\r\n"
			+ "Hurry, these deals won't last long! Visit Crazy Deals now and shop till you drop. 💃🕺\r\n"
			+ "\r\n"
			+ "Download the app: [App Link]\r\n"
			+ "\r\n"
			+ "Happy shopping, and thank you for choosing Crazy Deals! 🛒✨\"");
	return new ResponseEntity<>("Message Send",HttpStatus.CREATED);
	}

	/*****************************  RazorPay EndPoint ********************/
	
	@GetMapping("/getTransaction/{amount}")
	@CrossOrigin(origins = "*")
	public orderTransactionDetails getTransaction(@PathVariable("amount") 
	double amount)
	{
	return orderservices.orderCreateTransaction(amount);
	}
	
}


