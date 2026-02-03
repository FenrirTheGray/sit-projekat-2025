package rs.ac.singidunum.servelogic.dto.file;

import java.util.List;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "articles")
public class ArticleXMLWrapper<T> {

	@JacksonXmlProperty(localName = "article")
    @JacksonXmlElementWrapper(useWrapping = false)
	private List<T> items;

    public ArticleXMLWrapper() {
		super();
	}

	public ArticleXMLWrapper(List<T> items) {
        this.items = items;
    }

    public List<T> getItems() {
        return items;
    }

	public void setItems(List<T> items) {
		this.items = items;
	}
}