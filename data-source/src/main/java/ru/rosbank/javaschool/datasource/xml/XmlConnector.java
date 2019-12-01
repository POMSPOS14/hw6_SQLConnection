package ru.rosbank.javaschool.datasource.xml;

import lombok.Setter;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;

@Setter
public class XmlConnector {

    private SQLiteDataSource sqLiteDataSource;
    private String url;
    private String username;
    private String password;

    public XmlConnector(SQLiteDataSource sqLiteDataSource) {
        this.sqLiteDataSource = sqLiteDataSource;
    }

    public String Connect() throws SQLException {
        sqLiteDataSource.setUrl(url);
        try (Connection connection = sqLiteDataSource.getConnection(username, password)){
            return "Xml Connect!";
        }
    }


    @Override
    public String toString() {
        return "XmlConnector{" +
                "sqLiteDataSource=" + sqLiteDataSource +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
