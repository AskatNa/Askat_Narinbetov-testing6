package utils;

import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider(name = "loginDDT")
    public static Object[][] loginDDT() {
        return ExcelReader.read("testdata/orangehrm_data.xlsx", "Login");
    }
}
