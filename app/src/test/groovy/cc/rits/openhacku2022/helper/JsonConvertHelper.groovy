package cc.rits.openhacku2022.helper


import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.ObjectMapper
import lombok.SneakyThrows

/**
 * Jsonを変換するヘルパー
 */
class JsonConvertHelper {

    private static final ObjectMapper objectMapper = new ObjectMapper() //
        .configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true)

    /**
     * オブジェクトをJSON文字列に変換
     *
     * @param object object
     * @return string
     */
    @SneakyThrows
    static String convertObjectToJson(final Object object) {
        return objectMapper.writeValueAsString(object)
    }

    /**
     * JSON文字列をオブジェクトに変換
     *
     * @param json json
     * @param clazz clazz
     * @return object
     */
    @SneakyThrows
    static <T> T convertJsonToObject(final String json, final Class<T> clazz) {
        return objectMapper.readValue(json, clazz)
    }

}
