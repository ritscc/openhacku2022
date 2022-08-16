package cc.rits.openhacku2022.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cc.rits.openhacku2022.model.MenuModel;
import cc.rits.openhacku2022.model.TransactionModel;
import cc.rits.openhacku2022.repository.MenuRepository;
import lombok.RequiredArgsConstructor;

/**
 * メニューサービス
 */
@RequiredArgsConstructor
@Service
@Transactional
public class MenuService {

    final MenuRepository menuRepository;

    /**
     * メニューリストを取得
     * 
     * @param shopId 店舗ID
     * @param transaction 取引
     * @return メニューリスト
     */
    public List<MenuModel> getMenus(final Integer shopId, final TransactionModel transaction) {
        return this.menuRepository.selectByShopId(shopId);
    }

}
