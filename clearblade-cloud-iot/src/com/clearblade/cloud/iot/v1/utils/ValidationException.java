package com.clearblade.cloud.iot.v1.utils;

import java.util.Iterator;
import java.util.Stack;

public class ValidationException extends IllegalArgumentException {
    private static ThreadLocal<Stack<Supplier<String>>> contextLocal = new ThreadLocal();

    public static void pushCurrentThreadValidationContext(Supplier<String> supplier) {
        Stack<Supplier<String>> stack = (Stack)contextLocal.get();
        if (stack == null) {
            stack = new Stack();
            contextLocal.set(stack);
        }

        stack.push(supplier);
    }

    public static void pushCurrentThreadValidationContext(final String context) {
        pushCurrentThreadValidationContext(new Supplier<String>() {
            public String get() {
                return context;
            }
        });
    }

    public static void popCurrentThreadValidationContext() {
        Stack<?> stack = (Stack)contextLocal.get();
        if (stack != null) {
            stack.pop();
        }

    }

    public ValidationException(String format, Object... args) {
        super(message((Stack)contextLocal.get(), format, args));
    }

    private static String message(Stack<Supplier<String>> context, String format, Object... args) {
        if (context != null && !context.isEmpty()) {
            StringBuilder result = new StringBuilder();
            Iterator var4 = context.iterator();

            while(var4.hasNext()) {
                Supplier<String> supplier = (Supplier)var4.next();
                result.append((String)supplier.get() + ": ");
            }

            return result.toString() + String.format(format, args);
        } else {
            return String.format(format, args);
        }
    }

    public interface Supplier<T> {
        T get();
    }
}