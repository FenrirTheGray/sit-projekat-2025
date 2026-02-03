package sitprojekat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.vaadin.flow.component.UI;

import sitprojekat.dto.ArticleChoiceCreateRequestDTO;
import sitprojekat.dto.ChoiceCreateRequestDTO;
import sitprojekat.dto.ComboChoiceCreateRequestDTO;
import sitprojekat.dto.OrderCreateRequestDTO;
import sitprojekat.dto.OrderResponseDTO;
import sitprojekat.model.Article;
import sitprojekat.model.Modifier;
import sitprojekat.model.Order;
import sitprojekat.model.OrderStatus;
import sitprojekat.model.ProductInCart;
import sitprojekat.model.ProductInCartArticle;
import sitprojekat.model.ProductInCartCombo;
import sitprojekat.model.UserAccount;

@Service
public class OrderService {

	@Autowired
	private HttpService httpService;

	@Autowired
	private UserAccountService userAccountService;

	@Autowired
	private UserStoreService userStoreService;

	private final String controllerPath = "/orders";

	public List<Order> getOrders(String id) {
		return httpService.get(httpService.API_BASE_URL + controllerPath, new ParameterizedTypeReference<>() {
		});
	}

	public Order findByID(String id) {
		return httpService.get(httpService.API_BASE_URL + controllerPath + "/" + id, Order.class);
	}

	public OrderResponseDTO createOrders(List<ProductInCart> productInCart, String address, String telephone,
			String paymentType, double totalPrice) {
		try {

			UserAccount user = userAccountService.getUser();
			String userToken = userStoreService.getToken();
			List<ChoiceCreateRequestDTO> choices=new ArrayList<ChoiceCreateRequestDTO>();
			
			for (ProductInCart product : productInCart) {

				if (product instanceof ProductInCartArticle) {

					ProductInCartArticle productInCartArticle = (ProductInCartArticle) product;

					ArticleChoiceCreateRequestDTO articleChoiceCreateRequestDTO = new ArticleChoiceCreateRequestDTO();
					articleChoiceCreateRequestDTO.setArticle("article/"+productInCartArticle.getProduct().getId());
					articleChoiceCreateRequestDTO.setAmount(productInCartArticle.getNumberOrdered());

					List<String> modifiersList = new ArrayList<String>();
					for (Modifier m : productInCartArticle.getModifierToppings()) {
						modifiersList.add("modifier/"+m.getId());
					}

					articleChoiceCreateRequestDTO.setModifierList(modifiersList);
					articleChoiceCreateRequestDTO.setClassName("articleDTO");
					choices.add(articleChoiceCreateRequestDTO);
				} else {

					ProductInCartCombo productInCartCombo = (ProductInCartCombo) product;

					ComboChoiceCreateRequestDTO comboChoiceCreateRequestDTO = new ComboChoiceCreateRequestDTO();
					comboChoiceCreateRequestDTO.setCombo("combo/"+productInCartCombo.getProduct().getId());
					System.out.println("combo id je "+comboChoiceCreateRequestDTO.getCombo());		
					comboChoiceCreateRequestDTO.setAmount(productInCartCombo.getNumberOrdered());
					System.out.println("combo narucena kolicina je "+comboChoiceCreateRequestDTO.getAmount());
					
					ProductInCart main=(ProductInCart) productInCartCombo.getMain();
					ProductInCart side=(ProductInCart) productInCartCombo.getSide();
					ProductInCart drink=(ProductInCart) productInCartCombo.getDrink();

					List<ArticleChoiceCreateRequestDTO> ArticleChoiceCreateRequestDTOList = new ArrayList<ArticleChoiceCreateRequestDTO>();
					
					ArticleChoiceCreateRequestDTO articleChoiceCreateRequestDTOMain = new ArticleChoiceCreateRequestDTO();				
					articleChoiceCreateRequestDTOMain.setArticle("article/"+main.getProduct().getId());
					System.out.println("main id je "+articleChoiceCreateRequestDTOMain.getArticle());
					articleChoiceCreateRequestDTOMain.setClassName("articleDTO");
					
					List<String> modifiersListMain = new ArrayList<String>();
					for (Modifier m : main.getModifierToppings()) {
						System.out.println("main modofier je "+m.getId());
						modifiersListMain.add("modifier/"+m.getId());
					}
					articleChoiceCreateRequestDTOMain.setModifierList(modifiersListMain);
					articleChoiceCreateRequestDTOMain.setAmount(main.getNumberOrdered());
					
					ArticleChoiceCreateRequestDTOList.add(articleChoiceCreateRequestDTOMain);
					
					ArticleChoiceCreateRequestDTO articleChoiceCreateRequestDTOSide = new ArticleChoiceCreateRequestDTO();					
					articleChoiceCreateRequestDTOSide.setArticle("article/"+side.getProduct().getId());
					System.out.println("side id je "+articleChoiceCreateRequestDTOSide.getArticle());
					articleChoiceCreateRequestDTOSide.setClassName("articleDTO");
					
					List<String> modifiersListSide = new ArrayList<String>();
					for (Modifier m : side.getModifierToppings()) {
						modifiersListSide.add("modifier/"+m.getId());
					}
					
					articleChoiceCreateRequestDTOSide.setModifierList(modifiersListSide);
					articleChoiceCreateRequestDTOSide.setAmount(1);
					ArticleChoiceCreateRequestDTOList.add(articleChoiceCreateRequestDTOSide);
					
					ArticleChoiceCreateRequestDTO articleChoiceCreateRequestDTODrink = new ArticleChoiceCreateRequestDTO();
					articleChoiceCreateRequestDTODrink.setArticle("article/"+drink.getProduct().getId());
					System.out.println("drink id je "+articleChoiceCreateRequestDTODrink.getArticle());
					articleChoiceCreateRequestDTODrink.setClassName("articleDTO");
					
					List<String> modifiersListDrink = new ArrayList<String>();
					for (Modifier m : drink.getModifierToppings()) {
						modifiersListDrink.add("modifier/"+m.getId());
					}
					
					articleChoiceCreateRequestDTODrink.setModifierList(modifiersListDrink);
					articleChoiceCreateRequestDTODrink.setAmount(1);
					ArticleChoiceCreateRequestDTOList.add(articleChoiceCreateRequestDTODrink);
					
					comboChoiceCreateRequestDTO.setArticleChoiceList(ArticleChoiceCreateRequestDTOList);
					comboChoiceCreateRequestDTO.setClassName("comboDTO");
					
					choices.add(comboChoiceCreateRequestDTO);
					
				}

			}

			user.setAdress(address);
			user.setPhone(telephone);

			System.out.println("token " + userToken + "  adresa " + user.getAdress() + " email " + user.getEmail()
					+ " telefon " + user.getPhone());

			OrderCreateRequestDTO orderCreateRequestDTO = new OrderCreateRequestDTO();
			orderCreateRequestDTO.setStatus(OrderStatus.CREATED);
			orderCreateRequestDTO.setChoices(choices);
			
			
			return httpService.post(httpService.API_BASE_URL + controllerPath, orderCreateRequestDTO, OrderResponseDTO.class, true);
		} catch (Exception e) {
			e.printStackTrace();
			UI.getCurrent().navigate("OrderDenied");
			return null;
		}

	}

}
