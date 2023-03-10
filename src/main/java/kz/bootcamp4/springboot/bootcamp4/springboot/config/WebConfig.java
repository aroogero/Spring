package kz.bootcamp4.springboot.bootcamp4.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.time.Duration;
import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
    //готовый класс который принадлежит Спринг-у. Фишка в том, что способствует считывать целиком
    ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
    source.setBasename("classpath:translation"); //classpath - путь, translation - базовое название
    source.setDefaultEncoding("UTF-8");
    return source;
    }

    @Bean
    public CookieLocaleResolver localeResolver() { //решала проблем - решает проблемы с куки с сохранением данных
        CookieLocaleResolver resolver = new CookieLocaleResolver("lang");
        //у куки 2 самых важных критерий - название куки(ключ) и время смерти
        resolver.setDefaultLocale(new Locale("en"));
        resolver.setCookieMaxAge(Duration.ofDays(30));
        return resolver;
    }

    @Bean
    public LocaleChangeInterceptor localeInterceptor() { //перехватчик гетзапросов - сигнал бэкэнду чтобы настроил язык
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lng");
        return interceptor;
    }
    //я гетпараметром передам под ключом название lng со значением en он настроит английский язык на 30 дней

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeInterceptor());
    }
}

