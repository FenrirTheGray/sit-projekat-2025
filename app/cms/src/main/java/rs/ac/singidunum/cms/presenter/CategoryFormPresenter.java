package rs.ac.singidunum.cms.presenter;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import rs.ac.singidunum.cms.dto.create.CategoryCreateRequestDTO;
import rs.ac.singidunum.cms.dto.response.CategoryResponseDTO;
import rs.ac.singidunum.cms.dto.update.CategoryUpdateRequestDTO;
import rs.ac.singidunum.cms.service.CategoryService;
import rs.ac.singidunum.cms.view.CategoryFormDialog;

@SpringComponent
@UIScope
public class CategoryFormPresenter {

	private CategoryFormDialog view;
	private final CategoryService categoryService;
	private String editingCategoryId;

	public CategoryFormPresenter(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setView(CategoryFormDialog view) {
		this.view = view;
	}

	public void onViewLoad(String categoryId) {
		this.editingCategoryId = categoryId;
		if (categoryId != null && !categoryId.isEmpty()) {
			loadCategoryForEdit(categoryId);
		} else {
			view.clearForm();
		}
	}

	private void loadCategoryForEdit(String categoryId) {
		view.showLoading();
		try {
			CategoryResponseDTO category = categoryService.findById(categoryId);
			if (category != null) {
				view.setCategoryData(category);
			} else {
				view.showError("Kategorija nije pronađena");
				view.navigateBackToCategories();
			}
		} catch (Exception e) {
			view.showError("Greška prilikom učitavanja: " + e.getMessage());
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
			if (editingCategoryId == null) {
				CategoryCreateRequestDTO createDTO = new CategoryCreateRequestDTO();
				createDTO.setName(view.getCategoryName());
				createDTO.setDescription(view.getCategoryDescription());
				createDTO.setActive(view.getCategoryActive());

				categoryService.create(createDTO);
				view.showSuccess("Kategorija uspešno kreirana");
			} else {
				CategoryUpdateRequestDTO updateDTO = new CategoryUpdateRequestDTO();
				updateDTO.setId(editingCategoryId);
				updateDTO.setName(view.getCategoryName());
				updateDTO.setDescription(view.getCategoryDescription());
				updateDTO.setActive(view.getCategoryActive());

				categoryService.update(editingCategoryId, updateDTO);
				view.showSuccess("Kategorija uspešno ažurirana");
			}
			view.navigateBackToCategories();
		} catch (Exception e) {
			view.showError("Greška prilikom čuvanja: " + e.getMessage());
		} finally {
			view.hideLoading();
		}
	}

	public void onCancelClick() {
		view.navigateBackToCategories();
	}

	private boolean validateForm() {
		view.clearValidationErrors();
		boolean valid = true;

		if (view.getCategoryName() == null || view.getCategoryName().trim().isEmpty()) {
			view.showNameError("Naziv je obavezan");
			valid = false;
		}

		if (view.getCategoryDescription() == null || view.getCategoryDescription().trim().isEmpty()) {
			view.showDescriptionError("Opis je obavezan");
			valid = false;
		}

		return valid;
	}
}
