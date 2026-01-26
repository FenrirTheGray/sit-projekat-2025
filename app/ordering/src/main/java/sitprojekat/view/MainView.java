package sitprojekat.view;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import sitprojekat.interfaces.MainViewInterface;
import sitprojekat.presenter.MainPresenter;

@Route(value = "Main",layout = HeaderAndNavBar.class)
public class MainView extends VerticalLayout implements MainViewInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6313156717813295316L;

	private int currentIndexInt = 0;
    private List<String> imagesList=new ArrayList<String>();
    private TextField filterTextBox=new TextField();
    private final MainPresenter presenter;
    private HorizontalLayout carouselDotsContainer = new HorizontalLayout();
    private Image promoImage;
    
	public MainView(MainPresenter presenter) {
		this.presenter=presenter;
		presenter.setView(this);
		
		imagesList.add("/images/image_burger.jpg");
		imagesList.add("/images/image_pizza.jpg");
		promoImage=new Image(imagesList.get(currentIndexInt),"slika primer proizvoda");
		
			
		filterTextBox.setPlaceholder("pretraga");
		filterTextBox.setClearButtonVisible(true);
		filterTextBox.setSuffixComponent(VaadinIcon.SEARCH.create());
		filterTextBox.addClassName("filterTextBox");
		
		promoImage.addClassName("promoImage");
		
		H2 titleH2=new H2();
		titleH2.setText("Otkrite nase proizvode");
		titleH2.addClassName("whiteText");
			
		Icon leftArrowIcon=VaadinIcon.ARROW_LEFT.create();
		leftArrowIcon.addClassName("arrowIcon");
		
		leftArrowIcon.addClickListener(e -> {
			presenter.leftArrow();
        });
		
		
		Icon rightArrowIcon=VaadinIcon.ARROW_RIGHT.create();
		rightArrowIcon.addClassName("arrowIcon");
		
		rightArrowIcon.addClickListener(e -> {
			presenter.rightArrow();
        });
		
		
		HorizontalLayout carouselContainer=new HorizontalLayout();
		carouselContainer.add(leftArrowIcon,carouselDotsContainer,rightArrowIcon);
		
		
		VerticalLayout mainContentContainer=new VerticalLayout();
		mainContentContainer.add(filterTextBox,titleH2,promoImage,carouselContainer);
		mainContentContainer.setWidthFull();
		mainContentContainer.setJustifyContentMode(JustifyContentMode.CENTER);
		mainContentContainer.setAlignItems(Alignment.CENTER);
		
		presenter.changeCarouselDots();
		
		
		
		add(mainContentContainer);
	}

	@Override
	public int getCurrentIndexInt() {
		return currentIndexInt;
	}

	@Override
	public void setCurrentIndexInt(int currentIndexInt) {
		this.currentIndexInt=currentIndexInt;
		
	}

	@Override
	public List<String> getImagesList() {
		return imagesList;
	}

	@Override
	public void setImagesList(List<String> imagesList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPresenter(MainPresenter presenter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HorizontalLayout getCarouselDotsContainer() {
		return carouselDotsContainer;
	}

	@Override
	public void setCarouselDotsContainer(HorizontalLayout carouselDotsContainer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image getPromoImage() {
		return promoImage;
	}

	@Override
	public void setPromoImage(Image promoImage) {
		this.promoImage=promoImage;
		
	}

	
	


	
}
