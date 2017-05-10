package org.rbo.config;

import com.codahale.metrics.MetricRegistry;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.dropwizard.DropwizardExports;
import io.prometheus.client.exporter.MetricsServlet;
import org.rbo.config.properties.MetricProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Prometheus Configuration
 */
@Configuration
@ConditionalOnClass({CollectorRegistry.class})
public class PrometheusConfig implements ServletContextInitializer {
    private final Logger LOG = LoggerFactory.getLogger(PrometheusConfig.class);
    private final MetricRegistry metricRegistry;
    private final MetricProperties metricProperties;

    public PrometheusConfig(MetricRegistry metricRegistry, MetricProperties metricProperties) {
        this.metricRegistry = metricRegistry;
        this.metricProperties = metricProperties;
    }

    public void onStartup(ServletContext servletContext) throws ServletException {
        if(this.metricProperties.getPrometheusProperties().isEnabled()) {
            String endpoint = this.metricProperties.getPrometheusProperties().getEndPoint();
            LOG.info("Initializing Metrics Prometheus endpoint at {}", endpoint);
            CollectorRegistry collectorRegistry = new CollectorRegistry();
            collectorRegistry.register(new DropwizardExports(this.metricRegistry));
            servletContext.addServlet("prometheusMetrics", new MetricsServlet(collectorRegistry)).addMapping(new String[]{endpoint});
        }

    }
}
