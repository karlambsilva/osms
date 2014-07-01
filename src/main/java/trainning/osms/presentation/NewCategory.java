package trainning.osms.presentation;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import trainning.osms.business.Category;
import trainning.osms.business.CategoryController;
import trainning.osms.business.BusinessException;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class NewCategory {
	
	private Category category;
	private @Autowired CategoryController controller;
	
	public NewCategory() {
		category = new Category();
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public void save(){				
		FacesMessage message = new FacesMessage();
		
		try{
			controller.saveCategory(category);
			message.setSummary("Category successufully saved");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
		}catch(BusinessException e){
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		}	

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		
	}

}
