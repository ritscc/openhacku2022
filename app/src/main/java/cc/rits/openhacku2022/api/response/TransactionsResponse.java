package cc.rits.openhacku2022.api.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 取引リストレスポンス
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionsResponse {

    /**
     * 取引リスト
     */
    @Schema(required = true)
    List<TransactionResponse> transactions;

}
