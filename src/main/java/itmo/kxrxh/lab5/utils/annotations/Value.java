package itmo.kxrxh.lab5.utils.annotations;

public @interface Value {
    double min() default Double.MAX_VALUE;

    double max() default Double.MIN_VALUE;
}
