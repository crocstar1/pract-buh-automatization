import java.util.HashMap;
import java.util.List;

public class ReportsBuilder {

    public HashMap<Integer, MonthlyReport> monthlyReports = new HashMap<>();
    public YearlyReport yearlyReport;

    public boolean loadMonthlyReports() { //сделаем boolean, что занималась только считыванием, а в main уже общение с пользователем (с годовым отчетом можно так же)
        boolean allSuccess = true;

        for (int i = 1; i <= 3; i++) {
            String fileName = "resources/m.20210" + i + ".csv";
            List<String> lines = FileReader.readFileContents(fileName);

            if (lines != null) {
                monthlyReports.put(i, new MonthlyReport(lines));
            } else {
                System.out.println("Ошибка! Не найден или не читается файл с данными за " + i + " месяц.");
                allSuccess = false;
            }
        }
        return allSuccess;
    }

    public void loadYearlyReport() {
        String fileName = "resources/y.2021.csv";
        List<String> lines = FileReader.readFileContents(fileName);

        if (lines != null) {
            yearlyReport = new YearlyReport(lines);
            System.out.println("Отчет за год успешно загружен.");
            System.out.println();
        } else {
            System.out.println("Ошибка! Не найден или не читается файл с данными за 2021 год.");
            System.out.println();
        }
    }
}
