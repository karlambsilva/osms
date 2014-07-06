package training.osms.presentation;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.osms.business.BusinessException;
import training.osms.business.Category;
import training.osms.business.CategoryController;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class NewCategory {
	
	private CategoryForm form;
	private @Autowired CategoryController controller;
	
	public NewCategory() {
		form = new CategoryForm();
	}
	
	public CategoryForm getForm() {
		return form;
	}
	
	public void setForm(CategoryForm form) {
		this.form = form;
	}
	
	public void saveCategory(){				
		FacesMessage message = new FacesMessage();
		
		try{
			controller.saveCategory(form.getCategory());
			message.setSummary("Category was successufully saved");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
		}catch(BusinessException e){
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		}	

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		
	}

}
