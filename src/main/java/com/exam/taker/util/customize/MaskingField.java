package com.exam.taker.util.customize;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface MaskingField {

    /*脱敏类型(规则)*/
    MaskingTypeEnum type();

    /*判断注解是否生效的方法*/
    String isEffictiveMethod() default "";
}
