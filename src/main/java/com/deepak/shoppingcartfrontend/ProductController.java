package com.deepak.shoppingcartfrontend;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.deepak.shoppingcart.dao.ProductDAO;
import com.deepak.shoppingcart.dao.SupplierDAO;
import com.deepak.shoppingcart.domain.Product;
import com.deepak.shoppingcart.domain.Supplier;

@Controller
public class ProductController 
{
	@Autowired 
	private HttpSession httpSession;
	@Autowired
	private  Product product;
	 @Autowired
	 private  ProductDAO productDAO;
	 
	 @Autowired
	 private Supplier supplier;
	 
	 @Autowired 
	 private SupplierDAO supplierDAO;
	 
	/** @RequestMapping(name= "/product/get/{id}" ,method=RequestMethod.GET)
	public ModelAndView getProduct(@RequestParam("id") String id)
	{
		 product=productDAO.get(id);
		ModelAndView mv= new ModelAndView("home");
		mv.addObject("product", product);
		return mv;
		
	}*/
	 @PostMapping("/product/save")
	 public ModelAndView saveproduct(@RequestParam("id") String id , @RequestParam("name") String name, 
			 @RequestParam("description") String description, @RequestParam("price") String price,
			 @RequestParam("categoryID") String categoryID ,
			 @RequestParam("supplierID") String supplierID)
	 {
		 ModelAndView mv=new  ModelAndView("redirect:/ManageProducts");
		 
		 product.setId(id);
		 product.setName(name);
		 product.setDescription(description);
		 price= price.replace(",","");
		 product.setPrice(Integer.parseInt(price));
		 product.setCategoryId(categoryID);
		 product.setSupplierId(supplierID);
		 
		 if(productDAO.save(product)==true)
		 {
			 httpSession.setAttribute("SuccessMessage", "Product Saved Successfully");
		 }
		 else
		 {
			 httpSession.setAttribute("ErrorMessage", "Product couldn't Save Successfully");
		 }
		 return mv;
	 }
	 
	 @PutMapping("/product/update")
	 public ModelAndView updateProduct(@ModelAttribute Product product)
	 
	 {
		 ModelAndView mv=new ModelAndView("home");
		 if(productDAO.update(product)==true)
		 {
			 mv.addObject("SuccessMessage", "Product Updated Successfully");
		 }
		 else
		 {
			 mv.addObject("ErrorMessage", "Product couldn't update Successfully");
		 }
		 return mv;
		 
	 }
	 @GetMapping("/product/delete")
	 public ModelAndView deleteProduct(@RequestParam("id") String id)
	 {
		 ModelAndView mv=new ModelAndView("redirect:/ManageProducts");
		
		 if(productDAO.delete(id)==true)
		 {
			 mv.addObject("SuccessMessage", "Deleted Product Successfully");
		 }
		 else
		 {
			 mv.addObject("ErrorMessage", "Couldn't Delete Product ");
		 }
		 return mv;
	 }
	 @GetMapping("/products")
	 public ModelAndView getAllProduct()
	 {
		 ModelAndView mv= new ModelAndView();
		 List<Product> products=productDAO.list();
		 mv.addObject("products", products);
		 return mv;
	 }
	 @GetMapping("/product/edit")
		public ModelAndView editProduct(@RequestParam String id)
		{
			ModelAndView mv=new ModelAndView("redirect:/ManageProducts");
			 product=productDAO.get(id);
			httpSession.setAttribute("selectProducts", product);
			return mv;
			
		}

}
