package training.osms.business;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ORD_ORDER")
public class Order {
	
	private Integer id;
	private String orderNumber;
	private List <Product> products;
	private Date orderDate;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ORD_ID")
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="ORD_ORDER_NUMBER")
	public String getOrderNumber() {
		return orderNumber;
	}
	
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	@ManyToMany
	@JoinTable(
			name="ORP_ORDER_PRODUCT",
			joinColumns=@JoinColumn(name="PRO_ID"),
			inverseJoinColumns=@JoinColumn(name="ORD_ID")
		)
	public List<Product> getProducts() {
		return products;
	}
	
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ORD_ORDER_DATE")
	public Date getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
}
