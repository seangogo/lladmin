package com.seangogo.base.exception;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * 实体不存在异常
 *
 * @author seang
 * @date 2019/7/3 11:28
 */
public class EntityExistException extends RuntimeException {

    public EntityExistException(Class clazz, Object... saveBodyParamsMap) {
        super(EntityExistException.generateMessage(clazz.getSimpleName(), toMap(String.class, String.class, saveBodyParamsMap)));
    }

    private static String generateMessage(String entity, Map<String, String> saveBodyParams) {
        return StringUtils.capitalize(entity) +
                " 已存在 " +
                saveBodyParams;
    }

    private static <K, V> Map<K, V> toMap(
            Class<K> keyType, Class<V> valueType, Object... entries) {
        if (entries.length % 2 == 1)
            throw new IllegalArgumentException("Invalid entries");
        return IntStream.range(0, entries.length / 2).map(i -> i * 2)
                .collect(HashMap::new,
                        (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
                        Map::putAll);
    }
}