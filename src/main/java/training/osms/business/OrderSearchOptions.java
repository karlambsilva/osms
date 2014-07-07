package training.osms.business;

import java.util.Date;

public class OrderSearchOptions {
	
	public enum Order{
		ID("id"),
		ORDER_DATE("orderDate");
		
		private String value;
		
		//construtor tem que ser privado.
		private Order(String value){
			this.value = value;
		}
		
		public String getValue(){
			return value;
		}		
	}
	
	private Integer id;
	private String orderDate;
	
	private boolean desc;
	private Order order;
	private Integer startPosition;
	private Integer maxResults;
	
	public OrderSearchOptions() {
		order = Order.ID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public boolean isDesc() {
		return desc;
	}

	public void setDesc(boolean desc) {
		this.desc = desc;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Integer getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(Integer startPosition) {
		this.startPosition = startPosition;
	}

	public Integer getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}
	
	public Date getToday(){
		return new Date();
	}

	@Override
	public String toString() {
		return "OrderSearchOptions [id=" + id + ", order date="
				+ orderDate + "]";
	}

}
