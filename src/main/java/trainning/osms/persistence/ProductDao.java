package trainning.osms.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import trainning.osms.business.Category;
import trainning.osms.business.Product;
import trainning.osms.business.ProductSearchOptions;

@Component
public class ProductDao {
	
	private @PersistenceContext EntityManager manager;
	
	public void insertProduct(Product product) {
		manager.persist(product); 	
	}
	
	public void updateProduct(Product product) {
		manager.merge(product); 
	}

	public void deleteProduct(Product product) {
		Product managedProduct = manager.find(Product.class, product.getId()); 
		manager.remove(managedProduct); // o manager só consegue deletar os items q ele conhece.. por isso q preciso procurar pela categoria	
	}	
	
	public Product searchProduct(String productName) {
		TypedQuery<Product> query = manager.createQuery(
				"SELECT product FROM trainning.osms.business.Product product WHERE UPPER(product.name) = :productName", 
				Product.class);
		query.setParameter("productName", productName.toUpperCase());
		List<Product> result = query.getResultList();
		
		if (result.isEmpty()){
			return null; 
		}else{
			return result.get(0); 
		}
	}
	
	public List<Product> searchProduct(ProductSearchOptions options) {

		StringBuilder predicate = toPredicate(options);

		if(options.getOrder() != null){
			predicate.append(" order by product.");
			predicate.append(options.getOrder().getValue());
			if(options.isDesc()){
				predicate.append(" desc");
			}
		}		
		
		TypedQuery<Product> query = manager.createQuery(
				"SELECT product FROM trainning.osms.business.Product product where " + predicate, 
				Product.class);		

		setParameters(options, query);

		if (options.getStartPosition() != null){
			query.setFirstResult(options.getStartPosition());
		}			
		if (options.getMaxResults() != null){
			query.setMaxResults(options.getMaxResults());
		}			
		List<Product> result = query.getResultList();

		return result;		
	}

	public int searchProductCount(ProductSearchOptions options) {

		StringBuilder predicate = toPredicate(options);			
		
		TypedQuery<Long> query = manager.createQuery(
				"SELECT count(product) FROM trainning.osms.business.Product product where " + predicate, 
				Long.class);		

		setParameters(options, query);

		Long result = query.getSingleResult();

		return result.intValue();		

	}	


	private StringBuilder toPredicate(ProductSearchOptions options) {
		StringBuilder predicate = new StringBuilder("1 = 1");

		if (options.getCategoryId() != null){
			predicate.append(" and product.category.id = :categoryId");
		}
		
		if (options.getProductId() != null){
			predicate.append(" and product.id = :productId");
		}

		if(options.getName() != null && options.getName().length() > 0){
			predicate.append(" and upper(product.name) like :productName");
		}
		
		if(options.getDescription() != null && options.getDescription().length() > 0){
			predicate.append(" and upper(product.description) like :productDescription");
		}	

		if(options.getPrice() != null){
			predicate.append(" and product.price = :productPrice");
		}	
		
		return predicate;
	}

	private void setParameters(ProductSearchOptions options, TypedQuery<?> query) {
		if (options.getCategoryId() != null){
			query.setParameter("categoryId", options.getCategoryId());
		}

		if (options.getProductId() != null){
			query.setParameter("productId", options.getProductId());
		}

		if(options.getName() != null && options.getName().length() > 0){
			query.setParameter("productName", "%" + options.getName().toUpperCase() + "%");
		}

		if(options.getDescription() != null && options.getDescription().length() > 0){
			query.setParameter("productDescription", "%" + options.getDescription().toUpperCase() + "%");
		}

		if(options.getPrice() != null ){
			query.setParameter("productPrice", options.getPrice());
		}
	}
	
}
