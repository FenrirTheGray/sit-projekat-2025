package rs.ac.singidunum.servelogic.service;

import com.arangodb.ArangoCursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.servelogic.dto.create.ComboCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.response.ComboResponseDTO;
import rs.ac.singidunum.servelogic.dto.update.ComboUpdateRequestDTO;
import rs.ac.singidunum.servelogic.mapper.ComboMapper;
import rs.ac.singidunum.servelogic.model.Combo;
import rs.ac.singidunum.servelogic.repository.IComboRepository;
import rs.ac.singidunum.servelogic.utility.ArangoFusekiReferenceService;

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

    public Optional<ComboResponseDTO> create(ComboCreateRequestDTO comboDTO){
        Combo combo = mapper.createToEntity(comboDTO);

        combo = populator.populate(combo);

        if(
                combo == null ||
                        combo.getMainSelection() == null ||
                        combo.getDrinkSelection() == null ||
                        combo.getSideSelection() == null
        ) return Optional.empty();

        return Optional.of(mapper.toResponse(repo.save(combo)));
    }

    public Optional<ComboResponseDTO> update(ComboUpdateRequestDTO comboDTO){
        Optional<Combo> opCombo = findComboByKey(comboDTO.getKey());
        if(opCombo.isEmpty()) return Optional.empty();

        Combo combo = opCombo.get();

        if(comboDTO.getName() != null) combo.setName(comboDTO.getName());
        if(comboDTO.getDescription() != null) combo.setDescription(comboDTO.getDescription());
        if(comboDTO.getImageUrl() != null) combo.setImageUrl(comboDTO.getImageUrl());
        if(comboDTO.getBasePrice() != null) combo.setBasePrice(comboDTO.getBasePrice());
        if(comboDTO.getActive() != null) combo.setActive(comboDTO.getActive());
        if(comboDTO.getMainSelection() != null) combo.setMainSelection(mapper.toArticleList(comboDTO.getMainSelection()));
        if(comboDTO.getSideSelection() != null) combo.setSideSelection(mapper.toArticleList(comboDTO.getSideSelection()));
        if(comboDTO.getDrinkSelection() != null) combo.setDrinkSelection(mapper.toArticleList(comboDTO.getDrinkSelection()));

        repo.save(combo);

        return Optional.of(mapper.toResponse(combo));
    }

    public boolean deleteByKey(String key){
        Optional<Combo> opCombo = findComboByKey(key);
        if(opCombo.isEmpty()) return false;

        repo.delete(opCombo.get());

        return true;
    }

    private Optional<Combo> findComboByKey(String key){
        ArangoCursor<Combo> cursor = repo.findByKey(key);

        if(!cursor.hasNext()) return Optional.empty();

        return Optional.of(cursor.next());
    }


}
