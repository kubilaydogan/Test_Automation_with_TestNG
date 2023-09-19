package Framework.Core.ExtentReports;

import Framework.Core.ConfigurationReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();
    static Properties properties = System.getProperties();

    public synchronized static ExtentReports createExtentReports() {
        String filePath = System.getProperty("user.dir") + "/test-output/reports/" + LocalDate.now().format(DateTimeFormatter.ofPattern("MM_dd_yyyy")) + "/report.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(filePath);
        reporter.config().setDocumentTitle("TestNG Test automation");
        reporter.config().setReportName("Sample Extent Report");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Test Run", "Release 1.2");
        extentReports.setSystemInfo("Executed by", "Kubilay Dogan");
        extentReports.setSystemInfo("Browser", ConfigurationReader.get("browser"));
        extentReports.setSystemInfo("OS : ", System.getProperty("os.name"));
        extentReports.setSystemInfo("OS", properties.getProperty("os.name", "unknown"));
        extentReports.setSystemInfo("os.arch", properties.getProperty("os.arch", "unknown"));
        extentReports.setSystemInfo("os.version", properties.getProperty("os.version", "unknown"));
        extentReports.setSystemInfo("java.version", properties.getProperty("java.version", "unknown"));
        extentReports.setSystemInfo("java.home", properties.getProperty("java.home", "unknown"));
        extentReports.setSystemInfo("user.name", properties.getProperty("user.name", "unknown"));
        extentReports.setSystemInfo("user.dir", properties.getProperty("user.dir", "unknown"));
        return extentReports;
    }
}
