package cc.rits.openhacku2022.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.rits.openhacku2022.exception.ErrorCode;
import cc.rits.openhacku2022.exception.ForbiddenException;
import cc.rits.openhacku2022.exception.NotFoundException;
import cc.rits.openhacku2022.model.MenuModel;
import cc.rits.openhacku2022.model.TransactionModel;
import cc.rits.openhacku2022.repository.MenuRepository;
import cc.rits.openhacku2022.repository.ShopRepository;
import lombok.RequiredArgsConstructor;

/**
 * メニューサービス
 */
@RequiredArgsConstructor
@Service
@Transactional
public class MenuService {

    private final ShopRepository shopRepository;

    private final MenuRepository menuRepository;

    /**
     * メニューリストを取得
     * 
     * @param shopId 店舗ID
     * @param transaction 取引
     * @return メニューリスト
     */
    public List<MenuModel> getMenus(final Integer shopId, final TransactionModel transaction) {
        // 店舗の存在チェック
        this.shopRepository.selectById(shopId) //
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_SHOP));

        // 取引中の店舗かチェック
        if (!Objects.equals(shopId, transaction.getShopId())) {
            throw new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION);
        }

        return this.menuRepository.selectByShopId(shopId);
    }

}
