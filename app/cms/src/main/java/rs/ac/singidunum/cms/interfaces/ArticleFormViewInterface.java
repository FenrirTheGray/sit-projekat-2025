package rs.ac.singidunum.cms.interfaces;

import rs.ac.singidunum.cms.dto.response.ArticleResponseDTO;

public interface ArticleFormViewInterface {

	void setArticleData(ArticleResponseDTO article);

	void clearForm();

	String getArticleName();

	String getArticleDescription();

	String getArticleImageUrl();

	Double getArticleBasePrice();

	Boolean getArticleActive();

	String getSelectedCategoryId();

	void showLoading();

	void hideLoading();

	void showError(String message);

	void showSuccess(String message);

	void showNameError(String message);

	void showDescriptionError(String message);

	void showPriceError(String message);

	void showCategoryError(String message);

	void clearValidationErrors();

	void navigateBackToArticles();
}
