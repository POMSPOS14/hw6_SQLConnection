package ru.rosbank.javaschool.datasource.kotlin;

import org.springframework.beans.factory.annotation.Value;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class KotlinConnector {

    private final SQLiteDataSource sqLiteDataSource;
    private String url;
    private String username;
    private String password;


    public KotlinConnector(SQLiteDataSource sqLiteDataSource, @Value("${url}") String url, @Value("${login}") String username, @Value("${password}") String password) {
        this.sqLiteDataSource = sqLiteDataSource;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String Connect() throws SQLException {
        sqLiteDataSource.setUrl(url);
        try (Connection connection = sqLiteDataSource.getConnection(username, password)) {
            return "Kotlin Connect!";
        }
    }

}
