package training.osms.business;

import java.util.Date;

public class OrderSearchOptions {
	
	public enum Order{
		ORDER_NUMBER("orderNumber"),
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
	private String orderNumber;
	private Date orderDate;
	
	private boolean desc;
	private Order order;
	private Integer startPosition;
	private Integer maxResults;
	
	public OrderSearchOptions() {
		order = Order.ORDER_NUMBER;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
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

	@Override
	public String toString() {
		return "OrderSearchOptions [order number=" + orderNumber + ", order date="
				+ orderDate + "]";
	}

}
