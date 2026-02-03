package rs.ac.singidunum.cms.interfaces;

import rs.ac.singidunum.cms.dto.response.CategoryResponseDTO;

public interface CategoryFormViewInterface {
	void setCategoryData(CategoryResponseDTO category);
	void clearForm();

	String getCategoryName();
	String getCategoryDescription();
	Boolean getCategoryActive();

	void showLoading();
	void hideLoading();
	void showError(String message);
	void showSuccess(String message);

	void showNameError(String message);
	void showDescriptionError(String message);
	void clearValidationErrors();

	void navigateBackToCategories();
}
