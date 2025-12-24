package rs.ac.singidunum.servelogic.model;

import java.util.ArrayList;
import java.util.List;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Ref;

@Document("article")
public class Article extends AbstractEntity {

	private String name;
	private String description;
	private double basePrice;
	private boolean active;
	
	@Ref
	private List<Modification> modifications = new ArrayList<Modification>();

	protected Article() {
		super();
	}

	public Article(String id, String key, String name, String description, double basePrice, boolean active) {
		super(id, key);
		this.name = name;
		this.description = description;
		this.basePrice = basePrice;
		this.active = active;
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

	public List<Modification> getModifications() {
		return modifications;
	}

	public void setModifications(List<Modification> modifications) {
		this.modifications = modifications;
	}
	
	public void addModification(Modification modification) {
		this.modifications.add(modification);
	}
	
	public void removeModification(Modification modification) {
		this.modifications.remove(modification);
	}
	
	public void removeModificationByIndex(int i) {
		try {			
			this.modifications.remove(i);
		} catch (Exception e) {}
	}
	
}
