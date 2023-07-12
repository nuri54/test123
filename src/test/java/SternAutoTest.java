import static com.codeborne.selenide.Selenide.clearBrowserCookies;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.utility.DockerImageName;


public class SternAutoTest {
  @Rule
  public BrowserWebDriverContainer chrome =
      new BrowserWebDriverContainer(Abi.chromeImage())
          .withCapabilities(new ChromeOptions());

  @Before
  public void setUp() {
    RemoteWebDriver driver = chrome.getWebDriver();
    WebDriverRunner.setWebDriver(driver);
  }

  @After
  public void tearDown() {
    WebDriverRunner.closeWebDriver();
  }

  SternAutoPage sternAutoPage = new SternAutoPage();


  @Test
  public void sternAutoTest() {
    sternAutoPage.openStern("https://sternauto-gruppe.de/fahrzeugdetails?carId=0124150456");
    sternAutoPage.scrollToCalculator();
    sternAutoPage.iteratePrivateCalculator();
    sternAutoPage.iterateBusinessCalculator();
  }
}