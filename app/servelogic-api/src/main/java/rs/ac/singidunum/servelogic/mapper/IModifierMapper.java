package rs.ac.singidunum.servelogic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import rs.ac.singidunum.servelogic.dto.create.ModifierCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.file.ModifierFileDTO;
import rs.ac.singidunum.servelogic.dto.response.ModifierResponseDTO;
import rs.ac.singidunum.servelogic.dto.update.ModifierUpdateRequestDTO;
import rs.ac.singidunum.servelogic.model.Modifier;

@Mapper(componentModel = "spring", uses = {IModifierMapper.class})
public interface IModifierMapper {
	
	Modifier createToEntity(ModifierCreateRequestDTO dto);
	Modifier updateToEntity(ModifierUpdateRequestDTO dto);
	ModifierResponseDTO toResponse(Modifier item);
	
	@Mapping(target = "id", ignore = true)
	public abstract void updateModifierFromDto(ModifierFileDTO dto, @MappingTarget Modifier item);
	
}
