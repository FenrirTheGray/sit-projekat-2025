package rs.ac.singidunum.servelogic.dto.file;

import java.util.List;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "modifiers")
public class ModifierXMLWrapper extends FileXMLWrapper<ModifierFileDTO> {
	
	public ModifierXMLWrapper() {
		super();
	}

	public ModifierXMLWrapper(List<ModifierFileDTO> data) {
		super(data);
	}

	@JacksonXmlProperty(localName = "modifier")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<ModifierFileDTO> getItems() {
		return super.getItems();
	}
}
