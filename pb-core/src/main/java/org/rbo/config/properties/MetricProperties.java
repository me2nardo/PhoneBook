package org.rbo.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties for MetricConfiguration
 */
@ConfigurationProperties(prefix = "metric")
public class MetricProperties {
    private String host;
    private int reportFrequency;
    private boolean enabled;
    private Prometheus prometheus;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getReportFrequency() {
        return reportFrequency;
    }

    public void setReportFrequency(int reportFrequency) {
        this.reportFrequency = reportFrequency;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Prometheus getPrometheus() {
        return prometheus;
    }

    public void setPrometheus(Prometheus prometheus) {
        this.prometheus = prometheus;
    }

    public static class Prometheus{
        private boolean enabled;
        private String endPoint;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getEndPoint() {
            return endPoint;
        }

        public void setEndPoint(String endPoint) {
            this.endPoint = endPoint;
        }
    }
}
