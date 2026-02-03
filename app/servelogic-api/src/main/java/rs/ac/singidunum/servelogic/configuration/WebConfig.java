package rs.ac.singidunum.servelogic.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
            .favorParameter(true)            // Enable ?format=
            .parameterName("format")         // The parameter name
            .ignoreAcceptHeader(true)        // STOP listening to the browser headers
            .defaultContentType(MediaType.APPLICATION_JSON) // JSON first
            .mediaType("json", MediaType.APPLICATION_JSON)
            .mediaType("xml", MediaType.APPLICATION_XML);
    }
}