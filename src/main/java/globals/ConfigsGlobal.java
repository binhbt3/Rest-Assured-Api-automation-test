package globals;

import helpers.PropertiesHelper;

public class ConfigsGlobal {
    public static String BASE_URI = PropertiesHelper.getValue("BASE_URI");
    public static String BASE_PATH = PropertiesHelper.getValue("BASE_PATH");
    public static String USERNAME = PropertiesHelper.getValue("USERNAME");
    public static String PASSWORD = PropertiesHelper.getValue("PASSWORD");
    public static String CONTENT_TYPE = PropertiesHelper.getValue("CONTENT_TYPE");
    public static String TEST_DATA_RESOURCES = PropertiesHelper.getValue("TEST_DATA_RESOURCES");
    public static String SCHEMA_ALL_BOOKS = PropertiesHelper.getValue("SCHEMA_ALL_BOOKS");
    public static String SCHEMA_BOOK = PropertiesHelper.getValue("SCHEMA_BOOK");
    public static String SCHEMA_USER = PropertiesHelper.getValue("SCHEMA_USER");
    public static String SCHEMA_ALL_USERS = PropertiesHelper.getValue("SCHEMA_ALL_USERS");
    public static int TOTAL_TEST_CASE = 0;
    public static int TEST_CASE_PASSED = 0;
    public static int TEST_CASE_FAILED = 0;
    public static int TEST_CASE_SKIPED = 0;

}
