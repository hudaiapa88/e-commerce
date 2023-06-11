package com.uc.ecommerce.core.i18n;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Configuration
@Slf4j
public class LocaleResolver extends AcceptHeaderLocaleResolver implements WebMvcConfigurer {
    private static final List<Locale> LOCALES = Arrays.asList(
            new Locale("tr"),
            new Locale("en")
    );

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String language = request.getHeader("Accept-Language");
        if (Optional.ofNullable(language).isPresent()) {
            List<Locale.LanguageRange> list = Locale.LanguageRange.parse(language);
            Locale locale = Locale.lookup(list, LOCALES);
            return locale;

        }
        return Locale.getDefault();

    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
      //  rs.addBasenames("messages");
        rs.addBasenames("ValidationMessages");
        rs.setDefaultEncoding("UTF-8");
        rs.setUseCodeAsDefaultMessage(true);
        return rs;
    }

    @Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        System.out.println(bean);
        return bean;
    }

}
