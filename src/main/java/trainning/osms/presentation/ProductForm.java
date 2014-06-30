package trainning.osms.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.facelets.Tag;

import trainning.osms.business.Category;
import trainning.osms.business.CategoryController;
import trainning.osms.business.CategorySearchOptions;
import trainning.osms.business.Product;

public class ProductForm {

	private List <Category> categories;
	private Product product;
	
	public ProductForm() {
		CategoryController controller = new CategoryController();		
		categories = controller.searchCategory(new CategorySearchOptions());
		
		
		product = new Product();
	}
	
	public List<Category> getCategories() {
		return categories;
	}
	
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public void setCategoryId(Integer categoryId){
		if (categoryId == null){
			product.setCategory(null);
		}else{
			for(Category category : categories){
				if (category.getId().equals(categoryId)){
					product.setCategory(category);
					break;
				}
			}
		}
	}
	
	public Integer getCategoryId() {
		Category category = product.getCategory();
		
		if(category==null){
			return null;
		}else{
			return category.getId();
		}
	}
	
}
