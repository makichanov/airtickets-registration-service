package com.makichanov.core.configuration;

import com.makichanov.core.converter.*;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
public class AppConfig {
    @Bean
    public ConversionServiceFactoryBean conversionService(PasswordEncoder passwordEncoder) {
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        Set<Converter<?, ?>> converters = new HashSet<>();
        converters.add(new AirTicketToAirTicketDtoConverter());
        converters.add(new CreatingAirTicketToAirTicketConverter());
        converters.add(new FlightAddressDtoToFlightAddressConverter());
        converters.add(new FlightAddressToFlightAddressDtoConverter());
        converters.add(new OrderToOrderDtoConverter());
        converters.add(new UserToUserDtoConverter());
        converters.add(new AuthenticatingDtoToUserConverter(passwordEncoder));
        converters.add(new CreatingFlightDetailsDtoToFlightDetailsConverter());
        converters.add(new FlightDetailsToFlightDetailsDtoConverter());
        converters.add(new CreatingFlightAddressDtoToFlightAddressConverter());
        conversionServiceFactoryBean.setConverters(converters);
        conversionServiceFactoryBean.afterPropertiesSet();
        return conversionServiceFactoryBean;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Airtickets Registration System")
                        .contact(new Contact()
                                .email("vanya133719@gmail.com")
                                .name("Ivan Riabov"))
                );
    }
}
