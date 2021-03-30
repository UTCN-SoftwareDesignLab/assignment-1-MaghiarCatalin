package database;

import java.util.*;

/**
 * Created by Alex on 11/03/2017.
 */
public class Constants {

    public static class Schemas {
        public static final String TEST = "test_bank";
        public static final String PRODUCTION = "bank";

        public static final String[] SCHEMAS = new String[]{TEST, PRODUCTION};
    }

    public static class Tables {
        public static final String USER = "user";
        public static final String ACCOUNT = "account";
        public static final String CLIENT = "client";

        public static final String[] ORDERED_TABLES_FOR_CREATION = new String[]{USER, CLIENT, ACCOUNT};
    }
}