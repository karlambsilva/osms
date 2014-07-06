package training.osms.presentation;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.osms.business.BusinessException;
import training.osms.business.Product;
import training.osms.business.ProductController;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class NewProduct {
	
	private ProductForm form;
	private @Autowired ProductController controller;
	
	public NewProduct() {
		form = new ProductForm();
	}
	
	public ProductForm getForm() {
		return form;
	}
	
	public void setForm(ProductForm form) {
		this.form = form;
	}
	
	public void saveProduct(){				
		FacesMessage message = new FacesMessage();
		
		try{
			controller.saveProduct(form.getProduct());
			message.setSummary("Product was successufully saved");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
		}catch(BusinessException e){
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		}	

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		
	}

}
