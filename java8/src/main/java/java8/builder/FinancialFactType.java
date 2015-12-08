package java8.builder;

/**
 * Created by marc on 09/12/14.
 */
public enum FinancialFactType {
  
  DebtorPayment(LedgerAccount.Bank, LedgerAccount.Debtors),
  CreditPayment(LedgerAccount.Bank, LedgerAccount.Creditors);

  private LedgerAccount debitLedgerAccount;
  private LedgerAccount creditLedgerAccount;

  FinancialFactType(LedgerAccount creditLedgerAccount, LedgerAccount debitLedgerAccount) {
    this.creditLedgerAccount = creditLedgerAccount;
    this.debitLedgerAccount = debitLedgerAccount;
  }

  public LedgerAccount getCreditLedgerAccount() {
    return creditLedgerAccount;
  }

  public LedgerAccount getDebitLedgerAccount() {
    return debitLedgerAccount;
  }
}
