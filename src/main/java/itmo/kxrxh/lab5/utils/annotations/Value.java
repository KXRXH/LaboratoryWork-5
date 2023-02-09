package itmo.kxrxh.lab5.utils.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Value {
    double min() default Double.MIN_VALUE;

    double max() default Double.MAX_VALUE;
}
