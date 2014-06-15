package trainning.osms.test;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import trainning.osms.business.*;
import trainning.osms.persistence.*;

public class CategoryControllerTest {
	
	@Test
	public void insertHappyTest(){
		Category category = new Category();
		category.setName("Technology");
		category.setDescription("Eveything about technology you can find here.");
		
		CategoryDao dao = EasyMock.createMock(CategoryDao.class);
		EasyMock.expect(dao.containsCategory("Technology")).andReturn(false); // se n��o cont��m, insert
		
		dao.insertCategory(category);
		EasyMock.replay(dao);
		
		CategoryController controller = new CategoryController();
		controller.setDao(dao);
		controller.saveCategory(category);
		
		EasyMock.verify(dao);
	}

	@Test
	public void insertUnhappyTest(){
		Category category = new Category();
		category.setName("Technology");
		category.setDescription("Eveything about technology you can find here.");
		
		CategoryDao dao = EasyMock.createMock(CategoryDao.class);
		EasyMock.expect(dao.containsCategory("Technology")).andReturn(true);
		
		//dao.insertCategory(category); n��o vai ser chamado pelo controlodor pq j�� existe um categoria de nome "Technology"
		EasyMock.replay(dao);
		
		CategoryController controller = new CategoryController();
		controller.setDao(dao);
		
		
		// se eu chamar o m��todo e ele n��o levanta uma exce����o.. eu fa��o o teste falhar com o "Assert.fail"
		
		try{
			controller.saveCategory(category); //envolvo linha que deve levantar exce����o
			Assert.fail("Exce����o n��o levantada"); //caso n��o levante.. por padr��o, ele continua o codigo para o proxima linha.. mas faco o teste falhar
		}catch(BusinessException e){
			
		}
		EasyMock.verify(dao);
	}
	
	@Test
	public void searchUnhappyTest(){
		CategorySearchOptions options = new CategorySearchOptions();
		options.setName("Technology");
		
		CategoryDao dao = EasyMock.createMock(CategoryDao.class);
		EasyMock.expect(dao.searchCategory(options)).andReturn(new ArrayList<Category>()); 
		
		EasyMock.replay(dao);
		
		CategoryController controller = new CategoryController();
		controller.searchCategory(options);
		
		EasyMock.verify(dao);		
	}
	
	@Test
	public void searchHappyTest(){
		CategorySearchOptions options = new CategorySearchOptions();
		options.setName("Technology");
		
		Category category = new Category();
		category.setId(3);
		category.setName("Technology");
		category.setDescription("Eveything about technology you can find here.");
		
		List <Category> categories = new ArrayList<>();
		categories.add(category);
		
		CategoryDao dao = EasyMock.createMock(CategoryDao.class);
		EasyMock.expect(dao.searchCategory(options)).andReturn(categories); 
		
		EasyMock.replay(dao);
		
		CategoryController controller = new CategoryController();
		controller.searchCategory(options);
		
		EasyMock.verify(dao);		
	}
	
	@Test
	public void upadateHappyTestNull(){
		Category category = new Category();
		category.setId(1);
		category.setName("Technology");
		category.setDescription("Eveything about technology you can find here.");
		
		CategoryDao dao = EasyMock.createMock(CategoryDao.class);
		EasyMock.expect(dao.searchCategory("Technology")).andReturn(null);
		
		dao.updateCategory(category);
		EasyMock.replay(dao);
		
		CategoryController controller = new CategoryController();
		controller.setDao(dao);
		controller.updateCategory(category);
		
		EasyMock.verify(dao);
	}
	
	@Test
	public void updateHappyTestSameIds(){
		// j�� existe uma categoria no bd com o mesmo nome, por��m o id eh o mesmo do que estou atualizando.
		
		Category category = new Category();
		category.setId(1);
		category.setName("Technology");
		category.setDescription("Eveything about technology you can find here.");
		
		Category databaseCategory = new Category();
		databaseCategory.setId(1);
		category.setName("Technology");
		category.setDescription("Eveything about technology.");
		
		CategoryDao dao = EasyMock.createMock(CategoryDao.class);
		EasyMock.expect(dao.searchCategory("Technology")).andReturn(databaseCategory);
		
		dao.updateCategory(category);
		EasyMock.replay(dao);
		
		CategoryController controller = new CategoryController();
		controller.setDao(dao);
		controller.updateCategory(category);
		
		EasyMock.verify(dao);
	}
	
	@Test
	public void updateUnhappyTest(){
		//caso quando o search retorna um categoria que o objeto retornado tem um id diferente do que eu estou passando

		Category category = new Category();
		category.setId(3);
		category.setName("Technology");
		category.setDescription("Eveything about technology you can find here.");
		
		Category databaseCategory = new Category();
		databaseCategory.setId(2);
		databaseCategory.setName("Technology");
		databaseCategory.setDescription("Software for everyone");
		
		CategoryDao dao = EasyMock.createMock(CategoryDao.class);
		EasyMock.expect(dao.searchCategory("Technology")).andReturn(databaseCategory);
		//dao.updateCategory(category);  n��o espero que o update seja chamado pq eu n��o vou atualizar.
		EasyMock.replay(dao);
		
		CategoryController controller = new CategoryController();
		controller.setDao(dao);
		
		try{
			controller.updateCategory(category); //envolvo linha que deve levantar exce����o
			Assert.fail("Exce����o n��o levantada"); //caso n��o levante.. por padr��o, ele continua o codigo para o proxima linha.. mas faco o teste falhar
		}catch(BusinessException e){
			
		}
		EasyMock.verify(dao);
	}

}
