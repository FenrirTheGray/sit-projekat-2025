package rs.ac.singidunum.servelogic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import rs.ac.singidunum.servelogic.dto.create.ModifierTypeCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.response.ModifierTypeResponseDTO;
import rs.ac.singidunum.servelogic.dto.update.ModifierTypeUpdateRequestDTO;
import rs.ac.singidunum.servelogic.model.ModifierType;

@Mapper(componentModel = "spring")
public interface IModifierTypeMapper {
	
	ModifierType createToEntity(ModifierTypeCreateRequestDTO dto);
	ModifierType updateToEntity(ModifierTypeUpdateRequestDTO dto);
	ModifierTypeResponseDTO toResponse(ModifierType item);
	@Mapping(target = "id", ignore = true)
	public abstract void updateModifierTypeFromDto(ModifierTypeResponseDTO dto, @MappingTarget ModifierType item);
	
}
