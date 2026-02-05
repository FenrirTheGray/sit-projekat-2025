package rs.ac.singidunum.servelogic.dto.file;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface IAbstractDTO {
	@JsonIgnore
	String getIdOrKey();
}
