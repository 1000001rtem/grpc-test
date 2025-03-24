package com.eremin.grpctest.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@TestExecutionListeners(value = {LiquibaseTestListener.class}, mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
@ContextConfiguration(initializers = {PostgresContainerInitializer.class,})
@SpringBootTest
@ConfigurationPropertiesScan
@ActiveProfiles("test")
@Import(GrpcClientConfiguration.class)
public @interface IntegrationTest {
}
