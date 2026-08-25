package taxes;

public class UsnIncome implements TaxSystem {
    @Override
    public int calcTaxFor(int debit, int credit) {
        return debit * 6 / 100;
    }
}