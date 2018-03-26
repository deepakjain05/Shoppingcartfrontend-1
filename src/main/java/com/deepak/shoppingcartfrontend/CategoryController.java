package com.deepak.shoppingcartfrontend;

import java.util.List;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.deepak.shoppingcart.dao.CategoryDAO;
import com.deepak.shoppingcart.domain.Category;





@Controller
public class CategoryController 
{
	
	@Autowired
	private  CategoryDAO categoryDAO;
	
	@Autowired
	private Category category;
	@Autowired
	private  HttpSession httpSession;
	
	@RequestMapping(name="/category/get/{id}",method=RequestMethod.GET)
	public ModelAndView getCategory(@RequestParam("id") String id)
	{
		category= categoryDAO.get(id);
		ModelAndView mv=new ModelAndView("home");
		mv.addObject("category", category);
		return mv;
	}
	
	@PostMapping(name="/category/save/")
	public ModelAndView saveCategory(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("description") String description)

	{
		 ModelAndView mv=new ModelAndView("redirect:/ManageCategories");
		 category.setId(id);
		 category.setName(name);
		 category.setDescription(description);
		 
		 if(categoryDAO.save(category))
		 {
			mv.addObject("clickedcategoriessuccess","Category saved Successfully" ); 
			
		 }
		 else
		 {
			 mv.addObject("clickedcategorieserror", "Couldn't Save Category");
		 }
		 return mv;
	
		 
		 
	}
	@PutMapping("/category/update/")
	public ModelAndView updateCategory(@ModelAttribute Category category)
	{
		ModelAndView mv=new ModelAndView();
		
		if(categoryDAO.update(category)==true)
		{
			mv.addObject("SuccessMessage","Update Sucessfully");
		}
		else
		{
			mv.addObject("ErrorMessage", "Couldn't update");
		}
		return mv;
	}
	
	@GetMapping("/category/delete")
	 public ModelAndView deleteCategory(@RequestParam("id") String id)
	 {
	
		 ModelAndView mv=new ModelAndView("redirect:/ManageCategories");
		
		 if(categoryDAO.delete(id)==true)
		 {
			 mv.addObject("SuccessMessage", "Deleted Category Successfully");
		 }
		 else
		 {
			 mv.addObject("ErrorMessage", "Couldn't Delete Category ");
		 }
		 return mv;
	 }
	
	
	@GetMapping("/categories")
	public ModelAndView getAllCategories()
	{
		ModelAndView mv=new ModelAndView("home");
		List<Category> categories=categoryDAO.list();
		httpSession.setAttribute("categories", categories);
		return mv;
	}
	
	@GetMapping("/category/edit")
	public ModelAndView editCategory(@RequestParam String id)
	{
		ModelAndView mv=new ModelAndView("redirect:/ManageCategories");
		 category=categoryDAO.get(id);
		httpSession.setAttribute("selectCategories", category);
		return mv;
		
	}
}
