package training.osms.presentation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.osms.business.Category;
import training.osms.business.CategoryController;
import training.osms.business.CategorySearchOptions;
import training.osms.business.Product;
import training.osms.business.ProductController;
import training.osms.business.ProductSearchOptions;
import training.osms.business.ProductSearchOptions.Order;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ShowCategory {
	
	private Category category;
	private int categoryId;
	private List<Product> products;
	private List<Category> subCategories;
	private @Autowired CategoryController controller;
	private @Autowired ProductController productController;
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
		
		CategorySearchOptions options = new CategorySearchOptions();
		options.setId(categoryId);
		
		List <Category> categories = controller.searchCategory(options);
		
		if (categories.size() > 0){
			category = categories.get(0);
						
			ProductSearchOptions productOptions = new ProductSearchOptions();
			productOptions.setOrder(Order.NAME);
			productOptions.setDesc(true);
			
			productOptions.setCategoryId(categoryId);
			
			products = productController.searchProduct(productOptions);
		}
	}
	
	public List<Product> getProducts() {
		return products;
	}
	
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public List<Category> getSubCategories() {
		CategorySearchOptions options = new CategorySearchOptions();
		options.setParentCatId(categoryId);
		
		subCategories = controller.searchCategory(options);
		
		return subCategories;
	}
	
	public void setSubCategories(List<Category> subCategories) {
		this.subCategories = subCategories;
	}
	
	
}
