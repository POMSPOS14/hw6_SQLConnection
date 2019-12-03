package ru.rosbank.javaschool.datasource.java;

import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.sqlite.SQLiteDataSource;

public class JavaConfiguration {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        val configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new ClassPathResource("db.properties"));
        return configurer;
    }
    @Bean
    public SQLiteDataSource sqLiteDataSource() {
      return new SQLiteDataSource();
    }

    @Bean
    public JavaConnector connector(SQLiteDataSource sqLiteDataSource, @Value("${url}") String url, @Value("${login}") String username, @Value("${password}") String password){
        return new JavaConnector(sqLiteDataSource,url,username,password);
    }
}
