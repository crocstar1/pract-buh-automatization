
import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReportsBuilder builder = new ReportsBuilder();
        ReportsValidator validator = new ReportsValidator();

        while (true) {
            printMenu();
            int userInput;

            while (true) {
                try {
                    userInput = Integer.parseInt(scanner.nextLine());
                    if (userInput > 5) {
                        System.out.println("Такой команды не существует!");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка! Введите целое число.");
                }
            }

            switch (userInput) {
                case 1:
                    if (builder.loadMonthlyReports()) {
                        System.out.println("Месячные отчеты успешно загружены!");
                        System.out.println();
                    } else {
                        System.out.println("Ошибка! Часть данных не загружена, проверьте файлы.");
                    }
                    break;
                case 2:
                    builder.loadYearlyReport();
                    break;
                case 3:
                    if (builder.monthlyReports.isEmpty() || builder.yearlyReport == null) {
                        System.out.println("Ошибка! Сначала загрузите ВСЕ отчёты (1 и 2 пункт).");
                    } else {
                        if (validator.isValidated(builder)) {
                            System.out.println("Отчеты полностью совпадают!");
                        }
                    }
                    System.out.println();
                    break;
                case 4:
                    for (int i = 1; i <= 3; i++) {
                        builder.monthlyReports.get(i).printStatistics(i);
                        System.out.println();
                    }
                    break;
                case 5:
                    builder.yearlyReport.printStatisticsByYear();
                    System.out.println();
                    break;
                case 0:
                    System.out.println("Программа завершена.");
                    return;
            }
        }
    }

    public static void printMenu() {
        System.out.println("- Выберите команду -");
        System.out.println("1. Считать все месячные отчёты.");
        System.out.println("2. Считать годовой отчёт.");
        System.out.println("3. Сверить отчёты.");
        System.out.println("4. Вывести информацию о всех месячных отчётах.");
        System.out.println("5. Вывести информацию о годовом отчёте.");
    }
}