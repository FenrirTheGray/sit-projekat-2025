package rs.ac.singidunum.cms.presenter;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import rs.ac.singidunum.cms.dto.response.CategoryResponseDTO;
import rs.ac.singidunum.cms.interfaces.CategoriesViewInterface;
import rs.ac.singidunum.cms.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@SpringComponent
@UIScope
public class CategoriesPresenter {

	private CategoriesViewInterface view;
	private final CategoryService categoryService;
	private List<CategoryResponseDTO> allCategories;

	public CategoriesPresenter(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setView(CategoriesViewInterface view) {
		this.view = view;
	}

	public void onViewLoad() {
		loadCategories();
	}

	public void loadCategories() {
		view.showLoading();
		try {
			allCategories = categoryService.findAll();
			if (allCategories == null || allCategories.isEmpty()) {
				view.showEmptyState("Nema kategorija za prikaz");
			} else {
				view.setCategories(allCategories);
			}
		} catch (Exception e) {
			view.showError("Greška prilikom učitavanja: " + e.getMessage());
		} finally {
			view.hideLoading();
		}
	}

	public void onSearchChange(String searchText) {
		if (searchText == null || searchText.isEmpty()) {
			view.setCategories(allCategories);
			return;
		}

		List<CategoryResponseDTO> filtered = allCategories.stream()
				.filter(c -> c.getName().toLowerCase().contains(searchText.toLowerCase())
						|| c.getDescription().toLowerCase().contains(searchText.toLowerCase()))
				.collect(Collectors.toList());

		if (filtered.isEmpty()) {
			view.showEmptyState("Nema rezultata za: " + searchText);
		} else {
			view.setCategories(filtered);
		}
	}

	public void onAddCategoryClick() {
		view.openCategoryCreateDialog();
	}

	public void onEditCategoryClick(String categoryId) {
		view.openCategoryEditDialog(categoryId);
	}

	public void onDeleteCategoryClick(String categoryId, String categoryName) {
		view.showDeleteConfirmation(categoryId, categoryName);
	}

	public void confirmDeleteCategory(String categoryId) {
		try {
			categoryService.deleteById(categoryId);
			view.showSuccess("Kategorija uspešno obrisana");
			loadCategories();
		} catch (Exception e) {
			view.showError("Greška prilikom brisanja: " + e.getMessage());
		}
	}
}
