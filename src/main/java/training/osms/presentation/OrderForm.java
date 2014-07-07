package training.osms.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import training.osms.business.Category;
import training.osms.business.CategoryController;
import training.osms.business.CategorySearchOptions;
import training.osms.business.Order;
import training.osms.business.OrderController;
import training.osms.business.Product;
import training.osms.business.ProductController;
import training.osms.business.ProductSearchOptions;

public class OrderForm {

	private List <Product> products;
	private Order order;
	
	public OrderForm() {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ApplicationContext applicationContext = FacesContextUtils.getWebApplicationContext(facesContext);
		
		ProductController controller = applicationContext.getBean(ProductController.class);
		products = controller.searchProduct(new ProductSearchOptions());
		
		order = new Order();
	}
	
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public void setProductIds(List<String> productIds) {
		
		List<Product> productsAuxiliar = new ArrayList<>();
		
		if (order.getProducts() != null){
			order.getProducts().clear();
		}
		for (String productId : productIds) {
			for (Product prod : products) {
				if (prod.getId().toString().equals(productId)) {
					productsAuxiliar.add(prod);
				}
			}
		}
		
		order.setProducts(productsAuxiliar);
	}

	public List<String> getProductIds() {
		List<String> productIds = new ArrayList<>();
		if (order.getProducts() != null){
			for (Product prod: order.getProducts()) {
				productIds.add(prod.getId().toString());
			}
		}
		return productIds;
	}
	
}
