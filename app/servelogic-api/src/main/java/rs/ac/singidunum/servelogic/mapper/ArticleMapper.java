package rs.ac.singidunum.servelogic.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import rs.ac.singidunum.servelogic.dto.create.ArticleCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.file.ArticleFileDTO;
import rs.ac.singidunum.servelogic.dto.response.ArticleResponseDTO;
import rs.ac.singidunum.servelogic.dto.update.ArticleUpdateRequestDTO;
import rs.ac.singidunum.servelogic.model.Article;
import rs.ac.singidunum.servelogic.model.Category;
import rs.ac.singidunum.servelogic.model.Modifier;
import rs.ac.singidunum.servelogic.repository.CategoryRepository;
import rs.ac.singidunum.servelogic.repository.IModifierRepository;
import rs.ac.singidunum.servelogic.utility.ArangoFusekiReferenceService;

@Mapper(componentModel = "spring", uses = {IModifierMapper.class})
public abstract class ArticleMapper {
	
	@Autowired
	protected IModifierRepository modifierRepo;
	@Autowired
	protected CategoryRepository categoryRepo;
	@Autowired
	protected ArangoFusekiReferenceService populator;
	
	public abstract Article createToEntity(ArticleCreateRequestDTO dto);
	public abstract Article updateToEntity(ArticleUpdateRequestDTO dto);
	public abstract ArticleResponseDTO toResponse(Article item);
	public abstract List<Modifier> toModifierList(List<String> ids);
	public abstract ArticleFileDTO toExport(Article item);
	public abstract List<String> fromModifierList(List<Modifier> modifiers);
	
	@Mapping(target = "id", ignore = true)
    public abstract void updateArticleFromDto(ArticleFileDTO dto, @MappingTarget Article article);
	
	public Modifier idToModifier(String id) {
		if (id == null) {
			return null;
		}
		return populator.populate(modifierRepo.findById(id).orElse(null));
	}
	public Category idToCategory(String id) {
		if (id == null) {
			return null;
		}
		return categoryRepo.findById(id).orElse(null);
	}
	
	public String idFromModifier(Modifier modifier) {
		return modifier.getId();
	}
	
}
