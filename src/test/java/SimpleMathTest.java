import org.junit.Test;
import screen.Calculator;

public class SimpleMathTest extends AbstractTest {

	@Test
	public void AdditionTest() {

		System.out.println(driver.getPageSource());

		new Calculator(driver)
				.press3()
				.pressMultiply()
				.press2()
				.press3()
				.pressEquals()
				.resultShouldEqual("69.0");
	}

}
