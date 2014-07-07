package training.osms.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.osms.business.*;

@Component
@Scope(WebApplicationContext.SCOPE_SESSION)
public class SearchCategory {
	
	private static final int RESULTS_PER_PAGE = 2;
	private List<Category> result;
	private CategorySearchOptions options;
	private CategoryForm form;
	private boolean categoryDeleted;
	private List<Integer> pages;
	private int page;
	
	private @Autowired CategoryController controller;
	
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
	
	public CategoryForm getForm() {
		return form;
	}
	
	public void setForm(CategoryForm form) {
		this.form = form;
	}
	
	public boolean isCategoryDeleted() {
		return categoryDeleted;
	}

	public void setCategoryDeleted(boolean categoryDeleted) {
		this.categoryDeleted = categoryDeleted;
	}

	public List<Integer> getPages() {
		return pages;
	}
	
	public void setPages(List<Integer> pages) {
		this.pages = pages;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public static int getResultsPerPage() {
		return RESULTS_PER_PAGE;
	}
	
	public void search(){		
		
		int resultCount = controller.searchCategoryCount(options);
		int pageCount = resultCount / RESULTS_PER_PAGE;
		
		if (resultCount % RESULTS_PER_PAGE > 0){
			++pageCount;
		}
		
		pages = new ArrayList<Integer>();		
		for (int page = 1; page <= pageCount; ++page){
			pages.add(page);
		}
		
		goToPage(1);
		
	}
	
	public void goToPage(int page){
		this.page = page;
		
		int startPosition = (page - 1) * RESULTS_PER_PAGE;
		options.setStartPosition(startPosition);
		options.setMaxResults(RESULTS_PER_PAGE);

		result = controller.searchCategory(options);
	}

	public String update(Category category){
		
		CategorySearchOptions options = new CategorySearchOptions();
		options.setId(category.getId());
		Category categAux = controller.searchCategory(options).get(0);
		
		this.form = new CategoryForm();
		this.form.setCategory(categAux);
		
		/*CategorySearchOptions options = new CategorySearchOptions();
		options.setId(form.getCategory().getId());
		
		form.setCategories(controller.getPossibleParentCategories(options));*/
		
		return "updateCategory"; //outcome definido no faces-config.xml
	}
	
	public void confirmUpdate(){
		
		FacesMessage message = new FacesMessage();
		
		try{
			controller.updateCategory(form.getCategory());
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
		
		CategorySearchOptions options = new CategorySearchOptions();
		options.setId(category.getId());
		Category categAux = controller.searchCategory(options).get(0);
		
		this.form = new CategoryForm();
		this.form.setCategory(categAux);
		this.categoryDeleted = false;
		return "deleteCategory";
	}
	
	public void confirmDeletion(){
		controller.deleteCategory(form.getCategory());
		this.categoryDeleted = true; // disable delete button. evita do usu???rio tentar deletar a mesma coisa 2x
		reset();
		
		FacesMessage message = new FacesMessage();
		message.setSummary("Category successufully deleted");
		message.setSeverity(FacesMessage.SEVERITY_INFO);

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
	}
	 
}
