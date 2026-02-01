package rs.ac.singidunum.cms.interfaces;

import rs.ac.singidunum.cms.dto.response.ArticleResponseDTO;

import java.util.List;

public interface ArticlesViewInterface {

	void setArticles(List<ArticleResponseDTO> articles);

	void clearArticles();

	String getSearchText();

	void showLoading();

	void hideLoading();

	void showError(String message);

	void showSuccess(String message);

	void showEmptyState(String message);

	void navigateToArticleEdit(String articleKey);

	void navigateToArticleCreate();

	void showDeleteConfirmation(String articleKey, String articleName);
}
