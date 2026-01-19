package sitprojekat.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;


public abstract class AbstractEntity {

	@JsonProperty("key")    // arangoDB key je nama id
	private String id;

	public AbstractEntity(){}
		
	protected AbstractEntity(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
		if (!(obj instanceof AbstractEntity)) {
			return false;
		}
		AbstractEntity other = (AbstractEntity) obj;
		return Objects.equals(id, other.id);
	}

}


