import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.sqlite.SQLiteDataSource
import ru.rosbank.javaschool.datasource.groovy.GroovyConnector

beans{

    propertyPlaceholderConfigurer PropertySourcesPlaceholderConfigurer, {
        location = 'classpath:db.properties'
    }

    sqLiteDataSource (SQLiteDataSource){    }

    connector GroovyConnector, ref(sqLiteDataSource), '${url}', '${login}', '${password}'
}
