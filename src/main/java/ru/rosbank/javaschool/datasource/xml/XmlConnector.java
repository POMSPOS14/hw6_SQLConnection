package ru.rosbank.javaschool.datasource.xml;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;


@ToString
@Setter
@Getter
public class XmlConnector {

    private SQLiteDataSource sqLiteDataSource;
    private String url;
    private String username;
    private String password;

    public XmlConnector(SQLiteDataSource sqLiteDataSource) {
        this.sqLiteDataSource = sqLiteDataSource;
    }

    public String connect() throws SQLException {
        sqLiteDataSource.setUrl(url);
        try (Connection connection = sqLiteDataSource.getConnection(username, password)){
            return "Xml Connect!";
        }
    }

}
