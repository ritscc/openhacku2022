package cc.rits.openhacku2022.query_service;

import java.util.List;
import java.util.stream.Collectors;

import cc.rits.openhacku2022.db.mapper.ShopMapper;
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

    private final ShopMapper shopMapper;

    /**
     * 取引リストを取得
     *
     * @param shopId 店舗ID
     * @return 取引リスト
     */
    public List<TransactionWithOrderDto> getTransactions(final Integer shopId) {
        return this.transactionMapper.selectByShopId(shopId).stream() //
            .map(TransactionWithOrderDto::new) //
            .collect(Collectors.toList());
    }

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

        // NOTE: 店舗名が稀にnullになるバグ対策
        final var shop = this.shopMapper.selectByPrimaryKey(shopId);
        result.setShopName(shop.getName());
        return new TransactionWithOrderDto(result);
    }

}
