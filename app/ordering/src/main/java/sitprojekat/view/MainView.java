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

@Route(value = "Main",layout = HeaderAndNavBar.class)
public class MainView extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6313156717813295316L;

	private int currentIndexInt = 0;
    private List<String> imagesList=new ArrayList<String>();
    private HorizontalLayout carouselDotsContainer = new HorizontalLayout();
	
	public MainView() {
		
		
		imagesList.add("/images/image_burger.jpg");
		imagesList.add("/images/image_pizza.jpg");
		
		TextField filterTextBox=new TextField();
			
		filterTextBox.setValue("pretraga");
		filterTextBox.setClearButtonVisible(true);
		filterTextBox.setSuffixComponent(VaadinIcon.SEARCH.create());
		filterTextBox.addClassName("filterTextBox");
		
		Image promoImage=new Image(imagesList.get(currentIndexInt),"slika primer proizvoda");
		promoImage.addClassName("promoImage");
		
		H2 titleH2=new H2();
		titleH2.setText("Otkrite nase proizvode");
		titleH2.addClassName("whiteText");
			
		Icon leftArrowIcon=VaadinIcon.ARROW_LEFT.create();
		leftArrowIcon.addClassName("arrowIcon");
		
		leftArrowIcon.addClickListener(e -> {
			currentIndexInt-=1;
			if((currentIndexInt)<0) {
				currentIndexInt=imagesList.size()-1;
			}
            promoImage.setSrc(imagesList.get(currentIndexInt));
            changeCarouselDots();
        });
		
		
		Icon rightArrowIcon=VaadinIcon.ARROW_RIGHT.create();
		rightArrowIcon.addClassName("arrowIcon");
		
		rightArrowIcon.addClickListener(e -> {
			currentIndexInt+=1;
			if((currentIndexInt)>=imagesList.size()) {
				currentIndexInt=0;
			}
           promoImage.setSrc(imagesList.get(currentIndexInt));
           changeCarouselDots();
        });
		
		
		HorizontalLayout carouselContainer=new HorizontalLayout();
		carouselContainer.add(leftArrowIcon,carouselDotsContainer,rightArrowIcon);
		
		
		VerticalLayout mainContentContainer=new VerticalLayout();
		mainContentContainer.add(filterTextBox,titleH2,promoImage,carouselContainer);
		mainContentContainer.setWidthFull();
		mainContentContainer.setJustifyContentMode(JustifyContentMode.CENTER);
		mainContentContainer.setAlignItems(Alignment.CENTER);
		
		changeCarouselDots();
		
		
		
		add(mainContentContainer);
	}
	private void changeCarouselDots() {
        carouselDotsContainer.removeAll();
        for (int i = 0; i < imagesList.size(); i++) {
            Icon carouselDotIcon = VaadinIcon.CIRCLE.create();
            if(i==currentIndexInt) {
            	 carouselDotIcon.addClassName("activeCarousel");// drugacije boje stavlja selektovanu
            }
            else {
            carouselDotIcon.addClassName("notActiveCarousel");
            }
            carouselDotsContainer.add(carouselDotIcon);
            carouselDotsContainer.setAlignItems(Alignment.CENTER);
            carouselDotsContainer.setJustifyContentMode(JustifyContentMode.CENTER);
        }
    }

	
}
