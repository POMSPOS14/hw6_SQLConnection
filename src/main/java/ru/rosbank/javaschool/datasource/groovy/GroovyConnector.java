package ru.rosbank.javaschool.datasource.groovy;

import lombok.Getter;
import lombok.Setter;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;

@Setter
@Getter
public class GroovyConnector {
    private SQLiteDataSource sqLiteDataSource;
    private String url;
    private String username;
    private String password;

    public GroovyConnector(SQLiteDataSource sqLiteDataSource, String url, String username, String password) {
        this.sqLiteDataSource = sqLiteDataSource;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String connect() throws SQLException {
        sqLiteDataSource.setUrl(url);
        try (Connection connection = sqLiteDataSource.getConnection(username, password)){
            return "Groovy Connect!";
        }
    }



}
