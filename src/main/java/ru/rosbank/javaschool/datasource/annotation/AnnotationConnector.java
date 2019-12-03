package ru.rosbank.javaschool.datasource.annotation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;


@Getter
@Setter
@ToString
@Component("connector")
public class AnnotationConnector {

    private final SQLiteDataSource sqLiteDataSource;
    private String url;
    private String username;
    private String password;


    public AnnotationConnector(SQLiteDataSource sqLiteDataSource, @Value("${url}") String url, @Value("${login}") String username, @Value("${password}") String password) {
        this.sqLiteDataSource = sqLiteDataSource;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String connect() throws SQLException {
        sqLiteDataSource.setUrl(url);
        try (Connection connection = sqLiteDataSource.getConnection(username, password)) {
            return "Annotation Connect!";
        }
    }

}
