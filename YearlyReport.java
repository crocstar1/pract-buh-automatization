import java.util.ArrayList;
import java.util.List;

public class YearlyReport {
    public ArrayList<YearlyRecord> yearReport = new ArrayList<>();

    public YearlyReport(List<String> lines) {
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.split(",");

            YearlyRecord t = new YearlyRecord(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Boolean.parseBoolean(parts[2]));
            yearReport.add(t);
        }
    }

    public int sumOfIncomeByMonth(int month) {

        for (YearlyRecord t : yearReport) {
            if (!t.isExpense && t.month == month) {
                return t.amount;
            }
        }
        return 0;
    }

    public int sumOfExpensesByMonth(int month) {
        for (YearlyRecord t : yearReport) {
            if (t.isExpense && t.month == month) {
                return t.amount;
            }
        }
        return 0;
    }

    public int sumOfIncome() {
        int sum = 0;

        for (YearlyRecord t : yearReport) {
            if (!t.isExpense) sum += t.amount;
        }
        return sum;
    }

    public int sumOfExpenses() {
        int sum = 0;

        for (YearlyRecord t : yearReport) {
            if (t.isExpense) sum += t.amount;
        }
        return sum;
    }

    public int countOfExpensesByYear() {
        int count = 0;

        for (YearlyRecord t : yearReport) {
            if (t.isExpense) count++;
        }
        return count;
    }

    public int countOfIncomesByYear() {
        int count = 0;

        for (YearlyRecord t : yearReport) {
            if (!t.isExpense) count++;
        }
        return count;
    }

    public void printStatisticsByYear() {
        for (int i = 1; i <= 3; i++) {
            int profitByMonth = sumOfIncomeByMonth(i) - sumOfExpensesByMonth(i);
            System.out.println("Прибыль за " + i + " месяц: " + profitByMonth);
        }

        System.out.println("Средний расход за все месяцы: " + sumOfExpenses() / countOfExpensesByYear());
        System.out.println("Средний доход за все месяцы: " + sumOfIncome() / countOfIncomesByYear());
    }
}
