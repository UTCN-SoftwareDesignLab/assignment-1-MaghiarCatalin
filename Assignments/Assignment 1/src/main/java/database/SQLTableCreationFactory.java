package database;

import static database.Constants.Tables.*;

/**
 * Created by Alex on 11/03/2017.
 */
public class SQLTableCreationFactory {

    public String getCreateSQLForTable(String table) {
        switch (table) {
            case USER:
                return "CREATE TABLE IF NOT EXISTS user (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  username VARCHAR(200) NOT NULL," +
                        "  password VARCHAR(64) NOT NULL," +
                        "  role VARCHAR(64) NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                        "  UNIQUE INDEX username_UNIQUE (username ASC));";
            case ACCOUNT:
                return "CREATE TABLE IF NOT EXISTS client_account (" +
                        "  id int(11) NOT NULL AUTO_INCREMENT," +
                        "  client_id int(11) NOT NULL " +
                        "  card_number varchar(12) NOT NULL," +
                        "  account_type varchar(250) NOT NULL," +
                        "  amount int NOT NULL," +
                        "  date_created varchar(10) NOT NULL," +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE KEY id_UNIQUE (id ASC)" +
                        "  UNIQUE KEY card_number_UNIQUE (card_number ASC)" +
                        ") ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;";
            case CLIENT:
                return "CREATE TABLE IF NOT EXISTS client (" +
                        "  id INT NOT NULL AUTO_INCREMENT," +
                        "  name VARCHAR(200) NOT NULL," +
                        "  identity_card_number VARCHAR(4) NOT NULL," +
                        "  personal_numerical_code VARCHAR(13) NOT NULL" +
                        "  address VARCHAR(200) NOT NULL" +
                        "  email VARCHAR(250) NOT NULL" +
                        "  PRIMARY KEY (id)," +
                        "  UNIQUE INDEX id_UNIQUE (id ASC)," +
                        "  UNIQUE INDEX pnc_UNIQUE (personal_numerical_code ASC));";

            default:
                return "";

        }
    }

}
