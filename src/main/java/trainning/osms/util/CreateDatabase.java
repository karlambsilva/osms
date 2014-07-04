package trainning.osms.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import trainning.osms.business.Category;
import trainning.osms.business.Product;

public class CreateDatabase {
	
	public static void main(String [] args){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("OSMS");
		EntityManager manager = factory.createEntityManager();		
		EntityTransaction transaction = manager.getTransaction();
		
		/*
		 * Category
		 */
		
		transaction.begin();
		Category cat_moda = new Category();
		cat_moda.setName("Moda");
		cat_moda.setDescription("Feminina, masculina, infantil, calcados, acessorios e muito mais.");
		manager.persist(cat_moda);
		transaction.commit();
		
		transaction.begin();
		Category cat_mod_feminina = new Category();
		cat_mod_feminina.setName("Feminina");
		cat_mod_feminina.setDescription("Todos os artigos de moda feminina, voce encontra aqui.");
		cat_mod_feminina.setParentCategory(cat_moda);
		manager.persist(cat_mod_feminina);
		transaction.commit();
		
		transaction.begin();
		Category cat_mod_fem_blusas = new Category();
		cat_mod_fem_blusas.setName("Blusas");
		cat_mod_fem_blusas.setDescription("Camiseta, Social e etc.");
		cat_mod_fem_blusas.setParentCategory(cat_mod_feminina);
		manager.persist(cat_mod_fem_blusas);
		transaction.commit();
		
		transaction.begin();
		Category cat_mod_fem_vestidos = new Category();
		cat_mod_fem_vestidos.setName("Vestidos");
		cat_mod_fem_vestidos.setDescription("Curto, Midi, Longo e etc.");
		cat_mod_fem_vestidos.setParentCategory(cat_mod_feminina);
		manager.persist(cat_mod_fem_vestidos);
		transaction.commit();
		
		transaction.begin();
		Category cat_mod_fem_calcas = new Category();
		cat_mod_fem_calcas.setName("Calcas");
		cat_mod_fem_calcas.setDescription("Social, Jeans, Capri e etc.");
		cat_mod_fem_calcas.setParentCategory(cat_mod_feminina);
		manager.persist(cat_mod_fem_calcas);
		transaction.commit();
		
		transaction.begin();
		Category cat_mod_masculina = new Category();
		cat_mod_masculina.setName("Masculina");
		cat_mod_masculina.setDescription("Todos os artigos de moda masculina, voce encontra aqui.");
		cat_mod_masculina.setParentCategory(cat_moda);
		manager.persist(cat_mod_masculina);
		transaction.commit();
		
		transaction.begin();
		Category cat_mod_infantil = new Category();
		cat_mod_infantil.setName("Infantil");
		cat_mod_infantil.setDescription("Todos os artigos de moda infantil, voce encontra aqui.");
		cat_mod_infantil.setParentCategory(cat_moda);
		manager.persist(cat_mod_infantil);
		transaction.commit();
		
		transaction.begin();
		Category cat_tecnologia = new Category();
		cat_tecnologia.setName("Tecnologia");
		cat_tecnologia.setDescription("Games, celulares, informatica e eletronicos.");
		manager.persist(cat_tecnologia);
		transaction.commit();
		
		
		
		/*
		 * Product 
		 */
		
		transaction.begin();
		Product pro_vestido_1 = new Product();
		pro_vestido_1.setName("Vestido Longo Corujitas");
		pro_vestido_1.setDescription("Tecido: Viscose \n Composição: 100% Viscose - Forro: 100% Poliéster" +
				"\nDetalhes: Vestido longo de modelagem solta e tecido leve, com alças ajustáveis e zíper nas costas."
				+ " Acompanha cinto fino de elástico igual ao da foto. Com cores sóbrias e padronagem delicada, esse "
				+ "vestido pode ser combinado com acessórios alegres para looks casuais.");
		pro_vestido_1.setImage("http://www.antixstore.com/Assets/Produtos/Gigantes/9133_vestido-corujitas_antix-store_baunilha_01_635332707782160642.jpg");
		pro_vestido_1.setPrice(300.0);
		pro_vestido_1.setCategory(cat_mod_fem_vestidos);
		manager.persist(pro_vestido_1);
		transaction.commit();
		
		
		transaction.begin();
		Product pro_vestido_2 = new Product();
		pro_vestido_2.setName("Vestido Sonho");
		pro_vestido_2.setDescription("Tecido: Viscose \n Composição: 100% Viscose - Forro: 100% Poliéster" +
				"\nDetalhes: Vestido de tecido plano estampado, acinturado e com zíper nas costas. Possui um recorte "
				+ "de tecido liso de cor contrastante e detalhes rendados nas mangas. Com cores neutras e fáceis de "
				+ "combinar com diversos acessórios (dica: explore as cores presentes na estampa na escolha dos "
				+ "acessórios para compor o look), esse vestido é um must have da estação, para ser usado em variadas "
				+ "ocasiões. Acompanha cinto fininho de couro sintético e fivela delicada.");
		pro_vestido_2.setImage("http://www.antixstore.com/Assets/Produtos/Gigantes/9135_vestido-sonho_antix-store_verde_01_635348999623329046.jpg");
		pro_vestido_2.setPrice(250.0);
		pro_vestido_2.setCategory(cat_mod_fem_vestidos);
		manager.persist(pro_vestido_2);
		transaction.commit();
		
		
		System.out.println("DB successufully created.");
	}
	
}
