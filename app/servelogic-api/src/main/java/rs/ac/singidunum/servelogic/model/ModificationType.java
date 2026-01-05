package rs.ac.singidunum.servelogic.model;

import org.springframework.data.annotation.Id;

public class ModificationType {
	
	public static final String ns = "http://www.singidunum.ac.rs/servelogic#";
	
	@Id
	private String id;
	private String name;
	private boolean active;
	
	public ModificationType() {
		super();
	}

	public ModificationType(String id, String name, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.active = active;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public String getUrl() {
		return "%s%s".formatted(ns, id);
	}
	
}
