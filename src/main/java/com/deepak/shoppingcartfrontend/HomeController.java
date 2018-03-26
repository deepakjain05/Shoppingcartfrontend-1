package com.deepak.shoppingcartfrontend;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.deepak.shoppingcart.dao.CategoryDAO;
import com.deepak.shoppingcart.domain.Category;

	
@Controller
public class HomeController 
{	
	@Autowired
	private  CategoryDAO categoryDAO;
	
	@Autowired
	private Category category;
	
	@Autowired
	private  HttpSession httpSession;
	
	@GetMapping("/")
	public ModelAndView home() {
		ModelAndView mv1 = new ModelAndView("home");
		List<Category> categories= categoryDAO.list();
		httpSession.setAttribute("categories", categories);
		return mv1;
	}

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("UserClicked", true);
		return mv;
	}
	
	
	
	@GetMapping("/Registration")
	public ModelAndView Registration()
	{
		ModelAndView mv=new ModelAndView("home");
		mv.addObject("isUserClicked", true);
		return mv;
	}


}
