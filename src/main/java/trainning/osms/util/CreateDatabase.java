package trainning.osms.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import trainning.osms.business.Category;

public class CreateDatabase {
	
	public void populateDatabase(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("BMS");
		EntityManager manager = factory.createEntityManager();		
		EntityTransaction transaction = manager.getTransaction();
		
		transaction.begin();
		Category category1 = new Category();
		category1.setName("Moda");
		category1.setDescription("Feminina, masculina, infantil, calçados, acessórios e muito mais.");
		manager.persist(category1);
		transaction.commit();
		
		transaction.begin();
		Category category1_1 = new Category();
		category1_1.setName("Feminina");
		category1_1.setDescription("Todos os artigos de moda feminina, você encontra aqui.");
		category1_1.setParentCategory(category1);
		manager.persist(category1_1);
		transaction.commit();
		
		transaction.begin();
		Category category1_1_1 = new Category();
		category1_1_1.setName("Blusas");
		category1_1_1.setDescription("");
		category1_1_1.setParentCategory(category1_1);
		manager.persist(category1_1_1);
		transaction.commit();
		
		transaction.begin();
		Category category1_1_2 = new Category();
		category1_1_2.setName("Vestidos");
		category1_1_2.setDescription("");
		category1_1_2.setParentCategory(category1_1);
		manager.persist(category1_1_2);
		transaction.commit();
		
		transaction.begin();
		Category category1_1_3 = new Category();
		category1_1_3.setName("Calças");
		category1_1_3.setDescription("");
		category1_1_3.setParentCategory(category1_1);
		manager.persist(category1_1_3);
		transaction.commit();
		
		transaction.begin();
		Category category1_2 = new Category();
		category1_2.setName("Masculina");
		category1_2.setDescription("Todos os artigos de moda masculina, você encontra aqui.");
		category1_2.setParentCategory(category1);
		manager.persist(category1_2);
		transaction.commit();
		
		transaction.begin();
		Category category1_3 = new Category();
		category1_3.setName("Infantil");
		category1_3.setDescription("Todos os artigos de moda infantil, você encontra aqui.");
		category1_3.setParentCategory(category1);
		manager.persist(category1_3);
		transaction.commit();
		
		transaction.begin();
		Category category2 = new Category();
		category2.setName("Tecnologia");
		category2.setDescription("Games, celulares, informática e eletrônicos.");
		manager.persist(category2);
		transaction.commit();
		
		System.out.println("DB successufully created.");
		
	}
	
}
