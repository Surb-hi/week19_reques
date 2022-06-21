package reqres.cucumber;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;
import reqres.testbase.TestBase;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/java/resources/feature")
public class CucumberRunner extends TestBase {


}
