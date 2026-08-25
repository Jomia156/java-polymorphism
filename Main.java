import taxes.TaxSystem;
import taxes.UsnIncome;
import taxes.UsnIncomeMinusExpense;

public class Main {
    public static void main(String[] args) {
        // --- Полиморфизм через интерфейс TaxSystem ---
        // Одна переменная типа интерфейса может ссылаться на разные реализации:

        TaxSystem tax1 = new UsnIncome();                 // УСН доходы (6%)
        TaxSystem tax2 = new UsnIncomeMinusExpense();     // УСН доходы минус расходы (15%)

        System.out.println("=== Тест 1: УСН доходы (6%) ===");
        Company company = new Company("Ромашка", tax1);   // передаём через интерфейс

        company.shiftMoney(1000);
        company.shiftMoney(-300);
        System.out.println("До уплаты: debit=" + company.getDebit() + ", credit=" + company.getCredit());
        company.payTaxes();
        System.out.println("После уплаты: debit=" + company.getDebit() + ", credit=" + company.getCredit());
        System.out.println();

        System.out.println("=== Тест 2: УСН доходы минус расходы (15%) ===");
        company.setTaxSystem(tax2);   // меняем реализацию через тот же тип интерфейса

        company.shiftMoney(2000);
        company.shiftMoney(-500);
        System.out.println("До уплаты: debit=" + company.getDebit() + ", credit=" + company.getCredit());
        company.payTaxes();
        System.out.println("После уплаты: debit=" + company.getDebit() + ", credit=" + company.getCredit());
        System.out.println();

        System.out.println("=== Тест 3: расходы превышают доходы (налог = 0) ===");
        company.shiftMoney(100);
        company.shiftMoney(-500);
        System.out.println("До уплаты: debit=" + company.getDebit() + ", credit=" + company.getCredit());
        company.payTaxes();
        System.out.println("После уплаты: debit=" + company.getDebit() + ", credit=" + company.getCredit());
        System.out.println();

        System.out.println("=== Тест 4: shiftMoney(0) не меняет счётчики ===");
        company.shiftMoney(500);
        company.shiftMoney(0);
        System.out.println("После shiftMoney(0): debit=" + company.getDebit() + ", credit=" + company.getCredit());
        company.payTaxes();
    }
}