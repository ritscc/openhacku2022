package cc.rits.openhacku2022.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * GCPプロパティ
 */
@Data
@Configuration
@ConfigurationProperties("stripe")
public class StripeProperty {

    /**
     * シークレットキー
     */
    String secretKey;

    /**
     * Stripe Checkout
     */
    Checkout checkout;

    @Data
    public static class Checkout {

        /**
         * 有効フラグ
         */
        boolean enabled;

        /**
         * プロダクトID
         */
        String productId;

        /**
         * 税率ID
         */
        String taxId;

        /**
         * 支払い成功時のリダイレクトURL
         */
        String successUrl;

        /**
         * 支払いキャンセル時のリダイレクトURL
         */
        String cancelUrl;

    }

}
