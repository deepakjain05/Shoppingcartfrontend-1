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

import com.deepak.shoppingcart.dao.SupplierDAO;
import com.deepak.shoppingcart.domain.Supplier;

@Controller
public class SupplierController {
	
	@Autowired
	private Supplier supplier;
	 @Autowired
	 private  SupplierDAO supplierDAO;
	 
	 @Autowired 
	 private HttpSession httpSession;
	 
	 
	 
	 @RequestMapping(name= "/supplier/get/{id}" ,method=RequestMethod.GET)
	public ModelAndView getSupplier(@RequestParam("id") String id)
	{
		 supplier=supplierDAO.get(id);
		ModelAndView mv= new ModelAndView("home");
		mv.addObject("supplier", supplier);
		return mv;
		
	}
	 @PostMapping("/supplier/save/")
	 public ModelAndView saveSupplier(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("address") String address)
	 {
		 ModelAndView mv=new ModelAndView("redirect:/ManageSupplier");
		 supplier.setId(id);
		 supplier.setName(name);
		 supplier.setAddress(address);
		 
		 if(supplierDAO.save(supplier))
		 {
			mv.addObject("clickedsuppliersuccess","Supplier  saved Successfully" ); 
			
		 }
		 else
		 {
			 mv.addObject("clickedsuppliererror", "Couldn't Save Supplier");
		 }
		 return mv;
	
		 
		 
	}
	 @PutMapping("/supplier/update")
	 public ModelAndView updateSupplier(@ModelAttribute Supplier supplier)
	 
	 {
		 ModelAndView mv=new ModelAndView("home");
		 if(supplierDAO.update(supplier)==true)
		 {
			 mv.addObject("SuccessMessage", "Supplier Updated Successfully");
		 }
		 else
		 {
			 mv.addObject("ErrorMessage", "Supplier couldn't update Successfully");
		 }
		 return mv;
		 
	 }
	 @DeleteMapping("/supplier/delete/{id}")
	 public ModelAndView deleteSupplier(@RequestParam("id") String id)
	 {
		 ModelAndView mv=new ModelAndView("redirect:/ManageSupplier");
		
		 if(supplierDAO.delete(id)==true)
		 {
			 mv.addObject("SuccessMessage", "Deleted Supplier Successfully");
		 }
		 else
		 {
			 mv.addObject("ErrorMessage", "Couldn't Delete Supplier ");
		 }
		 return mv;
	 }
	 @GetMapping("/suppliers")
	 public ModelAndView getAllSupplier()
	 {	
		ModelAndView mv= new ModelAndView();
	 List<Supplier> suppliers=supplierDAO.list();
	 mv.addObject("suppliers", suppliers);
	 return mv;
 }
	 
	 @GetMapping("/supplier/edit")
		public ModelAndView editSupplier(@RequestParam String id)
		{
			ModelAndView mv=new ModelAndView("redirect:/ManageSupplier");
			 supplier=supplierDAO.get(id);
			httpSession.setAttribute("selectSuppliers", supplier);
			return mv;
			
		}

}
