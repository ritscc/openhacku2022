package cc.rits.openhacku2022.client;

import org.springframework.stereotype.Component;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.checkout.Session;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.checkout.SessionCreateParams;

import cc.rits.openhacku2022.exception.ErrorCode;
import cc.rits.openhacku2022.exception.InternalServerErrorException;
import cc.rits.openhacku2022.property.StripeProperty;
import lombok.RequiredArgsConstructor;

/**
 * 支払いクライアント
 */
@Component
@RequiredArgsConstructor
public class CheckoutClient {

    private final StripeProperty stripeProperty;

    /**
     * 支払い情報を送信する
     * 
     * @param paymentAmount 支払金額
     * @return 支払いURL
     */
    public String send(final Long paymentAmount) {

        if (!stripeProperty.getCheckout().isEnabled()) {
            return "";
        }

        // リダイレクト処理を設定
        Stripe.apiKey = stripeProperty.getSecretKey();
        final var priceCreateParams = PriceCreateParams.builder() //
            .setCurrency("JPY") //
            .setUnitAmount(paymentAmount) //
            .setProduct(stripeProperty.getCheckout().getProductId()) //
            .build();

        try {
            final var price = Price.create(priceCreateParams);
            final var sessionCreateParams = SessionCreateParams.builder() //
                .setMode(SessionCreateParams.Mode.PAYMENT) //
                .setSuccessUrl(stripeProperty.getCheckout().getSuccessUrl()) //
                .setCancelUrl(stripeProperty.getCheckout().getCancelUrl()) //
                .addLineItem(SessionCreateParams.LineItem.builder() //
                    .setQuantity(1L) //
                    .setPrice(price.getId()) //
                    .build()) //
                .build();
            final var session = Session.create(sessionCreateParams);
            return "redirect:" + session.getUrl();
        } catch (StripeException exception) {
            throw new InternalServerErrorException(ErrorCode.UNEXPECTED_ERROR);
        }
    }

}
