package rs.ac.singidunum.servelogic.model;

import java.util.Objects;

import org.springframework.data.annotation.Id;

public abstract class AbstractFusekiEntity {

	public static final String ns = "%s/%s#".formatted(
			System.getenv().getOrDefault("FUSEKI_NAMESPACEROOT", "http://www.singidunum.ac.rs"),
			System.getenv().getOrDefault("FUSEKI_DATASET", "servelogic"));
	
	@Id
	private String id;
	
	public AbstractFusekiEntity() {
		super();
	}

	public AbstractFusekiEntity(String id) {
		this();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return "%s%s".formatted(ns, id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AbstractFusekiEntity)) {
			return false;
		}
		AbstractFusekiEntity other = (AbstractFusekiEntity) obj;
		return Objects.equals(id, other.id);
	}
	
}
