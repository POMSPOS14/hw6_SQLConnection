package ru.rosbank.javaschool.datasource.programmatic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;

@Setter
@Getter


public class ProgrammaticConnector {


    private final String url;
    private final String username;
    private final String password;
    private final SQLiteDataSource sqLiteDataSource;

    public ProgrammaticConnector(String url, String username, String password, SQLiteDataSource sqLiteDataSource) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.sqLiteDataSource = sqLiteDataSource;
    }

    public String Connect() throws SQLException {
        sqLiteDataSource.setUrl(url);
        try (Connection connection = sqLiteDataSource.getConnection(username, password)){
            return "Programmatic Connect!";
        }
    }

    @Override
    public String toString() {
        return "ProgrammaticConnector{" +
                "url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sqLiteDataSource=" + sqLiteDataSource +
                '}';
    }
}
