package java8.builder;

import java.math.BigDecimal;

/**
 * Created by marc on 09/12/14.
 */
public class Amount {
  
  private BigDecimal amount;
  
  public Amount(BigDecimal amount) {
    this.amount = amount;
  }
  
  public Amount(String amount) {
    this.amount = new BigDecimal(amount);
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }
}
