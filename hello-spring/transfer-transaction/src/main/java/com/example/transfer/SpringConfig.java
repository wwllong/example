package com.example.transfer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author jack.wen
 * @since 2023/11/28 00:34
 */
@Configuration
@EnableTransactionManagement
public class SpringConfig {

}

