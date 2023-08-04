import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SternAutoPage {

  private final SelenideElement constent_button = $("#acceptCookies");
  private final SelenideElement customer_type_private = $("label[for='QB[customer_type]-private']");
  private final SelenideElement customer_type_business = $(
      "label[for='QB[customer_type]-business']");
  private final SelenideElement plus3 = $("label[for='QB[calculation_type]-balloon']");
  private final SelenideElement leasing = $("label[for='QB[calculation_type]-leasing']");
  private final SelenideElement financing = $(
      "label[for='QB[calculation_type]-financing']");
  private final SelenideElement price = $("div.col-lg-6.resultValue");
  private final SelenideElement plus3LoadLabel = $(byText("Mtl. Plus3-Finanzierungsrate"));
  private final SelenideElement financingLoadLabel = $(byText("Mtl. Finanzierungsrate"));
  private final SelenideElement leasingLoadLabel_private = $(byText("Mtl. Leasingrate"));
  // should normally not be neccessary because byText = contains and it should work there too
  private final SelenideElement leasingLoadLabel_business = $(
      byText("Mtl. Leasingrate (exkl. MwSt.)"));


  private void check_price_exists(SelenideElement element) {
    element.shouldHave(Condition.partialText("€"));
  }

  public void openStern(String link) {
    open(link);
    constent_button.shouldBe(visible).click();
  }

  public void scrollToCalculator() {
    price.shouldBe(visible);
    price.scrollIntoView("{behavior: \"instant\", block: \"center\", inline: \"center\"}");
  }

  private void switchToPrivate() {
    customer_type_private.shouldBe(visible).click();
    check_price_exists(price);
  }

  private void switchToBusiness() {
    customer_type_business.shouldBe(visible).click();
    check_price_exists(price);
  }

  public void iteratePrivateCalculator() {
    switchToPrivate();
    plus3.shouldBe(visible).click();
    plus3LoadLabel.shouldBe(visible);
    check_price_exists(price);

    financing.shouldBe(visible).click();
    financingLoadLabel.shouldBe(visible);
    check_price_exists(price);

    leasing.shouldBe(visible).click();
    leasingLoadLabel_private.shouldBe(visible);
    check_price_exists(price);
  }

  public void iterateBusinessCalculator() {
    switchToBusiness();
    plus3.shouldBe(visible).click();
    plus3LoadLabel.shouldBe(visible);
    check_price_exists(price);

    financing.shouldBe(visible).click();
    financingLoadLabel.shouldBe(visible);
    check_price_exists(price);

    leasing.shouldBe(visible).click();
    leasingLoadLabel_business.shouldBe(visible);
    check_price_exists(price);
  }


}
