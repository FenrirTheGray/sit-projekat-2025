package rs.ac.singidunum.servelogic.dto.file;

public abstract class AbstractArangoDTO implements IAbstractDTO {
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String getIdOrKey() {
		return getKey();
	}
}
