package trainning.osms.presentation;

import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import trainning.osms.business.Product;
import trainning.osms.business.ProductController;
import trainning.osms.business.ProductSearchOptions;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ShowProduct {

	private int productId;
	private Product product;
	private @Autowired ProductController controller;
	
	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int productId) {

		this.productId = productId;
		
		ProductSearchOptions options =  new ProductSearchOptions();
		options.setProductId(productId);
		
		List<Product> products = controller.searchProduct(options);
		
		if (products.size() > 0){
			product = products.get(0);
		}
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public String getProductBody() {
		String escapedBody = StringEscapeUtils.escapeHtml(product.getDescription());
		StringBuilder description = new StringBuilder();
		
		description.append("<p>");
		
		escapedBody.replaceAll("[(\\n\\r)(\\n)(\\r)]+", "</p><p>");		
		
		description.append("</p>");
		description.append(escapedBody);
		return description.toString();
	}
}

