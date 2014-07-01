package trainning.osms.presentation;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import trainning.osms.business.BusinessException;
import trainning.osms.business.Product;
import trainning.osms.business.ProductController;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class NewProduct {
	
	private Product product;
	private @Autowired ProductController controller;
	
	public NewProduct() {
		product = new Product();
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product category) {
		this.product = category;
	}
	
	public void save(){				
		FacesMessage message = new FacesMessage();
		
		try{
			controller.saveProduct(product);
			message.setSummary("Product successufully saved");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
		}catch(BusinessException e){
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		}	

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		
	}

}
