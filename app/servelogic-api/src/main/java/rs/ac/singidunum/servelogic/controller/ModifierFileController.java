package rs.ac.singidunum.servelogic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.singidunum.servelogic.dto.file.ModifierFileDTO;
import rs.ac.singidunum.servelogic.dto.file.ModifierXMLWrapper;
import rs.ac.singidunum.servelogic.model.Modifier;
import rs.ac.singidunum.servelogic.service.ModifierService;

@RestController
@RequestMapping("/files/modifiers")
public class ModifierFileController extends AbstractFileController<Modifier, ModifierFileDTO, ModifierXMLWrapper, ModifierService>  {

	@Override
	protected String getFilename() {
		return "modifiers";
	}

}
