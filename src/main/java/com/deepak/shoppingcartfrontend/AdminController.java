package com.deepak.shoppingcartfrontend;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.deepak.shoppingcart.dao.CategoryDAO;
import com.deepak.shoppingcart.dao.ProductDAO;
import com.deepak.shoppingcart.dao.SupplierDAO;
import com.deepak.shoppingcart.domain.Category;
import com.deepak.shoppingcart.domain.Product;
import com.deepak.shoppingcart.domain.Supplier;

@Controller
public class AdminController {
	
	
	@Autowired
	private Product product;
	@Autowired 
	private ProductDAO productDAO;
	
	@Autowired
	private Category category;
	
	@Autowired 
	private CategoryDAO categoryDAO;
	
	@Autowired
	private Supplier supplier;
	
	@Autowired 
	 private SupplierDAO supplierDAO;
	
	
	@Autowired 
	private HttpSession httpSession;
	
	@GetMapping("/ManageCategories")
	public ModelAndView adminclickedcategory()
	{
		ModelAndView  mv= new ModelAndView("home");
		mv.addObject("AdminClickedCategories",true);
		List<Category> categories=categoryDAO.list();
		httpSession.setAttribute("categories", categories);
		return mv;
	}
	@GetMapping("/ManageProducts")
	public ModelAndView adminclickedproduct()
	{
		ModelAndView  mv= new ModelAndView("home");
		mv.addObject("AdminClickedProduct",true);
		List<Category> categories=categoryDAO.list();
		List<Product> products=productDAO.list();
		List<Supplier> suppliers=supplierDAO.list();
		httpSession.setAttribute("products", products);
		httpSession.setAttribute("categories", categories);
		httpSession.setAttribute("suppliers", suppliers);
		return mv;
	}
	
	@GetMapping("/ManageSupplier")
	public ModelAndView adminclickedsupplier()
	{
		ModelAndView  mv= new ModelAndView("home");
		mv.addObject("AdminClickedSupplier",true);
		List<Supplier> suppliers=supplierDAO.list();
		httpSession.setAttribute("suppliers", suppliers);
		return mv;
	}
}
