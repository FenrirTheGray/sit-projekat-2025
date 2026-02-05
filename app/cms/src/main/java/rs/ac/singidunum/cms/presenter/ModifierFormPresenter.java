package rs.ac.singidunum.cms.presenter;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import rs.ac.singidunum.cms.dto.create.ModifierCreateRequestDTO;
import rs.ac.singidunum.cms.dto.response.ModifierResponseDTO;
import rs.ac.singidunum.cms.dto.response.ModifierTypeResponseDTO;
import rs.ac.singidunum.cms.dto.update.ModifierUpdateRequestDTO;
import rs.ac.singidunum.cms.service.ModifierService;
import rs.ac.singidunum.cms.service.ModifierTypeService;
import rs.ac.singidunum.cms.view.ModifierFormView;

import java.util.List;

@SpringComponent
@UIScope
public class ModifierFormPresenter {

	private ModifierFormView view;
	private final ModifierService modifierService;
	private final ModifierTypeService modifierTypeService;
	private String editingModifierKey;

	public ModifierFormPresenter(ModifierService modifierService, ModifierTypeService modifierTypeService) {
		this.modifierService = modifierService;
		this.modifierTypeService = modifierTypeService;
	}

	public void setView(ModifierFormView view) {
		this.view = view;
	}

	public void onViewLoad(String modifierKey) {
		this.editingModifierKey = modifierKey;
		loadModifierTypes();

		if (modifierKey != null && !modifierKey.isEmpty()) {
			loadModifierForEdit(modifierKey);
		} else {
			view.clearForm();
		}
	}

	private void loadModifierTypes() {
		try {
			List<ModifierTypeResponseDTO> types = modifierTypeService.findAll();
			if (types != null && !types.isEmpty()) {
				view.setAvailableTypes(types);
			} else {
				view.showError("Nema dostupnih tipova modifikatora. Kreirajte tip pre nego što dodate modifikator.");
			}
		} catch (Exception e) {
			view.showError("Greška pri učitavanju tipova: " + e.getMessage());
		}
	}

	private void loadModifierForEdit(String modifierKey) {
		view.showLoading();
		try {
			ModifierResponseDTO modifier = modifierService.findByKey(modifierKey);
			if (modifier != null) {
				view.setModifierData(modifier);
			} else {
				view.showError("Modifikator nije pronađen");
				view.navigateBackToModifiers();
			}
		} catch (Exception e) {
			view.showError("Greška prilikom učitavanja modifikatora: " + e.getMessage());
		} finally {
			view.hideLoading();
		}
	}

	public void onSaveClick() {
		if (!validateForm()) {
			return;
		}

		view.showLoading();
		try {
			if (editingModifierKey == null || editingModifierKey.isEmpty()) {
				createModifier();
			} else {
				updateModifier();
			}
			view.navigateBackToModifiers();
		} catch (Exception e) {
			view.showError("Greška prilikom čuvanja: " + e.getMessage());
		} finally {
			view.hideLoading();
		}
	}

	private void createModifier() {
		ModifierCreateRequestDTO dto = new ModifierCreateRequestDTO();
		dto.setName(view.getModifierName());
		dto.setDescription(view.getModifierDescription());
		dto.setPrice(view.getModifierPrice());
		dto.setActive(view.getModifierActive());
		dto.setTypeId(view.getSelectedTypeId());

		modifierService.create(dto);
		view.showSuccess("Modifikator uspešno kreiran");
	}

	private void updateModifier() {
		ModifierUpdateRequestDTO dto = new ModifierUpdateRequestDTO();
		dto.setKey(editingModifierKey);
		dto.setName(view.getModifierName());
		dto.setDescription(view.getModifierDescription());
		dto.setPrice(view.getModifierPrice());
		dto.setActive(view.getModifierActive());
		dto.setTypeId(view.getSelectedTypeId());

		modifierService.update(editingModifierKey, dto);
		view.showSuccess("Modifikator uspešno ažuriran");
	}

	public void onCancelClick() {
		view.navigateBackToModifiers();
	}

	private boolean validateForm() {
		view.clearValidationErrors();
		boolean valid = true;

		String name = view.getModifierName();
		if (name == null || name.trim().isEmpty()) {
			view.showNameError("Naziv je obavezan");
			valid = false;
		}

		Double price = view.getModifierPrice();
		if (price == null || price < 0) {
			view.showPriceError("Cena mora biti veća ili jednaka 0");
			valid = false;
		}

		String typeId = view.getSelectedTypeId();
		if (typeId == null || typeId.isEmpty()) {
			view.showTypeError("Potrebno je izabrati tip modifikatora");
			valid = false;
		}

		return valid;
	}
}
