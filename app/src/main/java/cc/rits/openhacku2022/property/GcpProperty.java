package cc.rits.openhacku2022.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * GCPプロパティ
 */
@Data
@Configuration
@ConfigurationProperties("gcp")
public class GcpProperty {

    /**
     * プロジェクトID
     */
    String projectId;

    /**
     * Cloud Storage
     */
    CloudStorage cloudStorage;

    @Data
    public static class CloudStorage {

        /**
         * 有効フラグ
         */
        boolean enabled;

        /**
         * バケット名
         */
        String bucketName;

    }

}
