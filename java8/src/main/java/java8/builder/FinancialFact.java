package java8.builder;

import java.time.LocalDate;

/**
 * Created by marc on 09/12/14.
 */
public class FinancialFact {
  
  private Amount amount;
  private LedgerAccount debitLedgerAccount;
  private LedgerAccount creditLedgerAccount;
  private LocalDate date;
  private String description;

  public FinancialFact() {
  }

  public Amount getAmount() {
    return amount;
  }

  public void setAmount(Amount amount) {
    this.amount = amount;
  }

  public LedgerAccount getCreditLedgerAccount() {
    return creditLedgerAccount;
  }

  public void setCreditLedgerAccount(LedgerAccount creditLedgerAccount) {
    this.creditLedgerAccount = creditLedgerAccount;
  }

  public LedgerAccount getDebitLedgerAccount() {
    return debitLedgerAccount;
  }

  public void setDebitLedgerAccount(LedgerAccount debitLedgerAccount) {
    this.debitLedgerAccount = debitLedgerAccount;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
