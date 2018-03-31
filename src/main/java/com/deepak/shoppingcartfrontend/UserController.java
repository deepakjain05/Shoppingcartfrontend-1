package com.deepak.shoppingcartfrontend;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.deepak.shoppingcart.dao.UserDAO;
import com.deepak.shoppingcart.domain.User;

@Controller
public class UserController {
	@Autowired

	private UserDAO userDAO;
	
	@Autowired
	private User user;
	
	@Autowired
	private HttpSession httpSession;
	
	@PostMapping("validate")
	public ModelAndView validate(@RequestParam("uname") String username, @RequestParam("psw") String password)
	{
		
		ModelAndView mv=new ModelAndView("home");
		
		user = userDAO.validate(username, password);
		
		if(user==null)
		{
			mv.addObject("errormessage","Invalid");
		}
		else
		{
			
			httpSession.setAttribute("Welcomemessage", "welcome user   "   +user.getName() );
			
			httpSession.setAttribute("loggedInUserID", user.getEmailID()); 
			
			if(user.getRole()=='A')
			{
				httpSession.setAttribute("adminlogin",true);
			}
		
		}

		
		
		return mv;
	}
	

}
