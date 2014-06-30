package trainning.osms.business;

import java.util.List;

import trainning.osms.persistence.ProductDao;

public class ProductController {
	
	private ProductDao dao;
	
	public ProductController() {
		dao = new ProductDao();
	}
	
	public ProductDao getDao() {
		return dao;
	}
	
	public void setDao(ProductDao dao) {
		this.dao = dao;
	}
	
	public void saveProduct(Product product) {
		dao.insertProduct(product);		
	}

	public List<Product> searchProduct(ProductSearchOptions options) {
		return dao.searchProduct(options);
	}
	
	public void deleteProduct(Product product) {
		dao.deleteProduct(product);
	}
	
	public void updateProduct(Product product) {
		dao.updateProduct(product);
	}

	public Integer searchProductCount(ProductSearchOptions options) {
		return dao.searchProductCount(options);
	}

}
