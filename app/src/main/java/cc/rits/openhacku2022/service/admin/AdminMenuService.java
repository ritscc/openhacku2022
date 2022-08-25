package cc.rits.openhacku2022.service.admin;

import java.util.List;
import java.util.Objects;

import org.apache.commons.net.util.Base64;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.rits.openhacku2022.api.request.MenuUpsertRequest;
import cc.rits.openhacku2022.exception.ErrorCode;
import cc.rits.openhacku2022.exception.ForbiddenException;
import cc.rits.openhacku2022.exception.NotFoundException;
import cc.rits.openhacku2022.model.FileModel;
import cc.rits.openhacku2022.model.MenuModel;
import cc.rits.openhacku2022.model.ShopModel;
import cc.rits.openhacku2022.repository.MenuRepository;
import cc.rits.openhacku2022.repository.ShopRepository;
import cc.rits.openhacku2022.util.FileStorageUtil;
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

    private final FileStorageUtil fileStorageUtil;

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
     * メニューを作成
     * 
     * @param shopId 店舗ID
     * @param requestBody メニュー作成リクエスト
     * @param shop 店舗
     */
    public void createMenu(final Integer shopId, final MenuUpsertRequest requestBody, final ShopModel shop) {
        // 店舗の存在チェック
        this.shopRepository.selectById(shopId) //
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_SHOP));

        // ログイン店舗と対象店舗が一致するかチェック
        if (!Objects.equals(shopId, shop.getId())) {
            throw new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION);
        }

        // メニュー画像をアップロード
        final var file = FileModel.builder() //
            .content(Base64.decodeBase64(requestBody.getImage())) //
            .build();
        final var imageUrl = this.fileStorageUtil.upload(file);

        // メニューを作成
        final var menu = MenuModel.builder() //
            .shopId(shopId) //
            .name(requestBody.getName()) //
            .price(requestBody.getPrice()) //
            .imageUrl(imageUrl) //
            .build();
        this.menuRepository.insert(menu);
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
        this.menuRepository.selectByIdAndShopId(menuId, shopId) //
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_MENU));
        this.menuRepository.deleteById(menuId);
    }

    /**
     * メニューを更新
     * 
     * @param shopId 店舗ID
     * @param menuId メニューID
     * @param requestBody メニュー更新リクエスト
     * @param shop 店舗
     */
    public void updateMenu(final Integer shopId, final Integer menuId, final MenuUpsertRequest requestBody, final ShopModel shop) {
        // 店舗の存在チェック
        this.shopRepository.selectById(shopId) //
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_SHOP));

        // ログイン店舗と対象店舗が一致するかチェック
        if (!Objects.equals(shopId, shop.getId())) {
            throw new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION);
        }

        // メニュー画像をアップロード
        final var file = FileModel.builder() //
            .content(Base64.decodeBase64(requestBody.getImage())) //
            .build();
        final var imageUrl = this.fileStorageUtil.upload(file);

        // メニューの存在チェック & 更新
        final var menu = this.menuRepository.selectByIdAndShopId(menuId, shopId) //
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_MENU));
        menu.setId(menuId);
        menu.setShopId(shopId);
        menu.setName(requestBody.getName());
        menu.setPrice(requestBody.getPrice());
        menu.setImageUrl(imageUrl);

        this.menuRepository.update(menu);
    }

}
