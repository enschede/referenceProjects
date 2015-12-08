package mockitotest;

import static org.mockito.Mockito.*;
import mockito.Business;
import mockito.Calculator;
import mockito.NoFourException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ApplicationTest {

	Calculator calculator;
	Business business;

	// Draait voor iedere individuele testclass
	@Before
	public void setup() {
		business = new Business();
	}

	@Test
	public void testMock() throws NoFourException {
		calculator = mock(Calculator.class);
		business.setCalculator(calculator);

		when(calculator.sum()).thenReturn(9).thenReturn(10);
		when(calculator.product()).thenReturn(20);

		// Deze werken ook gewoon
		// doThrow(NoFourException.class).when(calculator).setA(anyInt());
		// doThrow(NoFourException.class).when(calculator).getA();
		// when(calculator.getA()).thenThrow(NoFourException.class);

		int result = business.som(4, 5);
		Assert.assertEquals(9, result);

		result = business.som(4, 5);
		Assert.assertEquals(10, result);

		Assert.assertEquals(20L, business.product(4, 5).longValue());

		verify(calculator, atLeastOnce()).sum();
		verify(calculator, times(2)).sum();

		// Deze kan niet omdat setA(4) geen returntype (void) heeft
		// En dat is ook logisch, een mock doet niets met setters
		// verify(calculator, never()).setA(4);
	}

	@Test
	public void testSpy() {
		calculator = spy(new Calculator());
		business = new Business();
		business.setCalculator(calculator);

		Assert.assertEquals(20L, business.product(4, 5).longValue());

		verify(calculator, never()).sum();
		verify(calculator, times(1)).setA(4);
	}

}
