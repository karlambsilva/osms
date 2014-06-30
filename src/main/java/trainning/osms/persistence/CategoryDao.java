package trainning.osms.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import trainning.osms.business.Category;
import trainning.osms.business.CategorySearchOptions;

public class CategoryDao {	
	
	public boolean containsCategory(String categoryName){
		return searchCategory(categoryName) != null; // esse search retorna null (n�o existem categorias com o criterio passado) ou 1 categoria. 
	}
	
	public void insertCategory(Category category){
		EntityManagerFactory factory = EntityManagerFactoryHolder.factory;
		EntityManager manager = factory.createEntityManager();		
		EntityTransaction transaction = manager.getTransaction();
		
		transaction.begin();
		manager.persist(category); //m�todo do jpql para fazer a inser��o no banco
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
			return null; //se a lista estiver vazia significa que n�o existe nenhuma cat no banco com o nome desejado.
		}else{
			return result.get(0); // se existir, pego ela.
		}
	}

	public List<Category> searchCategory(CategorySearchOptions options) {
		
		StringBuilder predicate = toPredicate(options);

		if(options.getOrder() != null){
			predicate.append(" order by category.");
			predicate.append(options.getOrder().getValue());
			if(options.isDesc()){
				predicate.append(" desc");
			}
		}	
		
		EntityManagerFactory factory = EntityManagerFactoryHolder.factory;
		EntityManager manager = factory.createEntityManager();	
		
		TypedQuery<Category> query = manager.createQuery(
				"SELECT category FROM trainning.osms.business.Category category where " + predicate, 
				Category.class);
		
		
		setParameters(options, query);
		
		if (options.getStartPosition() != null){
			query.setFirstResult(options.getStartPosition());
		}			
		if (options.getMaxResults() != null){
			query.setMaxResults(options.getMaxResults());
		}			
		
		List<Category> result = query.getResultList();
		
		return result;
	}
	
	public int searchCategoryCount(CategorySearchOptions options) {

		StringBuilder predicate = toPredicate(options);			

		EntityManagerFactory factory = EntityManagerFactoryHolder.factory;
		EntityManager manager = factory.createEntityManager();	
		
		TypedQuery<Long> query = manager.createQuery(
				"SELECT count(category) FROM trainning.osms.business.Category category where " + predicate, 
				Long.class);		

		setParameters(options, query);

		Long result = query.getSingleResult();

		return result.intValue();		

	}
	
	private StringBuilder toPredicate(CategorySearchOptions options) {
		StringBuilder predicate = new StringBuilder("1 = 1"); // s� para a concatena��o funcionar perfeitamente por causa dos ands
		
		if(options.getName() != null && options.getName().length() > 0){
			predicate.append(" and upper(category.name) like :categoryName");
		}
		if(options.getDescription() != null && options.getDescription().length() > 0){
			predicate.append(" and upper(category.description) like :categoryDescription");
		}
		
		return predicate;
	}

	private void setParameters(CategorySearchOptions options, TypedQuery<?> query) {
	
		if(options.getName() != null && options.getName().length() > 0){
			query.setParameter("categoryName", "%" + options.getName().toUpperCase() + "%"); // % significa qualquer coisa
		}
		if(options.getDescription() != null && options.getDescription().length() > 0){
			query.setParameter("categoryDescription", "%" + options.getDescription().toUpperCase() + "%");
		}
	}
	
	public void deleteCategory(Category category) {
		EntityManagerFactory factory = EntityManagerFactoryHolder.factory;
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		
		Category managedCategory = manager.find(Category.class, category.getId()); // encontra a categoria que tem o id informado
		
		transaction.begin();
		manager.remove(managedCategory); // o manager s� consegue deletar os items q ele conhece.. por isso q preciso procurar pela categoria
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
