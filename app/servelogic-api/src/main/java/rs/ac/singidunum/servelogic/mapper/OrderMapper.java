package rs.ac.singidunum.servelogic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.SubclassMapping;
import org.springframework.beans.factory.annotation.Autowired;
import rs.ac.singidunum.servelogic.dto.create.ArticleChoiceCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.create.ChoiceCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.create.ComboChoiceCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.create.OrderCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.response.OrderResponseDTO;
import rs.ac.singidunum.servelogic.dto.update.OrderUpdateRequestDTO;
import rs.ac.singidunum.servelogic.model.*;
import rs.ac.singidunum.servelogic.repository.IArticleRepository;
import rs.ac.singidunum.servelogic.repository.IComboRepository;
import rs.ac.singidunum.servelogic.repository.IModifierRepository;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    @Autowired
    protected IComboRepository comboRepo;
    @Autowired
    protected IArticleRepository articleRepo;
    @Autowired
    protected IModifierRepository modifierRepo;

    public abstract Order createToEntity(OrderCreateRequestDTO dto);
    public abstract Order updateToEntity(OrderUpdateRequestDTO dto);
    public abstract OrderResponseDTO toResponse(Order order);
    
    @SubclassMapping(source = ArticleChoiceCreateRequestDTO.class, target = ArticleChoice.class)
    @SubclassMapping(source = ComboChoiceCreateRequestDTO.class, target = ComboChoice.class)
    public abstract Choice mapToChoice(ChoiceCreateRequestDTO choiceCreateRequestDTO);

    public Combo idToCombo(String id){
        if(id == null) return null;
        return comboRepo.findById(id).orElse(null);
    }

    public Article idToArticle(String id){
        if(id == null) return null;
        return articleRepo.findById(id).orElse(null);
    }

    public abstract List<Modifier> toModifierList(List<String> ids);
    public Modifier idToModifier(String id) {
        if (id == null) return null;
        return modifierRepo.findById(id).orElse(null);
    }

}
