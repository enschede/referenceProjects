package java8.builder;

import java.time.LocalDate;

/**
 * Created by marc on 09/12/14.
 */
public class FinancialFactBuilder {
  
  private FinancialFactType type;
  private Amount amount;
  private LocalDate date;
  private String description;
  
  public FinancialFactBuilder setAmount(Amount amount) {
    this.amount = amount;
    return this;
  }

  public FinancialFactBuilder setType(FinancialFactType type) {
    this.type = type;
    return this;
  }

  public FinancialFactBuilder setDate(LocalDate date) {
    this.date = date;
    return this;
  }

  public FinancialFactBuilder setDescription(String description) {
    this.description = description;
    return this;
  }

  public FinancialFact build()
      throws IncompleteBuilderException {
    
    if( type==null || amount==null || date==null)
      throw new IncompleteBuilderException();
    
    FinancialFact financialFact = new FinancialFact();
    financialFact.setDebitLedgerAccount(type.getDebitLedgerAccount());
    financialFact.setCreditLedgerAccount(type.getCreditLedgerAccount());
    financialFact.setAmount(amount);
    financialFact.setDate(date);
    financialFact.setDescription(description);
    
    return financialFact;
  }
}
