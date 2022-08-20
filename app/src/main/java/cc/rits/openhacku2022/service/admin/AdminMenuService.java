package cc.rits.openhacku2022.service.admin;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.rits.openhacku2022.exception.ErrorCode;
import cc.rits.openhacku2022.exception.ForbiddenException;
import cc.rits.openhacku2022.exception.NotFoundException;
import cc.rits.openhacku2022.model.MenuModel;
import cc.rits.openhacku2022.model.ShopModel;
import cc.rits.openhacku2022.repository.MenuRepository;
import cc.rits.openhacku2022.repository.ShopRepository;
import lombok.RequiredArgsConstructor;

/**
 * メニューサービス(管理者用)
 */
@RequiredArgsConstructor
@Service
@Transactional
public class AdminMenuService {

    private final ShopRepository shopRepository;

    private final MenuRepository menuRepository;

    /**
     * メニューリストを取得
     *
     * @param shopId 店舗ID
     * @param shop 店舗
     * @return メニューリスト
     */
    public List<MenuModel> getMenus(final Integer shopId, final ShopModel shop) {
        // 店舗の存在チェック
        this.shopRepository.selectById(shopId) //
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_SHOP));

        // ログイン店舗と対象店舗が一致するかチェック
        if (!Objects.equals(shopId, shop.getId())) {
            throw new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION);
        }

        return this.menuRepository.selectByShopId(shopId);
    }

    /**
     * メニューを削除
     * 
     * @param shopId 店舗ID
     * @param menuId メニューID
     * @param shop 店舗
     */
    public void deleteMenu(final Integer shopId, final Integer menuId, final ShopModel shop) {
        // 店舗の存在チェック
        this.shopRepository.selectById(shopId) //
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_SHOP));

        // ログイン店舗と対象店舗が一致するかチェック
        if (!Objects.equals(shopId, shop.getId())) {
            throw new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION);
        }

        // メニューの存在チェック & 削除
        this.menuRepository.selectById(menuId) //
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_MENU));
        this.menuRepository.deleteById(menuId);
    }

}
