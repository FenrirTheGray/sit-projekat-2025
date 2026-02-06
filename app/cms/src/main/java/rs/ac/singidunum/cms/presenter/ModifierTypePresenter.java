package rs.ac.singidunum.cms.presenter;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import rs.ac.singidunum.cms.dto.create.ModifierTypeCreateRequestDTO;
import rs.ac.singidunum.cms.dto.response.ModifierTypeResponseDTO;
import rs.ac.singidunum.cms.dto.update.ModifierTypeUpdateRequestDTO;
import rs.ac.singidunum.cms.service.ModifierTypeService;
import rs.ac.singidunum.cms.view.ModifierTypeManagementDialog;

import java.util.List;
import java.util.stream.Collectors;

@SpringComponent
@UIScope
public class ModifierTypePresenter {

	private ModifierTypeManagementDialog view;
	private final ModifierTypeService modifierTypeService;
	private List<ModifierTypeResponseDTO> allTypes;
	private String editingTypeId;

	public ModifierTypePresenter(ModifierTypeService modifierTypeService) {
		this.modifierTypeService = modifierTypeService;
	}

	public void setView(ModifierTypeManagementDialog view) {
		this.view = view;
	}

	public void onViewLoad() {
		loadTypes();
	}

	public void loadTypes() {
		view.showLoading();
		try {
			allTypes = modifierTypeService.findAll();
			if (allTypes == null || allTypes.isEmpty()) {
				view.showEmptyState("Nema tipova modifikatora");
			} else {
				view.setTypes(allTypes);
			}
		} catch (Exception e) {
			view.showError("Greška prilikom učitavanja: " + e.getMessage());
		} finally {
			view.hideLoading();
		}
	}

	public void onSearchChange(String searchText) {
		if (searchText == null || searchText.isEmpty()) {
			view.setTypes(allTypes);
			return;
		}

		List<ModifierTypeResponseDTO> filtered = allTypes.stream()
				.filter(t -> t.getName().toLowerCase().contains(searchText.toLowerCase()))
				.collect(Collectors.toList());

		if (filtered.isEmpty()) {
			view.showEmptyState("Nema rezultata za: " + searchText);
		} else {
			view.setTypes(filtered);
		}
	}

	public void onAddTypeClick() {
		editingTypeId = null;
		view.showFormForCreate();
	}

	public void onEditTypeClick(ModifierTypeResponseDTO type) {
		editingTypeId = type.getId();
		view.showFormForEdit(type);
	}

	public void onDeleteTypeClick(String typeId, String typeName) {
		view.showDeleteConfirmation(typeId, typeName);
	}

	public void confirmDeleteType(String typeId) {
		view.showLoading();
		try {
			modifierTypeService.deleteById(typeId);
			view.showSuccess("Tip modifikatora uspešno obrisan");
			loadTypes();
		} catch (Exception e) {
			view.showError("Greška prilikom brisanja: " + e.getMessage());
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
			if (editingTypeId == null) {
				ModifierTypeCreateRequestDTO createDTO = new ModifierTypeCreateRequestDTO();
				createDTO.setName(view.getTypeName());
				createDTO.setActive(view.getTypeActive());

				modifierTypeService.create(createDTO);
				view.showSuccess("Tip modifikatora uspešno kreiran");
			} else {
				ModifierTypeUpdateRequestDTO updateDTO = new ModifierTypeUpdateRequestDTO();
				updateDTO.setId(editingTypeId);
				updateDTO.setName(view.getTypeName());
				updateDTO.setActive(view.getTypeActive());

				modifierTypeService.update(editingTypeId, updateDTO);
				view.showSuccess("Tip modifikatora uspešno ažuriran");
			}
			view.hideForm();
			loadTypes();
		} catch (Exception e) {
			view.showError("Greška prilikom čuvanja: " + e.getMessage());
		} finally {
			view.hideLoading();
		}
	}

	public void onCancelFormClick() {
		editingTypeId = null;
		view.hideForm();
	}

	private boolean validateForm() {
		view.clearValidationErrors();
		boolean valid = true;

		if (view.getTypeName() == null || view.getTypeName().trim().isEmpty()) {
			view.showNameError("Naziv je obavezan");
			valid = false;
		}

		return valid;
	}
}
