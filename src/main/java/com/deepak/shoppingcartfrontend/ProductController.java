package com.deepak.shoppingcartfrontend;


import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.deepak.shoppingcart.dao.ProductDAO;
import com.deepak.shoppingcart.dao.SupplierDAO;
import com.deepak.shoppingcart.domain.Product;
import com.deepak.shoppingcart.domain.Supplier;
import com.niit.util.FileUtil;

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
	 
	 private static final String imageDirectory= "ShoppingCartImages";
	 
	 private static String rootpath= System.getProperty("catalina.home");
	 
	/** @RequestMapping(name= "/product/get/{id}" ,method=RequestMethod.GET)
	public ModelAndView getProduct(@RequestParam("id") String id)
	{
		 product=productDAO.get(id);
		ModelAndView mv= new ModelAndView("home");
		mv.addObject("product", product);
		return mv;
		
	}*/
	 /*@GetMapping("/product/get")
	 public ModelAndView getProduct(@RequestParam String id)
	 {
		 product =productDAO.get(id);
		 ModelAndView mv=new ModelAndView("home");
		 mv.addObject("selectedProduct", "product");
		 mv.addObject("isUserSelectedproduct", true);
		 mv.addObject("selectedProductImage", rootpath +File.separator +imageDirectory +File.separator +id + ".PNG");
		 
		 return mv;
		 
	 }*/
	@GetMapping("/product/get/{id}")
	 public ModelAndView getSelectedProduct(@PathVariable("id") String id , RedirectAttributes redirectAttributes)
	 {
		 ModelAndView mv= new ModelAndView("redirect:/");
		 redirectAttributes.addFlashAttribute("selectedProduct", productDAO.get(id));
		 redirectAttributes.addFlashAttribute("isUserSelectedProduct",true);
		 redirectAttributes.addFlashAttribute("selectedProductImage", rootpath +File.separator +imageDirectory +File.separator +id + ".PNG");
		 return mv;
		 
	 }
	 @PostMapping("/product/save")
	 public ModelAndView saveproduct(@RequestParam("id") String id , @RequestParam("name") String name, 
			 @RequestParam("description") String description, @RequestParam("price") String price,
			 @RequestParam("categoryID") String categoryID ,
			 @RequestParam("supplierID") String supplierID,
			 @RequestParam("file") MultipartFile file)
			 
	 {
		 ModelAndView mv=new  ModelAndView("redirect:/ManageProducts");
		 
		 product.setId(id);
		 product.setName(name);
		 product.setDescription(description);
		 price= price.replace(",","");
		 product.setPrice(Integer.parseInt(price));
		 product.setCategoryId(categoryID);
		 product.setSupplierId(supplierID);
		 
		 if(productDAO.save(product))
		 {
			 
			 mv.addObject("clickedproductsuccess", "Product Saved Successfully");
			
			 if(FileUtil.fileCopyNIO(file, id+ ".PNG")) 
			 {
				 mv.addObject("uploadMessage","product image uploaded successfully");
			 }
			 else
			 {
				 mv.addObject("uploadMessage","product image couldnt upload");
			 }
		 }
		 else
		 {
			 mv.addObject("clickedproducterror", "Product couldn't Save Successfully");
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
			httpSession.setAttribute("selectedProduct", product);
			return mv;
			
		}

}
