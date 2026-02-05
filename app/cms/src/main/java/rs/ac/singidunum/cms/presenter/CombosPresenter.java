package rs.ac.singidunum.cms.presenter;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import rs.ac.singidunum.cms.dto.response.ArticleResponseDTO;
import rs.ac.singidunum.cms.dto.response.ComboResponseDTO;
import rs.ac.singidunum.cms.service.ArticleService;
import rs.ac.singidunum.cms.service.ComboService;
import rs.ac.singidunum.cms.view.ComboView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringComponent
@UIScope
public class CombosPresenter {

	private ComboView view;
	private final ComboService comboService;
	private final ArticleService articleService;
	private List<ComboResponseDTO> allCombos = new ArrayList<>();

	public CombosPresenter(ComboService comboService, ArticleService articleService) {
		this.comboService = comboService;
		this.articleService = articleService;
	}

	public void setView(ComboView view) {
		this.view = view;
	}

	public void onViewLoad() {
		loadCombos();
	}

	public void loadCombos() {
		view.showLoading();
		try {
			allCombos = comboService.findAll();
			if (allCombos == null || allCombos.isEmpty()) {
				allCombos = new ArrayList<>();
				view.showEmptyState("Nema combo-a za prikaz");
			} else {
				view.setCombos(allCombos);
			}
		} catch (Exception e) {
			view.showError("Greška prilikom učitavanja: " + e.getMessage());
		} finally {
			view.hideLoading();
		}
	}

	public void onSearchChange(String searchText) {
		if (allCombos == null || allCombos.isEmpty()) {
			view.showEmptyState("Nema combo-a za prikaz");
			return;
		}

		if (searchText == null || searchText.isEmpty()) {
			view.setCombos(allCombos);
			return;
		}

		String searchLower = searchText.toLowerCase();
		List<ComboResponseDTO> filtered = allCombos.stream()
				.filter(c -> c.getName().toLowerCase().contains(searchLower))
				.collect(Collectors.toList());

		if (filtered.isEmpty()) {
			view.showEmptyState("Nema rezultata za: " + searchText);
		} else {
			view.setCombos(filtered);
		}
	}

	public void onAddComboClick() {
		if (!canCreateCombo()) {
			view.showError("Ne možete kreirati combo. Potrebni su artikli za main izbor i barem jedan side ili drink.");
			return;
		}
		view.navigateToComboCreate();
	}

	private boolean canCreateCombo() {
		try {
			List<ArticleResponseDTO> articles = articleService.findAll();
			if (articles == null || articles.isEmpty()) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void onEditComboClick(String comboKey) {
		view.navigateToComboEdit(comboKey);
	}

	public void onDeleteComboClick(String comboKey, String comboName) {
		view.showDeleteConfirmation(comboKey, comboName);
	}

	public void confirmDeleteCombo(String comboKey) {
		view.showLoading();
		try {
			comboService.deleteByKey(comboKey);
			view.showSuccess("Combo uspešno obrisan");
			loadCombos();
		} catch (Exception e) {
			view.showError("Greška prilikom brisanja: " + e.getMessage());
		} finally {
			view.hideLoading();
		}
	}
}
