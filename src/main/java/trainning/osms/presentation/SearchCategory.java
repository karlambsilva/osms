package trainning.osms.presentation;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import trainning.osms.business.*;

@ManagedBean
@SessionScoped
public class SearchCategory {
	
	private List<Category> result;
	private CategorySearchOptions options;
	private Category category;
	private boolean categoryDeleted;
	
	
	public SearchCategory() {
		reset();
	}
	
	public void reset(){
		options = new CategorySearchOptions();
		result = null;
	}
	
	public List<Category> getResult() {
		return result;
	}
	
	public void setResult(List<Category> result) {
		this.result = result;
	}

	public CategorySearchOptions getOptions() {
		return options;
	}
	
	public void setOptions(CategorySearchOptions options) {
		this.options = options;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public boolean isCategoryDeleted() {
		return categoryDeleted;
	}

	public void setCategoryDeleted(boolean categoryDeleted) {
		this.categoryDeleted = categoryDeleted;
	}

	public void search(){		
		CategoryController controller = new CategoryController();
		result = controller.searchCategory(options);
		
		/*
		for (Category a: result){
			System.out.println(a.getName() + " ");
		}
		*/
	}
	
	public String update(Category category){
		
		Category categAux = new Category();
		categAux.setId(category.getId());
		categAux.setName(category.getName());
		categAux.setDescription(category.getDescription());
		
		this.category = categAux; 
		
		return "updateCategory"; //outcome definido no faces-config.xml
	}
	
	public void confirmUpdate(){
		
		FacesMessage message = new FacesMessage();
		
		try{
			CategoryController controller = new CategoryController();
			controller.updateCategory(category);
			reset();
			message.setSummary("Category successufully saved");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
		}catch(BusinessException e){
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		}	

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
	}
	
	
	public String delete(Category category){
		this.category = category;
		this.categoryDeleted = false;
		return "deleteCategory";
	}
	
	public void confirmDeletion(){
		CategoryController controller = new CategoryController();
		controller.deleteCategory(category);
		this.categoryDeleted = true; // disable delete button. evita do usuário tentar deletar a mesma coisa 2x
		reset();
		
		FacesMessage message = new FacesMessage();
		message.setSummary("Category successufully deleted");
		message.setSeverity(FacesMessage.SEVERITY_INFO);

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
	}
	 
}
