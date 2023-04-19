package com.makichanov.core.configuration;

import com.makichanov.core.converter.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ConverterConfig {

    //TODO: use MapStruct. Зачем так конфигурировать конвертеры?
    // TODO: 7/21/22 просил поправить, плохое решение
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
}
