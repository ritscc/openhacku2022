package cc.rits.openhacku2022.query_service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.rits.openhacku2022.db.mapper.TransactionMapper;
import cc.rits.openhacku2022.exception.ErrorCode;
import cc.rits.openhacku2022.exception.NotFoundException;
import cc.rits.openhacku2022.query_service.dto.TransactionWithOrderDto;
import lombok.RequiredArgsConstructor;

/**
 * トランザクションクエリサービス
 */
@RequiredArgsConstructor
@Service
@Transactional
public class TransactionQueryService {

    private final TransactionMapper transactionMapper;

    /**
     * 取引を取得
     *
     * @param shopId 店舗ID
     * @param transactionId 取引ID
     * @return 取引
     */
    public TransactionWithOrderDto getTransaction(final Integer shopId, final Integer transactionId) {
        final var result = this.transactionMapper.selectByIdAndShopId(transactionId, shopId) //
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_TRANSACTION));
        return new TransactionWithOrderDto(result);
    }

}
