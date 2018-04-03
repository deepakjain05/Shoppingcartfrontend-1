package com.deepak.shoppingcartfrontend;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.deepak.shoppingcart.dao.CartDAO;
import com.deepak.shoppingcart.domain.Cart;

@Controller
public class CartController {
	
	@Autowired
	private Cart cart;
	
	@Autowired
	private CartDAO cartDAO;
	
	@Autowired
	private HttpSession httpSession;
	
	@PostMapping("/product/cart/add")
	public ModelAndView addToCart(@RequestParam String productName,@RequestParam String quantity, @RequestParam int price)
	{
		ModelAndView mv=new ModelAndView("home");
		String loggedInUserID= (String)httpSession.getAttribute("loggedInUserID");
		if(loggedInUserID==null)
		{
			mv.addObject("errorMessage", "Please login to add any product to cart");
			return mv;
		}
		cart.setEmailID(loggedInUserID);
		cart.setPrice(price);
		cart.setQuantity(Integer.parseInt(quantity));
		
		if(cartDAO.save(cart))
		{
			mv.addObject("successMessage", "Product added to cart");
		}
		else
		{
			mv.addObject("errorMessage","couldnt add product to th cart");
		}
		return mv;
	}
	
	@GetMapping("/mycart/")
	public ModelAndView getAllCartDeatils()
	{
		ModelAndView mv= new ModelAndView("home");
		String loggedInUserID= (String)httpSession.getAttribute("loggedInUserID");
		if(loggedInUserID==null)
		{
			mv.addObject("errorMessage", "Please login to see cart details");
			return mv;
		}
		List<Cart> cartList= cartDAO.list(loggedInUserID);
		mv.addObject("cartList",cartList);
		return mv;
	}
	
	@GetMapping("/mycart")
	public ModelAndView mycart()
	{
		ModelAndView mv= new ModelAndView();
		mv.addObject("isUserClickedCart",true);
		return mv;
	}
	
	
	

}
