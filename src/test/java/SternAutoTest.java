
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import com.codeborne.selenide.junit.TextReport;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.containers.BrowserWebDriverContainer;

public class SternAutoTest {

  @Rule
  public BrowserWebDriverContainer chrome =
      new BrowserWebDriverContainer(Abi.chromeImage())
          .withCapabilities(new ChromeOptions());

  @Rule
  public TextReport textReport = new TextReport();

  @Before
  public void setUp() {
    Configuration.timeout = 10000;
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
