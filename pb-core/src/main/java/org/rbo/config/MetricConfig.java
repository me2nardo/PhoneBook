package org.rbo.config;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.codahale.metrics.jvm.*;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;
import com.zaxxer.hikari.HikariDataSource;
import org.rbo.config.properties.MetricProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.lang.management.ManagementFactory;

/**
 * Metric Configuration
 */
@Configuration
@EnableMetrics(proxyTargetClass = true)
@EnableConfigurationProperties(MetricProperties.class)
public class MetricConfig extends MetricsConfigurerAdapter{

    private final Logger LOG = LoggerFactory.getLogger(MetricConfig.class);

    private final MetricProperties metricProperties;
    private HikariDataSource hikariDataSource;
    private MetricRegistry metricRegistry = new MetricRegistry();
    private HealthCheckRegistry healthCheckRegistry = new HealthCheckRegistry();


    public MetricConfig(MetricProperties metricProperties) {
        this.metricProperties = metricProperties;
    }

    @Autowired(required = false)
    public void setHikariDataSource(HikariDataSource hikariDataSource) {
        this.hikariDataSource = hikariDataSource;
    }

    @Bean
    public MetricRegistry getMetricRegistry() {
        return metricRegistry;
    }

    @Bean
    public HealthCheckRegistry getHealthCheckRegistry() {
        return healthCheckRegistry;
    }

    @PostConstruct
    public void init(){
        LOG.info("Register JVM metrics");
        metricRegistry.register("jvm.memory", new MemoryUsageGaugeSet());
        metricRegistry.register("jvm.garbage", new GarbageCollectorMetricSet());
        metricRegistry.register("jvm.threads", new ThreadStatesGaugeSet());
        metricRegistry.register("jvm.files", new FileDescriptorRatioGauge());
        metricRegistry.register("jvm.buffers", new BufferPoolMetricSet(ManagementFactory.getPlatformMBeanServer()));

        if (hikariDataSource != null) {
            LOG.debug("Monitoring the datasource");
            hikariDataSource.setMetricRegistry(metricRegistry);
        }

        if (metricProperties.isEnabled()){
            LOG.debug("Initializing Metrics JMX reporting");
            JmxReporter jmxReporter = JmxReporter.forRegistry(metricRegistry).build();
            jmxReporter.start();
        }
    }
}
