package mockito;

public class Calculator {

	private Integer a, b;

	public Integer getA() {
		System.out.println("Calculator::getA()=" + a);
		return a;
	}

	public void setA(Integer a) {
		System.out.println("Calculator::setA(x)::x=" + a);
		this.a = a;
	}

	public Integer getB() {
		System.out.println("Calculator::getB()=" + b);
		return b;
	}

	public void setB(Integer b) {
		System.out.println("Calculator::setB(x)::x=" + b);
		this.b = b;
	}
	
	public Integer sum() {
		System.out.println("Calculator::sum()=" + (a+b));
		return a+b;
	}
	
	public Integer product() {
		System.out.println("Calculator::product()=" + (a*b));
		return a*b;
	}
	
}
