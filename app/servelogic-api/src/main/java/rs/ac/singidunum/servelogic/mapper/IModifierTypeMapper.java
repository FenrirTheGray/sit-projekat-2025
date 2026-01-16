package rs.ac.singidunum.servelogic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import rs.ac.singidunum.servelogic.dto.create.ModifierTypeCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.response.ModifierTypeResponseDTO;
import rs.ac.singidunum.servelogic.dto.update.ModifierTypeUpdateRequestDTO;
import rs.ac.singidunum.servelogic.model.ModifierType;

@Mapper(componentModel = "spring")
public interface IModifierTypeMapper {
	
	IModifierTypeMapper MAPPER = Mappers.getMapper(IModifierTypeMapper.class);
	
	ModifierType createToEntity(ModifierTypeCreateRequestDTO dto);
	ModifierType updateToEntity(ModifierTypeUpdateRequestDTO dto);
	ModifierTypeResponseDTO toResponse(ModifierType item);
	
}
