package rs.ac.singidunum.servelogic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    
    protected abstract Class<F> getDtoClass();
    protected abstract Class<X> getXmlWrapperClass();
    
//    TODO: abstract it when implemented fully to keep the codebase clean
//    	To override. Doesn't break existing functionaity 
    protected byte[] convertDtosToRdf(List<F> dtos) {
    	return new byte[0];
    }
    protected List<F> convertRdfToDtos(byte[] fileData) throws Exception {
    	return new ArrayList<F>();
    }

    public byte[] exportRdf(List<F> data) {
        return convertDtosToRdf(data);
    }
    
    @Transactional
	public void importData(byte[] fileData, String format) throws Exception {
    	List<F> dtos;
        if (format.equalsIgnoreCase("rdf")) {
            dtos = convertRdfToDtos(fileData);
        } else {
            dtos = convertToDtos(fileData, format);
        }
        processEntities(dtos);
	}
    
    protected abstract void processEntities(List<F> dtos);
    
    private List<F> convertToDtos(byte[] fileData, String format) throws Exception {
        if (format.equalsIgnoreCase("json")) {
        	var listType = jsonMapper.getTypeFactory()
                    .constructCollectionType(ArrayList.class, getDtoClass());
                return jsonMapper.readValue(fileData, listType);
        } else {
            X wrapper = xmlMapper.readValue(fileData, getXmlWrapperClass());
            return wrapper.getItems();
        }
    }
    
    public abstract X wrapper(List<F> data);
}
