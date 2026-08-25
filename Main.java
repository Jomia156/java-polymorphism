import taxes.UsnIncome;
import taxes.UsnIncomeMinusExpense;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Тест 1: УСН доходы (6%) ===");
        Company company = new Company("Ромашка", new UsnIncome());

        company.shiftMoney(1000);   // доход 1000
        company.shiftMoney(-300);   // расход 300
        System.out.println("До уплаты: debit=" + company.getDebit() + ", credit=" + company.getCredit());
        // Налог: 1000 * 6% = 60
        company.payTaxes();
        System.out.println("После уплаты: debit=" + company.getDebit() + ", credit=" + company.getCredit());
        System.out.println();

        System.out.println("=== Тест 2: УСН доходы минус расходы (15%) ===");
        company.setTaxSystem(new UsnIncomeMinusExpense());

        company.shiftMoney(2000);   // доход 2000
        company.shiftMoney(-500);   // расход 500
        System.out.println("До уплаты: debit=" + company.getDebit() + ", credit=" + company.getCredit());
        // Налог: (2000 - 500) * 15% = 225
        company.payTaxes();
        System.out.println("После уплаты: debit=" + company.getDebit() + ", credit=" + company.getCredit());
        System.out.println();

        System.out.println("=== Тест 3: УСН доходы минус расходы — расходы превышают доходы (налог 0) ===");
        company.shiftMoney(100);    // доход 100
        company.shiftMoney(-500);   // расход 500
        System.out.println("До уплаты: debit=" + company.getDebit() + ", credit=" + company.getCredit());
        // Налог: max((100 - 500) * 15%, 0) = 0
        company.payTaxes();
        System.out.println("После уплаты: debit=" + company.getDebit() + ", credit=" + company.getCredit());
        System.out.println();

        System.out.println("=== Тест 4: shiftMoney(0) не меняет счётчики ===");
        company.shiftMoney(500);
        company.shiftMoney(0);      // ничего не должно измениться
        System.out.println("После shiftMoney(0): debit=" + company.getDebit() + ", credit=" + company.getCredit());
        company.payTaxes();
    }
}