package mockito;

public class Business {
	
	
	private Calculator calculator;

	/**
	 * DI setter
	 * @param calculator
	 */
	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}

	public Integer som(int i, int j) {
		calculator.setA(i);
		calculator.setB(j);
		
		return calculator.sum();
	}

	public Integer product(int i, int j) {
		calculator.setA(i);
		calculator.setB(j);
		
		return calculator.product();
	}

}
