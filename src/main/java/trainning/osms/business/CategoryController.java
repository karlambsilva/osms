package trainning.osms.business;

import java.util.List;

import trainning.osms.persistence.CategoryDao;

public class CategoryController {
	
	private CategoryDao dao;
	
	public CategoryController() {
		dao = new CategoryDao();
	}
	
	public CategoryDao getDao() {
		return dao;
	}
	
	public void setDao(CategoryDao dao) {
		this.dao = dao;
	}
	
	public void saveCategory(Category category) {
		if(dao.containsCategory(category.getName())){
			throw new BusinessException("There is a category named " + category.getName() + " already");
		}else{
			dao.insertCategory(category);
		}		
	}

	public List<Category> searchCategory(CategorySearchOptions options) {
		return dao.searchCategory(options);
	}
	
	public void deleteCategory(Category category) {
		dao.deleteCategory(category);
	}
	
	public void updateCategory(Category category) {
		Category databaseCategory = dao.searchCategory(category.getName());
		
		if (databaseCategory == null){
			dao.updateCategory(category);
		}else{
			if (category.getId().equals(databaseCategory.getId())){
				dao.updateCategory(category);
			}else{
				throw new BusinessException("There is a category named " + category.getName() + " already");
			}
		}
	}

}
