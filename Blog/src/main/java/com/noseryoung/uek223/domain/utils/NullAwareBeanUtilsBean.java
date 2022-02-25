package com.noseryoung.uek223.domain.utils;

import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class NullAwareBeanUtilsBean extends BeanUtilsBean {

    @Override
    @SneakyThrows
    public void copyProperty(Object dest, String name, Object value) {
        if (value == null) {
            return;
        }
        super.copyProperty(dest, name, value);
    }

}