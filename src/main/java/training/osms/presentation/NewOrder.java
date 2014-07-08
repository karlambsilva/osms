package training.osms.presentation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.osms.business.BusinessException;
import training.osms.business.OrderController;
import training.osms.business.Product;

@Component
@Scope(WebApplicationContext.SCOPE_APPLICATION)
public class NewOrder {
	
	private OrderForm form;
	private @Autowired OrderController controller;
		
	public NewOrder() {
		form = new OrderForm();
	}	
	
	public String getEmptyCart() {
		if ((form.getOrder().getProducts() != null) && (form.getOrder().getProducts().size() > 0)){
			return "none";
		}
		return "block";
	}

	public OrderForm getForm() {
		return form;
	}
	
	public void setForm(OrderForm form) {
		this.form = form;
	}
	
	public void addProductToCart(Product product){

		System.out.println("adicionei " + product.getName());
		if (form.getOrder().getProducts() == null){
			form.getOrder().setProducts(new ArrayList<Product>());
		}
		List<Product> proCart = form.getOrder().getProducts();
		proCart.add(product);
		this.form.setProducts(proCart);
		System.out.println("tamanho   " + form.getOrder().getProducts().size());
	}
	
	public void saveOrder(){				
		FacesMessage message = new FacesMessage();
		
		try{
			form.getOrder().setOrderDate(new Date());
			controller.saveOrder(form.getOrder());
			form = new OrderForm();
			message.setSummary("Order was successufully saved");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
		}catch(BusinessException e){
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		}	

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		
	}

}
