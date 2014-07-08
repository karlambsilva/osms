package training.osms.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ShowStore {
	
	private List<Category> parentCategories;
	private List<Category> subCategories;
	private List<Product> products;
	private int categoryId;

	private @Autowired CategoryController controller;
	private @Autowired ProductController productController;
	
	
	public int getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public List<Category> getParentCategories() {
		CategorySearchOptions options = new CategorySearchOptions();
		
		parentCategories = controller.searchParentCategories(options);	
		
		return parentCategories;
	}
	
	public void setParentCategories(List<Category> parentCategories) {
		this.parentCategories = parentCategories;
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
	
	
	public List<Product> getProducts() {
		products = productController.searchProduct(new ProductSearchOptions());
		List<Product> auxiliar = new ArrayList<>();
		Random rand = new Random();
		int counter = 0;
		while (counter < 6){
			int  n = rand.nextInt(products.size());
			if (!auxiliar.contains(products.get(n))){
				auxiliar.add(products.get(n));
				counter++;
			}			
		}		
		return auxiliar;		
	}
	
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	


}
