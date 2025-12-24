package rs.ac.singidunum.servelogic.model;

import java.util.Objects;
import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.ArangoId;


public abstract class AbstractEntity {

	@ArangoId
	private String id; //._id
	
	@Id
	private String key;	//._key
	
	protected AbstractEntity() {
		super();
	}

	protected AbstractEntity(String id, String key) {
		this.id = id;
		this.key = key;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String id) {
		this.key = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(key);
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
		return Objects.equals(key, other.key);
	}

}
