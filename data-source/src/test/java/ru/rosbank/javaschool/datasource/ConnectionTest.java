package ru.rosbank.javaschool.datasource;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.QualifierAnnotationAutowireCandidateResolver;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.sqlite.SQLiteDataSource;
import ru.rosbank.javaschool.datasource.annotation.AnnotationConnector;
import ru.rosbank.javaschool.datasource.groovy.GroovyConnector;
import ru.rosbank.javaschool.datasource.java.JavaConfiguration;
import ru.rosbank.javaschool.datasource.java.JavaConnector;
import ru.rosbank.javaschool.datasource.kotlin.BeansKt;
import ru.rosbank.javaschool.datasource.kotlin.KotlinConnector;
import ru.rosbank.javaschool.datasource.programmatic.ProgrammaticConnector;
import ru.rosbank.javaschool.datasource.xml.XmlConnector;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionTest {

    @Test
    void XmlConnection() throws SQLException {
        val context = new GenericApplicationContext();
        new XmlBeanDefinitionReader(context).loadBeanDefinitions("context.xml");
        context.refresh();
        XmlConnector bean = context.getBean(XmlConnector.class);
        assertEquals("Xml Connect!",bean.Connect());
    }

    @Test
    void AnnotationConnection() throws SQLException {
        val context = new AnnotationConfigApplicationContext("ru.rosbank.javaschool.datasource.annotation");
        AnnotationConnector bean = context.getBean(AnnotationConnector.class);
        assertEquals("Annotation Connect!",bean.Connect());
    }

    @Test
    void GroovyConnection() throws SQLException {
        val context = new GenericGroovyApplicationContext("context.groovy");
        GroovyConnector bean = context.getBean(GroovyConnector.class);
        assertEquals("Groovy Connect!",bean.Connect());
    }

    @Test
    void KotlinConnection() throws SQLException {
        val factory = new DefaultListableBeanFactory();
        factory.setAutowireCandidateResolver(new QualifierAnnotationAutowireCandidateResolver());
        val context = new GenericApplicationContext(factory);
        BeansKt.getBeans().initialize(context);
        context.refresh();
        KotlinConnector bean = context.getBean(KotlinConnector.class);
        assertEquals("Kotlin Connect!",bean.Connect());
    }


    @Test
    void JavaConnection() throws SQLException {
        val context = new AnnotationConfigApplicationContext(JavaConfiguration.class);
        JavaConnector bean = context.getBean(JavaConnector.class);
        assertEquals("Java Connect!",bean.Connect());
    }

    @Test
    void ProgrammaticConnection() throws SQLException {
        val context = new GenericApplicationContext();
        context.registerBean(PropertySourcesPlaceholderConfigurer.class, () -> {
            val configurer = new PropertySourcesPlaceholderConfigurer();
            configurer.setLocation(new ClassPathResource("db.properties"));
            return configurer;
        });

        context.registerBean("dbsource", SQLiteDataSource.class);
        context.registerBean("connector", ProgrammaticConnector.class, "${url}", "${login}", "${password}", new RuntimeBeanReference("dbsource"));
        context.refresh();
        ProgrammaticConnector bean = context.getBean(ProgrammaticConnector.class);
        assertEquals("Programmatic Connect!",bean.Connect());
    }


}