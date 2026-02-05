package rs.ac.singidunum.servelogic.dto.file;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import rs.ac.singidunum.servelogic.dto.response.CategoryResponseDTO;

@JacksonXmlRootElement(localName = "categories")
public class CategoryXMLWrapper extends FileXMLWrapper<CategoryResponseDTO>{

	@JacksonXmlProperty(localName = "category")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<CategoryResponseDTO> items;
	
	public CategoryXMLWrapper(List<CategoryResponseDTO> data) {
		super(data);
	}
	
}