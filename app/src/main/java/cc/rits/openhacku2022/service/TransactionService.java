package cc.rits.openhacku2022.service;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.rits.openhacku2022.exception.ErrorCode;
import cc.rits.openhacku2022.exception.ForbiddenException;
import cc.rits.openhacku2022.exception.NotFoundException;
import cc.rits.openhacku2022.model.TransactionModel;
import cc.rits.openhacku2022.query_service.TransactionQueryService;
import cc.rits.openhacku2022.query_service.dto.TransactionWithOrderDto;
import cc.rits.openhacku2022.repository.ShopRepository;
import lombok.RequiredArgsConstructor;

/**
 * トランザクションサービス
 */
@RequiredArgsConstructor
@Service
@Transactional
public class TransactionService {

    private final TransactionQueryService transactionQueryService;

    private final ShopRepository shopRepository;

    /**
     * 取引を取得
     *
     * @param shopId 店舗ID
     * @param transaction 取引
     * @return 取引
     */
    public TransactionWithOrderDto getTransaction(final Integer shopId, final TransactionModel transaction) {
        // 店舗の存在チェック
        this.shopRepository.selectById(shopId) //
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_SHOP));

        // 取引中の店舗かチェック
        if (!Objects.equals(shopId, transaction.getShopId())) {
            throw new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION);
        }
        return this.transactionQueryService.getTransaction(shopId, transaction);
    }

}
