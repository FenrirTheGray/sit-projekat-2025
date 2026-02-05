package rs.ac.singidunum.servelogic.dto.file;

import java.util.List;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import rs.ac.singidunum.servelogic.dto.response.CategoryResponseDTO;

@JacksonXmlRootElement(localName = "categories")
public class CategoryXMLWrapper extends FileXMLWrapper<CategoryResponseDTO>{

	public CategoryXMLWrapper() {
		super();
	}

	public CategoryXMLWrapper(List<CategoryResponseDTO> data) {
		super(data);
	}

	@JacksonXmlProperty(localName = "category")
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<CategoryResponseDTO> getItems() {
		return super.getItems();
	}
	
}