package rs.ac.singidunum.servelogic.dto.file;

import java.util.List;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "modifiers")
public class ModifierXMLWrapper extends FileXMLWrapper<ModifierFileDTO> {

	@JacksonXmlProperty(localName = "modifier")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<ModifierFileDTO> items;
	
	public ModifierXMLWrapper(List<ModifierFileDTO> data) {
		super(data);
	}
}
