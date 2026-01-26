package sitprojekat.presenter;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;

import sitprojekat.interfajsi.MainViewInterface;

@Component
public class MainPresenter {

	private MainViewInterface view;
	
	public MainPresenter(){	
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
}
	
