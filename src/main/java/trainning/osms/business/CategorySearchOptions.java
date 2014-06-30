package trainning.osms.business;

public class CategorySearchOptions {
	
	public enum Order{
		NAME("name"),
		DESCRIPTION("description");
		
		private String value;
		
		//construtor tem que ser privado.
		private Order(String value){
			this.value = value;
		}
		
		public String getValue(){
			return value;
		}		
	}
	
	private String name;
	private String description;
	
	private boolean desc;
	private Order order;
	private Integer startPosition;
	private Integer maxResults;
	
	public CategorySearchOptions() {
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
		return "CategorySearchOptions [name=" + name + ", description="
				+ description + "]";
	}

}
