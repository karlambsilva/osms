package training.osms.presentation;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.osms.business.BusinessException;
import training.osms.business.OrderController;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class NewOrder {
	
	private OrderForm form;
	private @Autowired OrderController controller;
	
	public NewOrder() {
		form = new OrderForm();
	}
	
	public OrderForm getForm() {
		return form;
	}
	
	public void setForm(OrderForm form) {
		this.form = form;
	}
	
	public void saveOrder(){				
		FacesMessage message = new FacesMessage();
		
		try{
			form.getOrder().setOrderDate(new Date());
			controller.saveOrder(form.getOrder());
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
