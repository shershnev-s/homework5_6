package by.tut.shershnev_s.repository.impl;

import by.tut.shershnev_s.repository.ConnectionRepository;
import by.tut.shershnev_s.util.PropertyUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;

import static by.tut.shershnev_s.repository.constant.ConnectionConstant.*;

public class ConnectionRepositoryImpl implements ConnectionRepository {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private PropertyUtil propertyUtil = new PropertyUtil();

    private static ConnectionRepository instance;

    public static ConnectionRepository getInstance() {
        if (instance == null) {
            instance = new ConnectionRepositoryImpl();
        }
        return instance;
    }

    private static HikariDataSource ds;

    @Override
    public Connection getConnection() {
        try {
            if (ds == null) {
                HikariConfig hikariConfig = new HikariConfig();
                hikariConfig.setJdbcUrl(propertyUtil.getProperty(DATABASE_URL));
                hikariConfig.setUsername(propertyUtil.getProperty(DATABASE_USERNAME));
                hikariConfig.setPassword(propertyUtil.getProperty(DATABASE_PASSWORD));
                hikariConfig.setConnectionTimeout(Long.parseLong(propertyUtil.getProperty(DATABASE_CONNECTION_TIMEOUT)));
                hikariConfig.addDataSourceProperty(DATABASE_CACHE_PREP_STMTS, propertyUtil.getProperty(DATABASE_CACHE_PREP_STMTS));
                hikariConfig.addDataSourceProperty(DATABASE_PREP_STMT_CASHE_SIZE, propertyUtil.getProperty(DATABASE_PREP_STMT_CASHE_SIZE));
                hikariConfig.addDataSourceProperty(PREP_STMT_CACHE_SQL_LIMIT, propertyUtil.getProperty(PREP_STMT_CACHE_SQL_LIMIT));

                ds = new HikariDataSource(hikariConfig);
            }
            return ds.getConnection();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new IllegalStateException("App cannot get connection to database");
        }
    }
}
