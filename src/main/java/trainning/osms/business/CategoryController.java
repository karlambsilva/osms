package trainning.osms.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import trainning.osms.persistence.CategoryDao;

@Component
public class CategoryController {
	
	private @Autowired CategoryDao dao;
	
	public CategoryDao getDao() {
		return dao;
	}
	
	public void setDao(CategoryDao dao) {
		this.dao = dao;
	}
	
	@Transactional
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
	
	@Transactional
	public void deleteCategory(Category category) {
		dao.deleteCategory(category);
	}
	
	@Transactional
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

	public Integer searchCategoryCount(CategorySearchOptions options) {
		return dao.searchCategoryCount(options);
	}

}
