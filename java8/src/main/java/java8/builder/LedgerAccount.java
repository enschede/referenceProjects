package java8.builder;

/**
 * Created by marc on 09/12/14.
 */
public enum LedgerAccount {
  
  Bank(1010),
  Debtors(1300),
  Creditors(1600);
  
  private Integer ledgerAccountNumber;
  
  LedgerAccount(Integer ledgerAccountNumber) {
    this.ledgerAccountNumber = ledgerAccountNumber;
  }

  public Integer getLedgerAccountNumber() {
    return ledgerAccountNumber;
  }
}
