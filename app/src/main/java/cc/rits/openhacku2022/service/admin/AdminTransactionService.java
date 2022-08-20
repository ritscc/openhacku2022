package cc.rits.openhacku2022.service.admin;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.rits.openhacku2022.exception.ErrorCode;
import cc.rits.openhacku2022.exception.ForbiddenException;
import cc.rits.openhacku2022.exception.NotFoundException;
import cc.rits.openhacku2022.model.ShopModel;
import cc.rits.openhacku2022.query_service.TransactionQueryService;
import cc.rits.openhacku2022.query_service.dto.TransactionWithOrderDto;
import cc.rits.openhacku2022.repository.ShopRepository;
import lombok.RequiredArgsConstructor;

/**
 * 取引サービス(管理者用)
 */
@RequiredArgsConstructor
@Service
@Transactional
public class AdminTransactionService {

    private final ShopRepository shopRepository;

    private final TransactionQueryService transactionQueryService;

    /**
     * 取引リストを取得
     *
     * @param shopId 店舗ID
     * @param shop 店舗
     * @return 取引リスト
     */
    public List<TransactionWithOrderDto> getTransactions(final Integer shopId, final ShopModel shop) {
        // 店舗の存在チェック
        this.shopRepository.selectById(shopId) //
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_SHOP));

        // ログイン中の店舗と店舗IDが一致するかチェック
        if (!Objects.equals(shop.getId(), shopId)) {
            throw new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION);
        }

        return this.transactionQueryService.getTransactions(shopId);
    }

    /**
     * 取引を取得
     * 
     * @param shopId 店舗ID
     * @param transactionId 取引ID
     * @param shop 店舗
     * @return 取引
     */
    public TransactionWithOrderDto getTransaction(final Integer shopId, final Integer transactionId, final ShopModel shop) {
        // 店舗の存在チェック
        this.shopRepository.selectById(shopId) //
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_SHOP));

        // ログイン中の店舗と店舗IDが一致するかチェック
        if (!Objects.equals(shop.getId(), shopId)) {
            throw new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION);
        }

        return this.transactionQueryService.getTransaction(shopId, transactionId);
    }

}
