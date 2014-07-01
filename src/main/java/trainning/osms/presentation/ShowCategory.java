package trainning.osms.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import trainning.osms.business.Category;
import trainning.osms.business.CategoryController;
import trainning.osms.business.CategorySearchOptions;
import trainning.osms.business.Product;
import trainning.osms.business.ProductController;
import trainning.osms.business.ProductSearchOptions;
import trainning.osms.business.ProductSearchOptions.Order;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ShowCategory {
	
	private Category category;
	private int categoryId;
	private List<Product> products;
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
	
}
