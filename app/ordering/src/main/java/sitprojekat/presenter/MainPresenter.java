package sitprojekat.presenter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;

import sitprojekat.interfaces.MainViewInterface;
import sitprojekat.model.Article;
import sitprojekat.model.Combo;
import sitprojekat.service.ArticleService;
import sitprojekat.service.ComboService;
import sitprojekat.view.ArticleView;
import sitprojekat.view.ComboView;

@Component
public class MainPresenter {

	private MainViewInterface view;
	private ArticleService articleService;
	private ComboService comboService;
	
	public MainPresenter(ArticleService articleService, ComboService comboService){	
		this.articleService=articleService;
		this.comboService=comboService;
	}
		
	public void leftArrow() {
		int currentIndexInt=view.getCurrentIndexInt();
		currentIndexInt-=1;
		
		if((currentIndexInt)<0) {
			currentIndexInt=view.getImagesList().size()-1;
		}
		view.setCurrentIndexInt(currentIndexInt);
		view.getPromoImage().setSrc(view.getImagesList().get(currentIndexInt));
	    changeCarouselDots();
	}

	public void rightArrow() {
		int currentIndexInt=view.getCurrentIndexInt();
		currentIndexInt+=1;
		if((currentIndexInt)>=view.getImagesList().size()) {
			currentIndexInt=0;
		}
		view.setCurrentIndexInt(currentIndexInt);
	    view.getPromoImage().setSrc(view.getImagesList().get(currentIndexInt));
	    changeCarouselDots();
	}

	public void changeCarouselDots() {
	   view.getCarouselDotsContainer().removeAll();
	    for (int i = 0; i < view.getImagesList().size(); i++) {
	        Icon carouselDotIcon = VaadinIcon.CIRCLE.create();
	        if(i==view.getCurrentIndexInt()) {
	        	 carouselDotIcon.addClassName("activeCarousel");// drugacije boje stavlja selektovanu
	        }
	        else {
	        carouselDotIcon.addClassName("notActiveCarousel");
	        }
	        view.getCarouselDotsContainer().add(carouselDotIcon);
	        view.getCarouselDotsContainer().setAlignItems(Alignment.CENTER);
	        view.getCarouselDotsContainer().setJustifyContentMode(JustifyContentMode.CENTER);
	    }
	}

	public void setView(MainViewInterface view) {
		this.view=view;
	}

	public void findProduct(String id) {
		List<Article> articleList=articleService.getArticles();
		List<Combo> comboList=comboService.getCombos();
		
		for (Article a: articleList) {
			if(a.getId().equals(id)) {
				UI.getCurrent().navigate(ArticleView.class, id);
			}
		}
		
		
		for (Combo c: comboList) {
			if(c.getId().equals(id)) {
				UI.getCurrent().navigate(ComboView.class, id);
			}
		}
		
	}
}
	
