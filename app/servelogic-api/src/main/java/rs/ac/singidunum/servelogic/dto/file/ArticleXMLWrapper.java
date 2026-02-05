package rs.ac.singidunum.servelogic.dto.file;

import java.util.List;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "articles")
public class ArticleXMLWrapper extends FileXMLWrapper<ArticleFileDTO> {

	@JacksonXmlProperty(localName = "article")
    @JacksonXmlElementWrapper(useWrapping = false)
	private List<ArticleFileDTO> items;

	public ArticleXMLWrapper() {
		super();
	}

	public ArticleXMLWrapper(List<ArticleFileDTO> items) {
		super(items);
	}

}