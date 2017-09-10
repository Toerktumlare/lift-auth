package se.andolf.lift.auth.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Thomas on 2016-06-06.
 */
@Configuration
@EnableNeo4jRepositories(basePackages = "se.andolf")
@EnableTransactionManagement
public class SessionConfig {

    private static final Log LOG = LogFactory.getLog(SessionConfig.class);

    @Autowired
    private DriverConfig driverConfig;

    @Bean
    public SessionFactory sessionFactory(){
        LOG.debug("Loading session factory");
        return new SessionFactory(getConfiguration(), "se.andolf");
    }

    @Bean
    public Neo4jTransactionManager transactionManager() {
        return new Neo4jTransactionManager(sessionFactory());
    }

    @Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {
        org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
        config.driverConfiguration()
                .setDriverClassName(driverConfig.getName())
                .setURI(driverConfig.getUri())
                .setCredentials(driverConfig.getUsername(), driverConfig.getPassword());
        if(driverConfig.getPool().getSize() != 0)
            config.driverConfiguration().setConnectionPoolSize(driverConfig.getPool().getSize());
        config.autoIndexConfiguration().setAutoIndex(driverConfig.getIndex());

        return config;
    }
}
