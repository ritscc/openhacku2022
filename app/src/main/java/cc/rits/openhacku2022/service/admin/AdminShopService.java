package cc.rits.openhacku2022.service.admin;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.rits.openhacku2022.exception.ErrorCode;
import cc.rits.openhacku2022.exception.NotFoundException;
import cc.rits.openhacku2022.model.ShopModel;
import cc.rits.openhacku2022.repository.ShopRepository;
import lombok.RequiredArgsConstructor;

/**
 * 店舗サービス(管理者)
 */
@RequiredArgsConstructor
@Service
@Transactional
public class AdminShopService {

    private final ShopRepository shopRepository;

    /**
     * 店舗を取得
     *
     * @param shop 店舗
     * @return 店舗
     */
    public ShopModel getShop(final ShopModel shop) {
        // 店舗を取得
        return this.shopRepository.selectById(shop.getId()) //
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_SHOP));
    }

}
