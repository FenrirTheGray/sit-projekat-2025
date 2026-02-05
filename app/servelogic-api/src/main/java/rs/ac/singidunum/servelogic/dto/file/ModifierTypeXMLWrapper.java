package rs.ac.singidunum.servelogic.dto.file;

import java.util.List;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import rs.ac.singidunum.servelogic.dto.response.ModifierTypeResponseDTO;

@JacksonXmlRootElement(localName = "modifierTypes")
public class ModifierTypeXMLWrapper extends FileXMLWrapper<ModifierTypeResponseDTO> {
	
	public ModifierTypeXMLWrapper() {
		super();
	}

	public ModifierTypeXMLWrapper(List<ModifierTypeResponseDTO> data) {
		super(data);
	}

	@JacksonXmlProperty(localName = "modifierType")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<ModifierTypeResponseDTO> getItems() {
		return super.getItems();
	}

}
