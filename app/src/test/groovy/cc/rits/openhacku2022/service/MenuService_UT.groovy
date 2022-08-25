package cc.rits.openhacku2022.service

import cc.rits.openhacku2022.BaseSpecification
import cc.rits.openhacku2022.exception.BaseException
import cc.rits.openhacku2022.exception.ErrorCode
import cc.rits.openhacku2022.exception.ForbiddenException
import cc.rits.openhacku2022.exception.NotFoundException
import cc.rits.openhacku2022.helper.RandomHelper
import cc.rits.openhacku2022.model.MenuModel
import cc.rits.openhacku2022.model.ShopModel
import cc.rits.openhacku2022.model.TransactionModel
import cc.rits.openhacku2022.repository.MenuRepository
import cc.rits.openhacku2022.repository.ShopRepository
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired

/**
 * MenuServiceの単体テスト
 */
class MenuService_UT extends BaseSpecification {

    @Autowired
    MenuService sut

    @SpringBean
    ShopRepository shopRepository = Mock()

    @SpringBean
    MenuRepository menuRepository = Mock()

    def "getMenus: メニューリストを取得"() {
        given:
        final shop = RandomHelper.mock(ShopModel)
        final transaction = Spy(TransactionModel)

        final menus = [RandomHelper.mock(MenuModel), RandomHelper.mock(MenuModel)]

        when:
        final result = this.sut.getMenus(shop.id, transaction)

        then:
        1 * this.shopRepository.selectById(shop.id) >> Optional.of(shop)
        1 * transaction.shopId >> shop.id
        1 * this.menuRepository.selectByShopId(shop.id) >> menus
        result == menus
    }

    def "getMenus: 店舗が存在しない場合は404エラー"() {
        given:
        final shop = RandomHelper.mock(ShopModel)
        final transaction = Spy(TransactionModel)

        when:
        this.sut.getMenus(shop.id, transaction)

        then:
        1 * this.shopRepository.selectById(shop.id) >> Optional.empty()
        final BaseException exception = thrown()
        verifyException(exception, new NotFoundException(ErrorCode.NOT_FOUND_SHOP))
    }

    def "getMenus: 取引中の店舗以外の場合は403エラー"() {
        given:
        final shop = RandomHelper.mock(ShopModel)
        final transaction = Spy(TransactionModel)

        when:
        this.sut.getMenus(shop.id, transaction)

        then:
        1 * this.shopRepository.selectById(shop.id) >> Optional.of(shop)
        1 * transaction.shopId >> shop.id + 1
        final BaseException exception = thrown()
        verifyException(exception, new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION))
    }

}
