package cc.rits.openhacku2022.service.admin

import cc.rits.openhacku2022.BaseSpecification
import cc.rits.openhacku2022.api.request.MenuCreateRequest
import cc.rits.openhacku2022.exception.BaseException
import cc.rits.openhacku2022.exception.ErrorCode
import cc.rits.openhacku2022.exception.ForbiddenException
import cc.rits.openhacku2022.exception.NotFoundException
import cc.rits.openhacku2022.helper.RandomHelper
import cc.rits.openhacku2022.model.MenuModel
import cc.rits.openhacku2022.model.ShopModel
import cc.rits.openhacku2022.repository.MenuRepository
import cc.rits.openhacku2022.repository.ShopRepository
import cc.rits.openhacku2022.util.FileStorageUtil
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired

/**
 * AdminMenuService_UTの単体テスト
 */
class AdminMenuService_UT extends BaseSpecification {

    @Autowired
    AdminMenuService sut

    @SpringBean
    ShopRepository shopRepository = Mock()

    @SpringBean
    MenuRepository menuRepository = Mock()

    @SpringBean
    FileStorageUtil fileStorageUtil = Mock()

    def "getMenus: メニューリストを取得"() {
        given:
        final shop = RandomHelper.mock(ShopModel)
        final menus = [RandomHelper.mock(MenuModel)]

        when:
        final result = this.sut.getMenus(shop.id, shop)

        then:
        1 * this.shopRepository.selectById(shop.id) >> Optional.of(shop)
        1 * this.menuRepository.selectByShopId(shop.id) >> menus
        result == menus
    }

    def "getMenus: 店舗が存在しない場合は404エラー"() {
        given:
        final shop = RandomHelper.mock(ShopModel)

        when:
        this.sut.getMenus(shop.id, shop)

        then:
        1 * this.shopRepository.selectById(shop.id) >> Optional.empty()
        final BaseException exception = thrown()
        verifyException(exception, new NotFoundException(ErrorCode.NOT_FOUND_SHOP))
    }

    def "getMenus: ログイン店舗以外の場合は403エラー"() {
        given:
        final shop = Spy(ShopModel)

        when:
        this.sut.getMenus(1, shop)

        then:
        1 * this.shopRepository.selectById(1) >> Optional.of(shop)
        1 * shop.id >> 2
        final BaseException exception = thrown()
        verifyException(exception, new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION))
    }

    def "createMenu: メニューを作成"() {
        given:
        final shop = RandomHelper.mock(ShopModel)
        final requestBody = RandomHelper.mock(MenuCreateRequest)

        when:
        this.sut.createMenu(shop.id, requestBody, shop)

        then:
        1 * this.shopRepository.selectById(shop.id) >> Optional.of(shop)
        1 * this.fileStorageUtil.upload(_) >> RandomHelper.alphanumeric(255)
        1 * this.menuRepository.insert(_)
    }

    def "createMenu: 店舗が存在しない場合は404エラー"() {
        given:
        final shop = RandomHelper.mock(ShopModel)
        final requestBody = RandomHelper.mock(MenuCreateRequest)

        when:
        this.sut.createMenu(shop.id, requestBody, shop)

        then:
        1 * this.shopRepository.selectById(shop.id) >> Optional.empty()
        final BaseException exception = thrown()
        verifyException(exception, new NotFoundException(ErrorCode.NOT_FOUND_SHOP))
    }

    def "createMenu: ログイン店舗以外の場合は403エラー"() {
        given:
        final shop = Spy(ShopModel)
        final requestBody = RandomHelper.mock(MenuCreateRequest)

        when:
        this.sut.createMenu(1, requestBody, shop)

        then:
        1 * this.shopRepository.selectById(1) >> Optional.of(shop)
        1 * shop.id >> 2
        final BaseException exception = thrown()
        verifyException(exception, new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION))
    }

    def "deleteMenu: メニューを削除"() {
        given:
        final shop = RandomHelper.mock(ShopModel)
        final menu = RandomHelper.mock(MenuModel)

        when:
        this.sut.deleteMenu(shop.id, menu.id, shop)

        then:
        1 * this.shopRepository.selectById(shop.id) >> Optional.of(shop)
        1 * this.menuRepository.selectById(menu.id) >> Optional.of(menu)
        1 * this.menuRepository.deleteById(menu.id)
    }

    def "deleteMenu: メニューが存在しない場合は404エラー"() {
        given:
        final shop = RandomHelper.mock(ShopModel)
        final menu = RandomHelper.mock(MenuModel)

        when:
        this.sut.deleteMenu(shop.id, menu.id, shop)

        then:
        1 * this.shopRepository.selectById(shop.id) >> Optional.of(shop)
        1 * this.menuRepository.selectById(menu.id) >> Optional.empty()
        final BaseException exception = thrown()
        verifyException(exception, new NotFoundException(ErrorCode.NOT_FOUND_MENU))
    }

    def "deleteMenu: 店舗が存在しない場合は404エラー"() {
        given:
        final shop = RandomHelper.mock(ShopModel)
        final menu = RandomHelper.mock(MenuModel)

        when:
        this.sut.deleteMenu(shop.id, menu.id, shop)

        then:
        1 * this.shopRepository.selectById(shop.id) >> Optional.empty()
        final BaseException exception = thrown()
        verifyException(exception, new NotFoundException(ErrorCode.NOT_FOUND_SHOP))
    }

    def "deleteMenu: ログイン店舗以外の場合は403エラー"() {
        given:
        final shop = Spy(ShopModel)
        final menu = RandomHelper.mock(MenuModel)

        when:
        this.sut.deleteMenu(1, menu.id, shop)

        then:
        1 * this.shopRepository.selectById(1) >> Optional.of(shop)
        1 * shop.id >> 2
        final BaseException exception = thrown()
        verifyException(exception, new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION))
    }
}
