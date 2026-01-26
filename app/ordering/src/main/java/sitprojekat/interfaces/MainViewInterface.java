package sitprojekat.interfaces;

import java.util.List;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import sitprojekat.presenter.MainPresenter;

public interface MainViewInterface {

	public int getCurrentIndexInt();
	public void setCurrentIndexInt(int currentIndexInt);
	public List<String> getImagesList();
	public void setImagesList(List<String> imagesList);
	public void setPresenter(MainPresenter presenter);
	public HorizontalLayout getCarouselDotsContainer();
	public void setCarouselDotsContainer(HorizontalLayout carouselDotsContainer);
	public Image getPromoImage();
	public void setPromoImage(Image promoImage);
	
}
