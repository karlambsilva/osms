package training.osms.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.facelets.Tag;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import training.osms.business.Category;
import training.osms.business.CategoryController;
import training.osms.business.CategorySearchOptions;
import training.osms.business.Product;

public class CategoryForm {

	private List <Category> categories;
	private Category category;
	
	public CategoryForm() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ApplicationContext applicationContext = FacesContextUtils.getWebApplicationContext(facesContext);
		
		CategoryController controller = applicationContext.getBean(CategoryController.class);
		categories = controller.searchCategory(new CategorySearchOptions());
		category = new Category();
	}
	
	public List<Category> getCategories() {
		return categories;
	}
	
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public void setParentCategoryId(Integer parentCategoryId){
		if (parentCategoryId == null){
			category.setParentCategory(null);
		}else{
			for(Category parentCategory : categories){
				if (parentCategory.getId().equals(parentCategoryId)){
					category.setParentCategory(parentCategory);
					break;
				}
			}
		}
	}
	
	public Integer getParentCategoryId() {
		Category parentCategory = category.getParentCategory();
		
		if(parentCategory==null){
			return null;
		}else{
			return parentCategory.getId();
		}
	}
	
	public void setCategoryIds(List<String> categoryIds) {
		category.getSubCategories().clear();
		for (String categoryId : categoryIds) {
			for (Category categ : categories) {
				if (categ.getId().toString().equals(categoryId)) {
					category.getSubCategories().add(categ);
				}
			}
		}
	}

	public List<String> getCategoryIds() {
		List<String> categoryIds = new ArrayList<>();
		
		if (category.getSubCategories() != null){
			for (Category categ : category.getSubCategories()) {
				categoryIds.add(categ.getId().toString());
			}
		}
		return categoryIds;
	}
	
}
