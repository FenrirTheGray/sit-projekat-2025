package rs.ac.singidunum.cms.presenter;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import rs.ac.singidunum.cms.dto.response.ModifierResponseDTO;
import rs.ac.singidunum.cms.service.ModifierService;
import rs.ac.singidunum.cms.view.ModifiersView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringComponent
@UIScope
public class ModifiersPresenter {

	private ModifiersView view;
	private final ModifierService modifierService;
	private List<ModifierResponseDTO> allModifiers = new ArrayList<>();

	public ModifiersPresenter(ModifierService modifierService) {
		this.modifierService = modifierService;
	}

	public void setView(ModifiersView view) {
		this.view = view;
	}

	public void onViewLoad() {
		loadModifiers();
	}

	public void loadModifiers() {
		view.showLoading();
		try {
			allModifiers = modifierService.findAll();
			if (allModifiers == null || allModifiers.isEmpty()) {
				allModifiers = new ArrayList<>();
				view.showEmptyState("Nema modifikatora za prikaz");
			} else {
				view.setModifiers(allModifiers);
			}
		} catch (Exception e) {
			view.showError("Greška prilikom učitavanja: " + e.getMessage());
		} finally {
			view.hideLoading();
		}
	}

	public void onSearchChange(String searchText) {
		if (allModifiers == null || allModifiers.isEmpty()) {
			view.showEmptyState("Nema modifikatora za prikaz");
			return;
		}

		if (searchText == null || searchText.isEmpty()) {
			view.setModifiers(allModifiers);
			return;
		}

		String searchLower = searchText.toLowerCase();
		List<ModifierResponseDTO> filtered = allModifiers.stream()
				.filter(m -> m.getName().toLowerCase().contains(searchLower)
						|| (m.getType() != null && m.getType().getName().toLowerCase().contains(searchLower)))
				.collect(Collectors.toList());

		if (filtered.isEmpty()) {
			view.showEmptyState("Nema rezultata za: " + searchText);
		} else {
			view.setModifiers(filtered);
		}
	}

	public void onAddModifierClick() {
		view.navigateToModifierCreate();
	}

	public void onEditModifierClick(String modifierKey) {
		view.navigateToModifierEdit(modifierKey);
	}

	public void onDeleteModifierClick(String modifierKey, String modifierName) {
		view.showDeleteConfirmation(modifierKey, modifierName);
	}

	public void confirmDeleteModifier(String modifierKey) {
		view.showLoading();
		try {
			modifierService.deleteByKey(modifierKey);
			view.showSuccess("Modifikator uspešno obrisan");
			loadModifiers();
		} catch (Exception e) {
			view.showError("Greška prilikom brisanja: " + e.getMessage());
		} finally {
			view.hideLoading();
		}
	}

	public void onManageTypesClick() {
		view.openModifierTypeManagementDialog();
	}

	public void onTypesDialogClosed() {
		loadModifiers();
	}
}
