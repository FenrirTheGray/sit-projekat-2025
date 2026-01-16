package rs.ac.singidunum.servelogic.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Transient;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

@Document("article")
public class Article extends AbstractArangoEntity {

	private String name;
	private String description;
	private String imageUrl;
	private double basePrice;
	private boolean active;
	private String categoryId;
	
	@Transient
	private Category category;
	@Ref
	private List<Modifier> modifiers = new ArrayList<Modifier>();

	public Article() {
		super();
	}
	public Article(String id, String key, String name, String description, String imageUrl, double basePrice, boolean active) {
		super(id, key);
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
		this.basePrice = basePrice;
		this.active = active;
		this.category = null;
		this.categoryId = null;
	}
	public Article(String id, String key, String name, String description, String imageUrl, double basePrice, boolean active, Category category) {
		this(id, key, name, description, imageUrl, basePrice, active);
		this.category = category;
		if (category != null) {
			this.categoryId = category.getId();
		}
	}
	public Article(String id, String key, String name, String description, String imageUrl, double basePrice, boolean active, String categoryId) {
		this(id, key, name, description, imageUrl, basePrice, active);
		this.categoryId = categoryId;
		this.categoryId = category.getId();
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
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public List<Modifier> getModifiers() {
		return modifiers;
	}
	public void setModifiers(List<Modifier> modifiers) {
		this.modifiers = modifiers;
	}
	public void addModifier(Modifier modifier) {
		this.modifiers.add(modifier);
	}
	public void removeModifier(Modifier modifier) {
		this.modifiers.remove(modifier);
	}
	public void removeModifierByIndex(int i) {
		try {			
			this.modifiers.remove(i);
		} catch (Exception e) {}
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
		if (category != null) {
			this.categoryId = category.getId();
		}
	}
	
}
