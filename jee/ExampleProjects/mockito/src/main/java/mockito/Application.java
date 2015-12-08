package mockito;

public class Application {

	public static void main(String[] args) {
		Business business = new Business();
		business.setCalculator(new Calculator());
		
		System.out.println("Som van 4 en 5 is " + business.som(4,5));
		System.out.println("Product van 4 en 5 is " + business.product(4,5));
	}
	
}
