package rs.ac.singidunum.servelogic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.servelogic.dto.response.ComboResponseDTO;
import rs.ac.singidunum.servelogic.mapper.ArticleMapper;
import rs.ac.singidunum.servelogic.mapper.ComboMapper;
import rs.ac.singidunum.servelogic.model.Combo;
import rs.ac.singidunum.servelogic.repository.IArticleRepository;
import rs.ac.singidunum.servelogic.repository.IComboRepository;
import rs.ac.singidunum.servelogic.utility.ArangoFusekiReferenceService;

import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ComboService {

    @Autowired
    private IComboRepository repo;
    @Autowired
    private ArangoFusekiReferenceService populator;
    @Autowired
    private ComboMapper mapper;

    public List<ComboResponseDTO> findAll(){
        List<ComboResponseDTO> allCombos = new ArrayList<>();

        repo.findAll().forEach(combo -> allCombos.add(mapper.toResponse(populator.populate(combo))));

        return allCombos;
    }
    public List<Combo> findAllInit(){
        List<Combo> allCombos = new ArrayList<>();

        repo.findAll().forEach(combo -> allCombos.add(populator.populate(combo)));

        return allCombos;
    }

    public Optional<ComboResponseDTO> findByKey(String key) {
        Optional<Combo> item = repo.findById(key);
        if(item.isPresent()){
            return Optional.of(mapper.toResponse(populator.populate(item.get())));
        }

        return Optional.empty();
    }
}
