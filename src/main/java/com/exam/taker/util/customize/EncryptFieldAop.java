package com.exam.taker.util.customize;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Objects;

/**
 * 安全字段加密解密切面
 *
 * @author: 1000
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Aspect
@Component
public class EncryptFieldAop {
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Value("${xjbt.secretkey}")
    private String secretKey;

    @Pointcut("@annotation(com.exam.taker.util.customize.EncryptMethod)")
    public void annotationPointCut() {
    }

    @Around("annotationPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object responseObj = null;
        try {
            Object[] request = joinPoint.getArgs();
            for (Object object : request) {
                if (object instanceof Collection) {
                    Collection collection = (Collection) object;
                    collection.forEach(var -> {
                        try {
                            handleEncrypt(var);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    });
                } else {
                    handleEncrypt(object);
                }
            }
            responseObj = joinPoint.proceed();
            if (responseObj instanceof Collection) {
                Collection collection = (Collection) responseObj;
                collection.forEach(var -> {
                    try {
                        handleDecrypt(var);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
            } else {
                handleDecrypt(responseObj);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("SecureFieldAop 异常{}", throwable);
        }
        return responseObj;
    }


    /**
     * 处理加密
     *
     * @param requestObj
     */
    private void handleEncrypt(Object requestObj) throws IllegalAccessException {
        //System.out.println("  加密秘钥secretKey ==="+secretKey);
        if (Objects.isNull(requestObj)) {
            return;
        }
        Field[] fields = requestObj.getClass().getDeclaredFields();
        for (Field field : fields) {
            boolean hasSecureField = field.isAnnotationPresent(EncryptField.class);
            if (hasSecureField) {
                field.setAccessible(true);
                String plaintextValue = (String) field.get(requestObj);
                String encryptValue = "";
                if(plaintextValue!=null&&!plaintextValue.equals("")){
                    encryptValue = AseUtil.encrypt(plaintextValue, secretKey);
                }
                //String encryptValue = AseUtil.encrypt(plaintextValue, secretKey);
                field.set(requestObj, encryptValue);
            }
        }
    }


    /**
     * 处理解密
     *
     * @param responseObj
     */
    private Object handleDecrypt(Object responseObj) throws IllegalAccessException {
        //System.out.println("  解密秘钥secretKey ==="+secretKey);
        if (Objects.isNull(responseObj)) {
            return null;
        }

        Field[] fields = responseObj.getClass().getDeclaredFields();
        for (Field field : fields) {
            boolean hasSecureField = field.isAnnotationPresent(EncryptField.class);
            if (hasSecureField) {
                field.setAccessible(true);
                String encryptValue = (String) field.get(responseObj);
                String plaintextValue = "";
                if(encryptValue!=null&&!encryptValue.equals("")) {
                     //System.out.println( " encryptValue="+encryptValue);
                     plaintextValue = AseUtil.decrypt(encryptValue, secretKey);
                }
                field.set(responseObj, plaintextValue);
            }
        }
        return responseObj;
    }
}
