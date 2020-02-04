package io.github.hizhangbo.util;

import io.github.hizhangbo.exception.CheckException;

import java.util.stream.Stream;

/**
 * @author Bob
 * @date 2020-02-03 0:36
 */
public class CheckUtil {

    private static final String[] INVALID_NAMES = {"admin",""};

    public static void checkName(String value){
        Stream.of(INVALID_NAMES).filter(name->name.equalsIgnoreCase(value))
                .findAny().ifPresent(name->{
                    throw new CheckException("name",value);
        });
    }

}
