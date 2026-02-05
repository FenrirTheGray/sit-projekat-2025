package rs.ac.singidunum.cms.presenter;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import rs.ac.singidunum.cms.dto.create.ComboCreateRequestDTO;
import rs.ac.singidunum.cms.dto.response.ArticleResponseDTO;
import rs.ac.singidunum.cms.dto.response.ComboResponseDTO;
import rs.ac.singidunum.cms.dto.update.ComboUpdateRequestDTO;
import rs.ac.singidunum.cms.service.ArticleService;
import rs.ac.singidunum.cms.service.ComboService;
import rs.ac.singidunum.cms.view.ComboFormView;

import java.util.Collections;
import java.util.List;

@SpringComponent
@UIScope
public class ComboFormPresenter {

	private ComboFormView view;
	private final ComboService comboService;
	private final ArticleService articleService;
	private String editingComboKey;

	public ComboFormPresenter(ComboService comboService, ArticleService articleService) {
		this.comboService = comboService;
		this.articleService = articleService;
	}

	public void setView(ComboFormView view) {
		this.view = view;
	}

	public void onViewLoad(String comboKey) {
		this.editingComboKey = comboKey;
		loadArticles();

		if (comboKey != null && !comboKey.isEmpty()) {
			loadComboForEdit(comboKey);
		} else {
			view.clearForm();
			validateAndUpdateSaveButtonState();
		}
	}

	private void loadArticles() {
		try {
			List<ArticleResponseDTO> articles = articleService.findAll();
			if (articles != null) {
				view.setAvailableArticles(articles);
			}
		} catch (Exception e) {
			view.showError("Greška pri učitavanju artikala: " + e.getMessage());
		}
	}

	private void loadComboForEdit(String comboKey) {
		view.showLoading();
		try {
			ComboResponseDTO combo = comboService.findByKey(comboKey);
			if (combo != null) {
				view.setComboData(combo);
				validateAndUpdateSaveButtonState();
			} else {
				view.showError("Combo nije pronađen");
				view.navigateBackToCombos();
			}
		} catch (Exception e) {
			view.showError("Greška prilikom učitavanja combo-a: " + e.getMessage());
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
			if (editingComboKey == null || editingComboKey.isEmpty()) {
				createCombo();
			} else {
				updateCombo();
			}
			view.navigateBackToCombos();
		} catch (Exception e) {
			view.showError("Greška prilikom čuvanja: " + e.getMessage());
		} finally {
			view.hideLoading();
		}
	}

	private void createCombo() {
		ComboCreateRequestDTO dto = new ComboCreateRequestDTO();
		dto.setName(view.getComboName());
		dto.setDescription(view.getComboDescription());
		dto.setImageUrl(view.getComboImageUrl());
		dto.setBasePrice(view.getComboBasePrice());
		dto.setActive(view.getComboActive());

		String mainKey = view.getSelectedMainArticleKey();
		dto.setMainSelection(mainKey != null ? Collections.singletonList(mainKey) : Collections.emptyList());
		dto.setSideSelection(view.getSelectedSideArticleKeys());
		dto.setDrinkSelection(view.getSelectedDrinkArticleKeys());

		comboService.create(dto);
		view.showSuccess("Combo uspešno kreiran");
	}

	private void updateCombo() {
		ComboUpdateRequestDTO dto = new ComboUpdateRequestDTO();
		dto.setKey(editingComboKey);
		dto.setName(view.getComboName());
		dto.setDescription(view.getComboDescription());
		dto.setImageUrl(view.getComboImageUrl());
		dto.setBasePrice(view.getComboBasePrice());
		dto.setActive(view.getComboActive());

		String mainKey = view.getSelectedMainArticleKey();
		dto.setMainSelection(mainKey != null ? Collections.singletonList(mainKey) : Collections.emptyList());
		dto.setSideSelection(view.getSelectedSideArticleKeys());
		dto.setDrinkSelection(view.getSelectedDrinkArticleKeys());

		comboService.update(editingComboKey, dto);
		view.showSuccess("Combo uspešno ažuriran");
	}

	public void onCancelClick() {
		view.navigateBackToCombos();
	}

	private boolean validateForm() {
		view.clearValidationErrors();
		boolean valid = true;

		String name = view.getComboName();
		if (name == null || name.trim().isEmpty()) {
			view.showNameError("Naziv je obavezan");
			valid = false;
		}

		Double basePrice = view.getComboBasePrice();
		if (basePrice == null || basePrice <= 0) {
			view.showPriceError("Cena mora biti veća od 0");
			valid = false;
		}

		String mainKey = view.getSelectedMainArticleKey();
		if (mainKey == null || mainKey.isEmpty()) {
			view.showMainSelectionError("Potrebno je izabrati glavni artikal");
			valid = false;
		}

		List<String> sideSelection = view.getSelectedSideArticleKeys();
		List<String> drinkSelection = view.getSelectedDrinkArticleKeys();

		boolean hasSides = sideSelection != null && !sideSelection.isEmpty();
		boolean hasDrinks = drinkSelection != null && !drinkSelection.isEmpty();

		if (!hasSides && !hasDrinks) {
			view.showSelectionError("Potrebno je izabrati barem jedan prilog ILI barem jedno piće");
			valid = false;
		}

		return valid;
	}

	public void validateAndUpdateSaveButtonState() {
		view.clearSelectionErrors();
		view.setSaveButtonEnabled(true);
	}
}
