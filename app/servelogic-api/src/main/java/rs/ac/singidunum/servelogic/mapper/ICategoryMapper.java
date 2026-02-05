package rs.ac.singidunum.servelogic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import rs.ac.singidunum.servelogic.dto.create.CategoryCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.response.CategoryResponseDTO;
import rs.ac.singidunum.servelogic.dto.update.CategoryUpdateRequestDTO;
import rs.ac.singidunum.servelogic.model.Category;

@Mapper(componentModel = "spring")
public interface ICategoryMapper {
	
	Category createToEntity(CategoryCreateRequestDTO dto);
	Category updateToEntity(CategoryUpdateRequestDTO dto);
	CategoryResponseDTO toResponse(Category item);
	@Mapping(target = "id", ignore = true)
	public abstract void updateCategoryFromDto(CategoryResponseDTO dto, @MappingTarget Category item);
	
}
