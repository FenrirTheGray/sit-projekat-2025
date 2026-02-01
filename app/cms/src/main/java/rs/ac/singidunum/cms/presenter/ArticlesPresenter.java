package rs.ac.singidunum.cms.presenter;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import rs.ac.singidunum.cms.dto.response.ArticleResponseDTO;
import rs.ac.singidunum.cms.interfaces.ArticlesViewInterface;
import rs.ac.singidunum.cms.service.ArticleService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringComponent
@UIScope
public class ArticlesPresenter {

	private ArticlesViewInterface view;
	private final ArticleService articleService;
	private List<ArticleResponseDTO> allArticles = new ArrayList<>();

	public ArticlesPresenter(ArticleService articleService) {
		this.articleService = articleService;
	}

	public void setView(ArticlesViewInterface view) {
		this.view = view;
	}

	public void onViewLoad() {
		loadArticles();
	}

	public void loadArticles() {
		view.showLoading();
		try {
			allArticles = articleService.findAll();
			if (allArticles == null || allArticles.isEmpty()) {
				allArticles = new ArrayList<>();
				view.showEmptyState("Nema artikala za prikaz");
			} else {
				view.setArticles(allArticles);
			}
		} catch (Exception e) {
			view.showError("Greška prilikom učitavanja: " + e.getMessage());
		} finally {
			view.hideLoading();
		}
	}

	public void onSearchChange(String searchText) {
		if (allArticles == null || allArticles.isEmpty()) {
			view.showEmptyState("Nema artikala za prikaz");
			return;
		}

		if (searchText == null || searchText.isEmpty()) {
			view.setArticles(allArticles);
			return;
		}

		String searchLower = searchText.toLowerCase();
		List<ArticleResponseDTO> filtered = allArticles.stream()
				.filter(a -> a.getName().toLowerCase().contains(searchLower)
						|| (a.getDescription() != null && a.getDescription().toLowerCase().contains(searchLower)))
				.collect(Collectors.toList());

		if (filtered.isEmpty()) {
			view.showEmptyState("Nema rezultata za: " + searchText);
		} else {
			view.setArticles(filtered);
		}
	}

	public void onAddArticleClick() {
		view.navigateToArticleCreate();
	}

	public void onEditArticleClick(String articleKey) {
		view.navigateToArticleEdit(articleKey);
	}

	public void onDeleteArticleClick(String articleKey, String articleName) {
		view.showDeleteConfirmation(articleKey, articleName);
	}

	public void confirmDeleteArticle(String articleKey) {
		view.showLoading();
		try {
			articleService.deleteByKey(articleKey);
			view.showSuccess("Artikal uspešno obrisan");
			loadArticles();
		} catch (Exception e) {
			view.showError("Greška prilikom brisanja: " + e.getMessage());
		} finally {
			view.hideLoading();
		}
	}
}
