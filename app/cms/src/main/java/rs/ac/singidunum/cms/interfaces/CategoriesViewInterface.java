package rs.ac.singidunum.cms.interfaces;

import rs.ac.singidunum.cms.dto.response.CategoryResponseDTO;
import java.util.List;

public interface CategoriesViewInterface {
	void setCategories(List<CategoryResponseDTO> categories);
	void clearCategories();
	String getSearchText();

	void showLoading();
	void hideLoading();
	void showError(String message);
	void showSuccess(String message);
	void showEmptyState(String message);

	void openCategoryEditDialog(String categoryId);
	void openCategoryCreateDialog();

	void showDeleteConfirmation(String categoryId, String categoryName);
}
