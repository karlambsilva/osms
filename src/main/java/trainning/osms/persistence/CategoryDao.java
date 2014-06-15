package trainning.osms.persistence;

import java.util.List;

import javax.persistence.*;
import trainning.osms.business.*;

public class CategoryDao {
	
	
	public boolean containsCategory(String categoryName){
		return searchCategory(categoryName) != null; // esse search retorna null 9(não existem categorias com o criterio passado) ou 1 categoria. 
	}
	
	public void insertCategory(Category category){
		EntityManagerFactory factory = EntityManagerFactoryHolder.factory;
		EntityManager manager = factory.createEntityManager();		
		EntityTransaction transaction = manager.getTransaction();
		
		transaction.begin();
		manager.persist(category); //método do jpql para fazer a inserção no banco
		transaction.commit();
		
	}
	
	public Category searchCategory(String categoryName){
		EntityManagerFactory factory = EntityManagerFactoryHolder.factory;
		EntityManager manager = factory.createEntityManager();	
		
		TypedQuery<Category> query = manager.createQuery(
				"SELECT category FROM trainning.osms.business.Category category WHERE UPPER(category.name) = :categoryName", 
				Category.class);
		query.setParameter("categoryName", categoryName.toUpperCase());
		List<Category> result = query.getResultList();
		
		if (result.isEmpty()){
			return null; //se a lista estiver vazia significa que não existe nenhuma cat no banco com o nome desejado.
		}else{
			return result.get(0); // se existir, pego ela.
		}
	}

	public List<Category> searchCategory(CategorySearchOptions options) {
		
		StringBuilder predicate = new StringBuilder("1 = 1"); // só para a concatenação funcionar perfeitamente por causa dos ands
		
		if(options.getName() != null && options.getName().length() > 0){
			predicate.append(" and upper(category.name) like :categoryName");
		}
		if(options.getDescription() != null && options.getDescription().length() > 0){
			predicate.append(" and upper(category.description) like :categoryDescription");
		}
		
		EntityManagerFactory factory = EntityManagerFactoryHolder.factory;
		EntityManager manager = factory.createEntityManager();	
		
		TypedQuery<Category> query = manager.createQuery(
				"SELECT category FROM trainning.osm.business.Category category where " + predicate, 
				Category.class);
		
		if(options.getName() != null && options.getName().length() > 0){
			query.setParameter("categoryName", "%" + options.getName().toUpperCase() + "%"); // % significa qualquer coisa
		}
		if(options.getDescription() != null && options.getDescription().length() > 0){
			query.setParameter("categoryDescription", "%" + options.getDescription().toUpperCase() + "%");
		}
		
		/*TypedQuery<Category> query = manager.createQuery(
				"SELECT category FROM trainning.osms.business.Category category WHERE UPPER(category.name) = :categoryName AND UPPER(category.description) = :categoryDescription", 
				Category.class);
		query.setParameter("categoryName", options.getName().toUpperCase());
		query.setParameter("categoryDescription", options.getDescription().toUpperCase());*/
		
		List<Category> result = query.getResultList();
		
		return result;
	}

	public void deleteCategory(Category category) {
		EntityManagerFactory factory = EntityManagerFactoryHolder.factory;
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		
		Category managedCategory = manager.find(Category.class, category.getId()); // encontra a categoria que tem o id informado
		
		transaction.begin();
		manager.remove(managedCategory); // o manager só consegue deletar os items q ele conhece.. por isso q preciso procurar pela categoria
		transaction.commit();		
		
	}
	
	public void updateCategory(Category category) {

		EntityManagerFactory factory = EntityManagerFactoryHolder.factory;
		EntityManager manager = factory.createEntityManager();		
		EntityTransaction transaction = manager.getTransaction();
		
		transaction.begin();
		manager.merge(category); // o merge vai procurar uma categoria e atualiza-la
		transaction.commit();
		
	}
}
