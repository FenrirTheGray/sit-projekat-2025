package rs.ac.singidunum.servelogic.mapper;

import org.mapstruct.Mapper;
import rs.ac.singidunum.servelogic.dto.response.ComboResponseDTO;
import rs.ac.singidunum.servelogic.model.Combo;

@Mapper(componentModel = "spring")
public abstract class ComboMapper {

    public abstract ComboResponseDTO toResponse(Combo combo);
}
