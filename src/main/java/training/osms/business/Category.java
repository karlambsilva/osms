package training.osms.business;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="CAT_CATEGORY")
public class Category implements Cloneable{
	
	private Integer id;
	private String name;
	private String description;
	private List<Product> products;
	private Category parentCategory;
	private List<Category> subCategories;
	
	
	public Category() {
		products = new ArrayList<>();
		subCategories = new ArrayList<>();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CAT_ID")
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Size(min=1, max=100)
	@Column(name="CAT_NAME")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Size(min=1, max=1000)
	@Column(name="CAT_DESCRIPTION")
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@OneToMany(mappedBy="category", cascade=CascadeType.REMOVE)
	public List<Product> getProducts() {
		return products;
	}
	
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@ManyToOne
	@JoinColumn(name="CAT_PARENT_CATEGORY")
	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	@OneToMany(mappedBy="parentCategory")
	public List<Category> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<Category> subCategories) {
		this.subCategories = subCategories;
	}
	
	@Override
	public Category clone() {
		try {
			return (Category) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new Error("Cloning is not supported for this object.");
		}  
	}


}
