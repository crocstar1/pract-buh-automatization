import java.util.ArrayList;
import java.util.List;

public class MonthlyReport {
    public ArrayList<Transaction> transactions = new ArrayList<>();

    public MonthlyReport(List<String> lines) {
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.split(",");

            Transaction t = new Transaction(parts[0], Boolean.parseBoolean(parts[1].trim().toLowerCase()), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
            transactions.add(t);
        }
    }

    public int sumOfIncome() {
        int sum = 0;

        for (Transaction t : transactions) {
            if (!t.isExpense) {
                sum += t.sumOfOne * t.quantity;
            }
        }
        return sum;
    }

    public int sumOfExpenses() {
        int sum = 0;

        for (Transaction t : transactions) {
            if (t.isExpense) {
                sum += t.sumOfOne * t.quantity;
            }
        }
        return sum;
    }

    public int getMaxIncome() {
        int max = 0;

        for (Transaction t : transactions) {
            if (!t.isExpense) {
                if ((t.sumOfOne * t.quantity) > max) max = t.sumOfOne * t.quantity;
            }
        }
        return max;
    }

    public int getMaxExpense() {
        int max = 0;

        for (Transaction t : transactions) {
            if (t.isExpense) {
                if ((t.sumOfOne * t.quantity) > max) max = t.sumOfOne * t.quantity;
            }
        }
        return max;
    }

    public String getTopExpenseName(int maxExpense) { // принимает уже найденный максимум, что не вычислять его много раз

        for (Transaction t : transactions) {
            if (t.isExpense && ((t.sumOfOne * t.quantity) == maxExpense)) {
                return t.itemName;
            }
        }
        return "";
    }

    public String getTopProduct(int maxIncome) { // принимает уже найденный максимум, что не вычислять его много раз

        for (Transaction t : transactions) {
            if (!t.isExpense && ((t.sumOfOne * t.quantity) == maxIncome)) {
                return t.itemName;
            }
        }
        return "";
    }

    public void printStatistics(int month) {
        int maxIncome = getMaxIncome(); // вычисляем и передаем в метод для поиска названия
        int maxExpense = getMaxExpense();
        String topProduct = getTopProduct(maxIncome);
        String TopExpenseName = getTopExpenseName(maxExpense);


        System.out.println("Статистика за " + month + " месяц:");
        System.out.println("-- Cамый прибыльный товар: " + topProduct + " на сумму: " + maxIncome);
        System.out.println("-- Самая большая трата: " + TopExpenseName + " на сумму: " + maxExpense);

        /*
         Методы getMaxIncome, getTopProduct (и то же с тратами) и поля в printStatistic можно убрать и сделать проще:
         посчитать все в самом printStatistics сразу:

         public void printStatistics(int monthNumber) {
            int maxIncome = 0;
            String topProduct = "";

            int maxExpense = 0;
            String topExpenseCategory = "";

            // Один цикл для поиска всего сразу
            for (Transaction t : transactions) {
                int currentSum = t.sumOfOne * t.quantity;

                if (!t.isExpense) { // Считаем доходы
                    if (currentSum > maxIncome) {
                        maxIncome = currentSum;
                        topProduct = t.itemName;
                    }
                } else { // Считаем расходы
                    if (currentSum > maxExpense) {
                        maxExpense = currentSum;
                        topExpenseCategory = t.itemName;
                    }
                }
            }
         */
    }
}
