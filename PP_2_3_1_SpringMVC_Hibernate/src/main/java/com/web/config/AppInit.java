package com.web.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {


    //КАКОЙ-ТО ФИЛЬТР НУЖЕН

    //  Метод, указывающий на класс конфигурации
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    //  Добавление конфигурации, в которой инициализируем ViewResolver, для корректного отображения jsp.
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    //  Данный метод указывает url, на котором будет базироваться приложение
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
