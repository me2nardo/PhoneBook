package org.rbo.util;

/**
 * @author vitalii.levash
 */
public class Constants {


    public static final String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]*$";

    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";
    public static final String SPRING_PROFILE_LOCAL_DEV = "ldev";
    public static final String SPRING_PROFILE_NO_SWAGGER = "no-swagger";
    // Spring profile used to disable running liquibase
    public static final String SPRING_PROFILE_NO_LIQUIBASE = "no-liquibase";

    public static final String SYSTEM_ACCOUNT = "system";

    private Constants() {
    }
}
