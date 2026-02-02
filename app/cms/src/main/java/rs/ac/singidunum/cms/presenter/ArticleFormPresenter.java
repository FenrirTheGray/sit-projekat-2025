package rs.ac.singidunum.cms.presenter;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import rs.ac.singidunum.cms.dto.create.ArticleCreateRequestDTO;
import rs.ac.singidunum.cms.dto.response.ArticleResponseDTO;
import rs.ac.singidunum.cms.dto.update.ArticleUpdateRequestDTO;
import rs.ac.singidunum.cms.service.ArticleService;
import rs.ac.singidunum.cms.view.ArticleFormView;

@SpringComponent
@UIScope
public class ArticleFormPresenter {

	private ArticleFormView view;
	private final ArticleService articleService;
	private String editingArticleKey;

	public ArticleFormPresenter(ArticleService articleService) {
		this.articleService = articleService;
	}

	public void setView(ArticleFormView view) {
		this.view = view;
	}

	public void onViewLoad(String articleKey) {
		this.editingArticleKey = articleKey;
		if (articleKey != null && !articleKey.isEmpty()) {
			loadArticleForEdit(articleKey);
		} else {
			view.clearForm();
		}
	}

	private void loadArticleForEdit(String articleKey) {
		view.showLoading();
		try {
			ArticleResponseDTO article = articleService.findByKey(articleKey);
			if (article != null) {
				view.setArticleData(article);
			} else {
				view.showError("Artikal nije pronađen");
				view.navigateBackToArticles();
			}
		} catch (Exception e) {
			view.showError("Greška prilikom učitavanja artikla: " + e.getMessage());
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
			if (editingArticleKey == null || editingArticleKey.isEmpty()) {
				createArticle();
			} else {
				updateArticle();
			}
			view.navigateBackToArticles();
		} catch (Exception e) {
			view.showError("Greška prilikom čuvanja: " + e.getMessage());
		} finally {
			view.hideLoading();
		}
	}

	private void createArticle() {
		ArticleCreateRequestDTO createDTO = new ArticleCreateRequestDTO();
		createDTO.setName(view.getArticleName());
		createDTO.setDescription(view.getArticleDescription());
		createDTO.setImageUrl(view.getArticleImageUrl());
		createDTO.setBasePrice(view.getArticleBasePrice());
		createDTO.setActive(view.getArticleActive());
		createDTO.setCategoryId(view.getSelectedCategoryId());
		createDTO.setModifiers(java.util.Collections.emptyList());

		articleService.create(createDTO);
		view.showSuccess("Artikal uspešno kreiran");
	}

	private void updateArticle() {
		ArticleUpdateRequestDTO updateDTO = new ArticleUpdateRequestDTO();
		updateDTO.setKey(editingArticleKey);
		updateDTO.setName(view.getArticleName());
		updateDTO.setDescription(view.getArticleDescription());
		updateDTO.setImageUrl(view.getArticleImageUrl());
		updateDTO.setBasePrice(view.getArticleBasePrice());
		updateDTO.setActive(view.getArticleActive());
		updateDTO.setCategoryId(view.getSelectedCategoryId());

		articleService.update(editingArticleKey, updateDTO);
		view.showSuccess("Artikal uspešno ažuriran");
	}

	public void onCancelClick() {
		view.navigateBackToArticles();
	}

	private boolean validateForm() {
		view.clearValidationErrors();
		boolean valid = true;

		String name = view.getArticleName();
		if (name == null || name.trim().isEmpty()) {
			view.showNameError("Naziv je obavezan");
			valid = false;
		}

		String description = view.getArticleDescription();
		if (description == null || description.trim().isEmpty()) {
			view.showDescriptionError("Opis je obavezan");
			valid = false;
		}

		Double basePrice = view.getArticleBasePrice();
		if (basePrice == null || basePrice <= 0) {
			view.showPriceError("Cena mora biti veća od 0");
			valid = false;
		}

		String categoryId = view.getSelectedCategoryId();
		if (categoryId == null || categoryId.isEmpty()) {
			view.showCategoryError("Kategorija je obavezna");
			valid = false;
		}

		return valid;
	}
}
