package rs.ac.singidunum.servelogic.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import rs.ac.singidunum.servelogic.dto.file.FileXMLWrapper;

@Service
public abstract class AbstractService <T, R, C, U, F, X extends FileXMLWrapper<F>> {

	private final ObjectMapper jsonMapper = new ObjectMapper();
    private final XmlMapper xmlMapper = new XmlMapper();
    
    public abstract List<R> findAll();
    public abstract List<F> findAllExport();
    public abstract Optional<R> findById(String id);
    public abstract Optional<R> create(C item);
    public abstract Optional<R> update(U item);
    public abstract void deleteById(String id);
    
    @Transactional
	public void importData(byte[] fileData, String format) throws Exception {
	    List<F> dtos = convertToDtos(fileData, format);
	    processEntities(dtos);
	}
    
    protected abstract void processEntities(List<F> dtos);
    
    private List<F> convertToDtos(byte[] fileData, String format) throws Exception {
        if (format.equalsIgnoreCase("json")) {
        	JsonNode node = jsonMapper.readTree(fileData);
            return jsonMapper.convertValue(node, new TypeReference<List<F>>() {});
        } else {
        	X wrapper = xmlMapper.readValue(fileData, 
                    new TypeReference<X>() {});
                return wrapper.getItems();
        }
    }
    
    public abstract X wrapper(List<F> data);
}
