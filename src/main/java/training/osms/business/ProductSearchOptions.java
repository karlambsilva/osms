package training.osms.business;

public class ProductSearchOptions {
	
	public enum Order{
		NAME("name"),
		PRICE("price");
		
		private String value;
		
		//construtor tem que ser privado.
		private Order(String value){
			this.value = value;
		}
		
		public String getValue(){
			return value;
		}		
	}
	private Integer productId;
	private Integer categoryId;	
	private boolean desc;
	private Order order;
	private Integer startPosition;
	private Integer maxResults;
	
	private String name;
	private String description;
	private Double price;
	
	public ProductSearchOptions() {
		order = Order.NAME;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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

	public Double getPrice() {		
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "CategorySearchOptions [name=" + name + ", description="
				+ description + "]";
	}

}
