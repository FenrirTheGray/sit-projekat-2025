package sitprojekat.interfaces;

import java.util.List;
import java.util.Set;

import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.data.renderer.ComponentRenderer;

import sitprojekat.model.Article;
import sitprojekat.model.Modifier;

public interface ComboViewInterface {

	public void setComboName(String string);
	public void setComboDescription(String string);
	public Integer getProductCounter();
	
	public RadioButtonGroup<Article> getMainRadioButtonGroup();
	public void setMainRadioButtonGroup(RadioButtonGroup<Article> mainRadioButtonGroup);
	public CheckboxGroup<Modifier> getMainModifierCheckBoxGroup();
	public void setMainModifierCheckBoxGroup(CheckboxGroup<Modifier> mainModifierCheckBoxGroup);
	public RadioButtonGroup<Article> getSideRadioButtonGroup();
	public void setSideRadioButtonGroup(RadioButtonGroup<Article> sideRadioButtonGroup);
	public CheckboxGroup<Modifier> getSideModifierCheckBoxGroup();
	public void setSideModifierCheckBoxGroup(CheckboxGroup<Modifier> sideModifierCheckBoxGroup);
	public RadioButtonGroup<Article> getDrinkRadioButtonGroup();
	public void setDrinkRadioButtonGroup(RadioButtonGroup<Article> drinkRadioButtonGroup);
	public CheckboxGroup<Modifier> getDrinkModifierCheckBoxGroup();
	public void setDrinkModifierCheckBoxGroup(CheckboxGroup<Modifier> drinkModifierCheckBoxGroup);
	public VerticalLayout getComboChoiceContainer();
	public void setComboChoiceContainer(VerticalLayout comboChoiceContainer);
	public VerticalLayout getComboModificationsContainer();
	public void setComboModificationsContainer(VerticalLayout comboModificationsContainer);
	public void setPrice(double basePrice);
	
	public List<Article> getSelectedArticles();
	public Set<Modifier> getSelectedModifiers();
	
	public Article getMainArticle();
	public Set<Modifier> getMainModifiers();
	
	public Article getSideArticle();
	public Set<Modifier> getSideModifiers();
	
	public Article getDrinkArticle();
	public Set<Modifier> getDrinkModifiers();
	
	public RadioButtonGroup<Article> createComboRadioButtons(String label,List<Article> articles);
	public CheckboxGroup<Modifier> createComboCheckBox(String label,List<Modifier> modifiers);
	public ComponentRenderer<VerticalLayout, Article> createArticleRenderer();
	public ComponentRenderer<VerticalLayout, Modifier> createModifierRenderer();
	public  void AddToCartNotif(String string);
	

	

}
