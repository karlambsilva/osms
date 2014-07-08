package training.osms.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import training.osms.persistence.ProductDao;

@Component
public class ProductController {
	
	private @Autowired ProductDao dao;
	
	public ProductDao getDao() {
		return dao;
	}
	
	public void setDao(ProductDao dao) {
		this.dao = dao;
	}
	
	@Transactional
	public void saveProduct(Product product) {
		dao.insertProduct(product);		
	}

	public List<Product> searchProduct(ProductSearchOptions options) {
		return dao.searchProduct(options);
	}
	
	@Transactional
	public void deleteProduct(Product product) {
		product = dao.searchProduct(product.getName());
		if (product.getOrders() == null || product.getOrders().size() == 0){
			dao.deleteProduct(product);
		}else{
			throw new BusinessException("Ops! You cannot delete this product.");
		}
	}
	
	@Transactional
	public void updateProduct(Product product) {
		dao.updateProduct(product);
	}

	public Integer searchProductCount(ProductSearchOptions options) {
		return dao.searchProductCount(options);
	}

}
