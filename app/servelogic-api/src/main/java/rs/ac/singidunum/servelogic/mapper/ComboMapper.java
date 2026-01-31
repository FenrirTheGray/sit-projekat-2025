package rs.ac.singidunum.servelogic.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import rs.ac.singidunum.servelogic.dto.create.ComboCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.response.ComboResponseDTO;
import rs.ac.singidunum.servelogic.model.Article;
import rs.ac.singidunum.servelogic.model.Combo;
import rs.ac.singidunum.servelogic.repository.IArticleRepository;
import rs.ac.singidunum.servelogic.utility.ArangoFusekiReferenceService;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ComboMapper {

    @Autowired
    protected IArticleRepository articleRepo;
    @Autowired
    protected ArangoFusekiReferenceService populator;

    public abstract ComboResponseDTO toResponse(Combo combo);
    public abstract Combo createToEntity(ComboCreateRequestDTO dto);
    public abstract List<Article> toArticleList(List<String> ids);

    public Article idToArticle(String id){
        if(id == null) return null;

        return populator.populate(articleRepo.findById(id).orElse(null));
    }

}
