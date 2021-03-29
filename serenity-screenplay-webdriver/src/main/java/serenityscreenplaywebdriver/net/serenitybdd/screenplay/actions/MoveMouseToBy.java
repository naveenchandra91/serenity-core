package serenityscreenplaywebdriver.net.serenitybdd.screenplay.actions;

import serenitymodel.net.serenitybdd.core.collect.NewList;
import serenityscreenplay.net.serenitybdd.screenplay.Actor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MoveMouseToBy extends MoveMouseTo {
    private final List<By> locators;

   public MoveMouseToBy(By... locators) {
       this.locators = NewList.copyOf(locators);
   }

   public <T extends Actor> void performAs(T actor) {
       WebElement element = WebElementLocator.forLocators(locators).andActor(actor);
       performMouseMoveAs(actor, element);
   }
}