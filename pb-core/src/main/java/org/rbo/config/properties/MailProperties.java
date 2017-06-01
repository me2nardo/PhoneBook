package org.rbo.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Base mail properties
 */
@ConfigurationProperties(prefix = "mail")
public class MailProperties {
    private String baseUrl;
    private String from;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
