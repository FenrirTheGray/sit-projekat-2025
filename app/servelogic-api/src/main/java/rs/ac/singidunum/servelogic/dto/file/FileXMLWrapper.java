package rs.ac.singidunum.servelogic.dto.file;

import java.util.List;

public abstract class FileXMLWrapper<T> {
	
	private List<T> items;

	public FileXMLWrapper() {
		super();
	}

	public FileXMLWrapper(List<T> items) {
        this.items = items;
    }

    public List<T> getItems() {
        return items;
    }

	public void setItems(List<T> items) {
		this.items = items;
	}
}
