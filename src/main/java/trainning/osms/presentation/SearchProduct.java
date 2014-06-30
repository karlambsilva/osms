package trainning.osms.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import trainning.osms.business.BusinessException;
import trainning.osms.business.Product;
import trainning.osms.business.ProductController;
import trainning.osms.business.ProductSearchOptions;


@ManagedBean
@SessionScoped
public class SearchProduct {
	
	private static final int RESULTS_PER_PAGE = 2;
	private List<Product> result;
	private ProductSearchOptions options;
	private ProductForm form;
	private boolean productDeleted;
	private List<Integer> pages;
	private int page;
	
	public SearchProduct() {
		reset();
	}
	
	private void reset() {
		options = new ProductSearchOptions();
		result = null;
	}
	
	public List<Product> getResult() {
		return result;
	}
	
	public void setResult(List<Product> result) {
		this.result = result;
	}
	
	public ProductSearchOptions getOptions() {
		return options;
	}
	
	public void setOptions(ProductSearchOptions options) {
		this.options = options;
	}
	
	public ProductForm getForm() {
		return form;
	}
	
	public void setForm(ProductForm form) {
		this.form = form;
	}
	
	public boolean isProductDeleted() {
		return productDeleted;
	}
	
	public void setProductDeleted(boolean productDeleted) {
		this.productDeleted = productDeleted;
	}
	
	public List<Integer> getPages() {
		return pages;
	}
	
	public void setPages(List<Integer> pages) {
		this.pages = pages;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public static int getResultsPerPage() {
		return RESULTS_PER_PAGE;
	}
	
	public void search(){		
		ProductController controller = new ProductController();		
		
		int resultCount = controller.searchProductCount(options);
		int pageCount = resultCount / RESULTS_PER_PAGE;
		
		if (resultCount % RESULTS_PER_PAGE > 0){
			++pageCount;
		}
		
		pages = new ArrayList<Integer>();		
		for (int page = 1; page <= pageCount; ++page){
			pages.add(page);
		}
		
		goToPage(1);
		
	}
	
	public void goToPage(int page){
		this.page = page;
		
		int startPosition = (page - 1) * RESULTS_PER_PAGE;
		options.setStartPosition(startPosition);
		options.setMaxResults(RESULTS_PER_PAGE);


		ProductController controller = new ProductController();
		result = controller.searchProduct(options);
	}
	
	public String update(Product product){
		
		Product productAux = new Product();
		productAux = product.clone();
		
		this.form = new ProductForm();
		this.form.setProduct(productAux);
		
		return "updateProduct";
	}
	
	public void confirmUpdate(){
				
		FacesMessage message = new FacesMessage();
		
		try{
			ProductController controller = new ProductController();
			controller.updateProduct(form.getProduct());
			reset();
			message.setSummary("Product successufully saved");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
		}catch(BusinessException e){
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		}	

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		//context.addMessage("form:name", message);
	}
	
	public String delete(Product product){
		this.form = new ProductForm();
		this.form.setProduct(product);
		this.productDeleted = false;
		return "deleteProduct";
	}
	
	public void confirmDeletion(){		
		
		ProductController controller = new ProductController();
		controller.deleteProduct(form.getProduct());
		this.productDeleted = true;
		reset();
		
		FacesMessage message = new FacesMessage();
		message.setSummary("Product successufully deleted");
		message.setSeverity(FacesMessage.SEVERITY_INFO);

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		
		
	}

	
	
	 
}