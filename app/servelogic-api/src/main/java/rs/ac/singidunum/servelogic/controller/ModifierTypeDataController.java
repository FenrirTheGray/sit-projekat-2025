package rs.ac.singidunum.servelogic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.singidunum.servelogic.dto.create.ModifierTypeCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.response.ModifierTypeResponseDTO;
import rs.ac.singidunum.servelogic.dto.update.ModifierTypeUpdateRequestDTO;
import rs.ac.singidunum.servelogic.model.ModifierType;
import rs.ac.singidunum.servelogic.service.ModifierTypeService;

@RestController
@RequestMapping(value = { "/api/modifiertypes" })
public class ModifierTypeDataController extends AbstractDataController<ModifierType, ModifierTypeResponseDTO, ModifierTypeCreateRequestDTO, ModifierTypeUpdateRequestDTO, ModifierTypeService> {
	
}
