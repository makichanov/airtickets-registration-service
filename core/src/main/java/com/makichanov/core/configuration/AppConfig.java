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
    //TODO: use MapStruct. Зачем так конфигурировать конвертеры?
    @Bean
    public ConversionServiceFactoryBean conversionService() {
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        Set<Converter<?, ?>> converters = new HashSet<>();
        converters.add(new AirTicketToAirTicketDtoConverter());
        converters.add(new CreateAirTicketDtoToAirTicketConverter());
        converters.add(new FlightAddressDtoToFlightAddressConverter());
        converters.add(new FlightAddressToFlightAddressDtoConverter());
        converters.add(new OrderToOrderDtoConverter());
        converters.add(new UserToUserDtoConverter());
        converters.add(new AuthenticateDtoToUserConverter());
        converters.add(new CreateFlightDetailsDtoToFlightDetailsConverter());
        converters.add(new FlightDetailsToFlightDetailsDtoConverter());
        converters.add(new CreateFlightAddressDtoToFlightAddressConverter());
        converters.add(new UpdateUserRequestDtoToUserConverter());
        converters.add(new UpdateAirTicketRequestDtoToAirTicketConverter());
        converters.add(new UpdateFlightAddressDtoToFlightAddressConverter());
        converters.add(new UpdateFlightDetailsDtoToFlightDetailsConverter());
        conversionServiceFactoryBean.setConverters(converters);
        conversionServiceFactoryBean.afterPropertiesSet();
        return conversionServiceFactoryBean;
    }

    //TODO: почему именно BCrypt? Не меняй, просто ответь мотивацию использовать именно такой энкодер
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
