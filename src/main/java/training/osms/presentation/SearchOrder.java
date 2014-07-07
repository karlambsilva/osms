package training.osms.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import training.osms.business.*;

@Component
@Scope(WebApplicationContext.SCOPE_SESSION)
public class SearchOrder {
	
	private static final int RESULTS_PER_PAGE = 2;
	private List<Integer> pages;
	private int page;
	
	private List<Order> result;
	private OrderSearchOptions options;
	
	//Update and Delete
	private OrderForm form;
	private boolean orderDeleted;
	
	
	private @Autowired OrderController controller;
	
	public SearchOrder() {
		reset();
	}
	
	public void reset(){
		options = new OrderSearchOptions();
		result = null;
		pages = null;
		page = 0;
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

	public List<Order> getResult() {
		return result;
	}

	public void setResult(List<Order> result) {
		this.result = result;
	}

	public OrderSearchOptions getOptions() {
		if (options.getId() == null || options.getId() == 0){
			options.setId(null);
		}
		return options;
	}

	public void setOptions(OrderSearchOptions options) {
		this.options = options;
	}

	public OrderForm getForm() {
		return form;
	}

	public void setForm(OrderForm form) {
		this.form = form;
	}

	public boolean isOrderDeleted() {
		return orderDeleted;
	}

	public void setOrderDeleted(boolean orderDeleted) {
		this.orderDeleted = orderDeleted;
	}

	public OrderController getController() {
		return controller;
	}

	public void setController(OrderController controller) {
		this.controller = controller;
	}

	public static int getResultsPerPage() {
		return RESULTS_PER_PAGE;
	}

	public void search(){		
		
		int resultCount = controller.searchOrderCount(options);
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

		result = controller.searchOrder(options);
	}

	public String update(Order order){
		
		OrderSearchOptions options = new OrderSearchOptions();
		options.setId(order.getId());
		Order orderAux = controller.searchOrder(options).get(0);
		
		this.form = new OrderForm();
		this.form.setOrder(orderAux);
		
		/*OrderSearchOptions options = new OrderSearchOptions();
		options.setId(form.getOrder().getId());
		
		form.setCategories(controller.getPossibleParentCategories(options));*/
		
		return "updateOrder"; //outcome definido no faces-config.xml
	}
	
	public void confirmUpdate(){
		
		FacesMessage message = new FacesMessage();
		
		try{
			controller.updateOrder(form.getOrder());
			reset();
			message.setSummary("Order successufully saved");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
		}catch(BusinessException e){
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		}	

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
	}
	
	
	public String delete(Order order){
		
		OrderSearchOptions options = new OrderSearchOptions();
		options.setId(order.getId());
		Order categAux = controller.searchOrder(options).get(0);
		
		this.form = new OrderForm();
		this.form.setOrder(categAux);
		this.orderDeleted = false;
		return "deleteOrder";
	}
	
	public void confirmDeletion(){
		controller.deleteOrder(form.getOrder());
		this.orderDeleted = true; // disable delete button. evita do usu???rio tentar deletar a mesma coisa 2x
		reset();
		
		FacesMessage message = new FacesMessage();
		message.setSummary("Order successufully deleted");
		message.setSeverity(FacesMessage.SEVERITY_INFO);

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
	}
	 
}
