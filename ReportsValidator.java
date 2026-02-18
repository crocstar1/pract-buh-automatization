public class ReportsValidator {

    public boolean isValidated(ReportsBuilder engine) {
        boolean isValidIncome = true;
        boolean isValidExpenses = true;

        for (int i = 1; i <= 3; i++) {

            if (engine.monthlyReports.get(i).sumOfIncome() != engine.yearlyReport.sumOfIncomeByMonth(i)) {
                System.out.println("Обнаружено несоответствие по доходам в " + i + " месяце.");
                isValidIncome = false;
            }

            if (engine.monthlyReports.get(i).sumOfExpenses() != engine.yearlyReport.sumOfExpensesByMonth(i)) {
                System.out.println("Обнаружено несоответствие по расходам в " + i + " месяце.");
                isValidExpenses = false;
            }
        }
        return (isValidExpenses && isValidIncome);
    }
}
