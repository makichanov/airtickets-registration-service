package com.makichanov.core.configuration;

import com.makichanov.core.converter.*;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.HashSet;
import java.util.Set;
//TODO: SOLID принципы можно использовать и на практике. Здесь актуален первый. Один конфиг на ТМ, конвертеры и сваггер - плохо.
// Декомпозировать

@Configuration
//todo: зачем тут @ComponentScan? причем ты сканируешь все, что сканировалось бы по дефолту
@ComponentScan(basePackages = "com.makichanov.core")
// TODO: 7/14/22 Уверен в надобности @EnableJpaRepository?
@EnableJpaRepositories(basePackages = "com.makichanov.core.repository")
@EnableJpaAuditing
@EnableTransactionManagement
public class AppConfig {

    //TODO: Есть ли смысл создавать в конфиге бин транзакшн манагера. Спринг сам инжектнет нужную имплементацию, если что.
    // не надо пустую строку после названия класса оставлять
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    //TODO: use MapStruct. Зачем так конфигурировать конвертеры?
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

    } // TODO: 7/14/22 Для кого ты оставил столько пустого места внизу?


}
