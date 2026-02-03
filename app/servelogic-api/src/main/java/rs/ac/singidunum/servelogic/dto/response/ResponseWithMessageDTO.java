package rs.ac.singidunum.servelogic.dto.response;

public class ResponseWithMessageDTO {
	private String message;

	public ResponseWithMessageDTO(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
