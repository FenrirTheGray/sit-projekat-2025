package rs.ac.singidunum.servelogic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.singidunum.servelogic.dto.create.ModifierCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.response.ModifierResponseDTO;
import rs.ac.singidunum.servelogic.dto.update.ModifierUpdateRequestDTO;
import rs.ac.singidunum.servelogic.model.Modifier;
import rs.ac.singidunum.servelogic.service.ModifierService;

@RestController
@RequestMapping(value={"/api/modifiers"})
public class ModifierDataController extends AbstractDataController<Modifier, ModifierResponseDTO, ModifierCreateRequestDTO, ModifierUpdateRequestDTO, ModifierService> {
	
}
