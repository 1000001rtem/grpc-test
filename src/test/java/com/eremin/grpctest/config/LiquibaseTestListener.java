package com.eremin.grpctest.config;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

public class LiquibaseTestListener extends AbstractTestExecutionListener {
    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        recreateDatabase(testContext);
    }

    private void recreateDatabase(TestContext testContext) throws LiquibaseException {
        var springLiquibase = testContext.getApplicationContext().getBean(SpringLiquibase.class);

        springLiquibase.setDropFirst(true);
        springLiquibase.afterPropertiesSet();
    }
}
