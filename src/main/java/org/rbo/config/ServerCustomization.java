package org.rbo.config;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

/**
 * Created by vitalii.levash on 20.04.2016.
 */
public class ServerCustomization extends ServerProperties {
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {

        super.customize(container);
        container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,
                "404.html"));
        /*
        container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,
                "500.html"));
        container.addErrorPages(new ErrorPage("/jsp/error.jsp"));
        */
    }
    @Bean
    public ServerProperties getServerProperties() {
        return new ServerCustomization();
    }
}
