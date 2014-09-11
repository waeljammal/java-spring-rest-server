package spring;

import java.util.List;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.codehaus.jackson.map.SerializationConfig;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static org.codehaus.jackson.map.SerializationConfig.Feature.*;


/**
 * Created by Wael Jammal on 10/09/2014.
 *
 * Spring JavaConfig
 */
@Configuration
@ComponentScan(basePackages = { "com.rs" })
@EnableJpaRepositories(basePackages = { "com.rs.repository" })
@EnableTransactionManagement
public class AppConfiguration extends WebMvcConfigurerAdapter
{
    public static final String DB_NAME = "skillDb";

    /////////////////////////////////////////////////////////////////
    // Datasource
    /////////////////////////////////////////////////////////////////

    @Bean(destroyMethod = "shutdown")
    public DataSource dataSource()
    {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).setName(DB_NAME).build();
    }

    /////////////////////////////////////////////////////////////////
    // JPA Database & Adapter Config
    /////////////////////////////////////////////////////////////////

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory()
    {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.HSQL);
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setPersistenceUnitName(DB_NAME);
        factory.setPackagesToScan("com.rs");
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setJpaProperties(jpaProperties());
        factory.afterPropertiesSet();

        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager()
    {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }

    @Bean
    public HibernateExceptionTranslator exceptionTranslator()
    {
        return new HibernateExceptionTranslator();
    }

    public Properties jpaProperties()
    {
        Properties properties = new Properties();

        properties.put(Environment.HBM2DDL_AUTO, "create");
        //properties.put(Environment.HBM2DDL_IMPORT_FILES, "data.sql");
        properties.put("javax.persistence.schema-generation.create-database-schemas", "true");
        properties.put("javax.persistence.schema-generation.scripts.action", "create");
        properties.put("javax.persistence.schema-generation.scripts.create-target", "src/main/resources/schema.sql");
        properties.put("javax.persistence.database-product-name", "HSQL");

        return properties;
    }

    /////////////////////////////////////////////////////////////////
    // REST Object Mapping and Tidy output
    /////////////////////////////////////////////////////////////////

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter()
    {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper());
        return mappingJackson2HttpMessageConverter;
    }

    @Bean
    public ObjectMapper objectMapper()
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        return mapper;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters)
    {
        super.configureMessageConverters(converters);
        converters.add(mappingJackson2HttpMessageConverter());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer)
    {
        configurer.favorPathExtension(true)
                .ignoreAcceptHeader(true)
                .defaultContentType(MediaType.APPLICATION_XML)
                .useJaf(false)
                .mediaType("html", MediaType.TEXT_HTML)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML);
    }
}
