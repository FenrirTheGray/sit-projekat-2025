package rs.ac.singidunum.servelogic.dto;

public abstract class AbstractFusekiDTO implements IAbstractDTO {
	String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getIdOrKey() {
		return getId();
	}
}
