package rs.ac.singidunum.servelogic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.singidunum.servelogic.dto.file.ModifierTypeXMLWrapper;
import rs.ac.singidunum.servelogic.dto.response.ModifierTypeResponseDTO;
import rs.ac.singidunum.servelogic.model.ModifierType;
import rs.ac.singidunum.servelogic.service.ModifierTypeService;

@RestController
@RequestMapping("/files/modifiertypes")
public class ModifierTypeFileController extends AbstractFileController<ModifierType, ModifierTypeResponseDTO, ModifierTypeXMLWrapper, ModifierTypeService> {
	@Override
	protected String getFilename() {
		return "modifiertypes";
	}
}
