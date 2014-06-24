package trainning.osms.presentation;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import trainning.osms.business.BusinessException;
import trainning.osms.business.Product;
import trainning.osms.business.ProductController;

@ManagedBean
public class NewProduct {
	
	private Product product;
	
	public NewProduct() {
		product = new Product();
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product category) {
		this.product = category;
	}
	
	public void save(){				
		FacesMessage message = new FacesMessage();
		
		try{
			ProductController controller = new ProductController();
			controller.saveProduct(product);
			message.setSummary("Product successufully saved");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
		}catch(BusinessException e){
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		}	

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		
	}

}
