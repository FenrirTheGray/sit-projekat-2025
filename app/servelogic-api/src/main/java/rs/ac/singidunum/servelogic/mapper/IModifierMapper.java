package rs.ac.singidunum.servelogic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import rs.ac.singidunum.servelogic.dto.create.ModifierCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.response.ModifierResponseDTO;
import rs.ac.singidunum.servelogic.dto.update.ModifierUpdateRequestDTO;
import rs.ac.singidunum.servelogic.model.Modifier;

@Mapper(componentModel = "spring")
public interface IModifierMapper {
	
	IModifierMapper MAPPER = Mappers.getMapper(IModifierMapper.class);
	
	Modifier createToEntity(ModifierCreateRequestDTO dto);
	Modifier updateToEntity(ModifierUpdateRequestDTO dto);
	ModifierResponseDTO toResponse(Modifier item);
	
}
