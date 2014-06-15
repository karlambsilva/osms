package trainning.osms.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import trainning.osms.business.Category;

public class CreateDatabase {
	
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("BMS");
		EntityManager manager = factory.createEntityManager();		
		EntityTransaction transaction = manager.getTransaction();
		
		transaction.begin();
		Category category = new Category();
		category.setName("Karla");
		category.setDescription("Category da Karla");
		manager.persist(category);
		transaction.commit();
		
		TypedQuery<Category> query = manager.createQuery(
				"SELECT category FROM trainning.osms.business.Category category WHERE UPPER(category.name) = :categoryName", 
				Category.class);
		query.setParameter("categoryName", "KARLA");
		List<Category> result = query.getResultList();
		System.out.println(result);
	}
}
