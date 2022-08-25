package cc.rits.openhacku2022.service

import cc.rits.openhacku2022.BaseSpecification
import cc.rits.openhacku2022.api.request.OrderCreateRequest
import cc.rits.openhacku2022.exception.BaseException
import cc.rits.openhacku2022.exception.ErrorCode
import cc.rits.openhacku2022.exception.ForbiddenException
import cc.rits.openhacku2022.exception.NotFoundException
import cc.rits.openhacku2022.helper.RandomHelper
import cc.rits.openhacku2022.model.MenuModel
import cc.rits.openhacku2022.model.ShopModel
import cc.rits.openhacku2022.model.TransactionModel
import cc.rits.openhacku2022.repository.MenuRepository
import cc.rits.openhacku2022.repository.OrderRepository
import cc.rits.openhacku2022.repository.ShopRepository
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired

/**
 * OrderServiceの単体テスト
 */
class OrderService_UT extends BaseSpecification {

    @Autowired
    OrderService sut

    @SpringBean
    ShopRepository shopRepository = Mock()

    @SpringBean
    OrderRepository orderRepository = Mock()

    @SpringBean
    MenuRepository menuRepository = Mock()

    def "createOrder: 注文を作成"() {
        given:
        final shop = RandomHelper.mock(ShopModel)
        final transaction = RandomHelper.mock(TransactionModel)
        transaction.shopId = shop.id

        final requestBody = RandomHelper.mock(OrderCreateRequest)
        final menus = requestBody.menus.collect {
            MenuModel.builder()
                .id(it.menuId)
                .build()
        }

        when:
        this.sut.createOrder(shop.id, requestBody, transaction)

        then:
        1 * this.shopRepository.selectById(shop.id) >> Optional.of(shop)
        1 * this.menuRepository.selectByShopId(shop.id) >> menus
        1 * this.orderRepository.insert(_)
    }

    def "createOrder: 店舗が存在しない場合は404エラー"() {
        given:
        final shop = RandomHelper.mock(ShopModel)
        final transaction = RandomHelper.mock(TransactionModel)
        transaction.shopId = shop.id

        final requestBody = RandomHelper.mock(OrderCreateRequest)

        when:
        this.sut.createOrder(shop.id, requestBody, transaction)

        then:
        1 * this.shopRepository.selectById(shop.id) >> Optional.empty()
        final BaseException exception = thrown()
        verifyException(exception, new NotFoundException(ErrorCode.NOT_FOUND_SHOP))
    }

    def "createOrder: 取引中の店舗以外の場合は403エラー"() {
        given:
        final shop = RandomHelper.mock(ShopModel)
        final transaction = Spy(TransactionModel)

        final requestBody = RandomHelper.mock(OrderCreateRequest)

        when:
        this.sut.createOrder(shop.id, requestBody, transaction)

        then:
        1 * this.shopRepository.selectById(shop.id) >> Optional.of(shop)
        1 * transaction.shopId >> shop.id + 1
        final BaseException exception = thrown()
        verifyException(exception, new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION))
    }

    def "createOrder: 存在しないメニューIDを指定した場合は404エラー"() {
        given:
        final shop = RandomHelper.mock(ShopModel)
        final transaction = RandomHelper.mock(TransactionModel)
        transaction.shopId = shop.id

        final requestBody = RandomHelper.mock(OrderCreateRequest)

        when:
        this.sut.createOrder(shop.id, requestBody, transaction)

        then:
        1 * this.shopRepository.selectById(shop.id) >> Optional.of(shop)
        1 * this.menuRepository.selectByShopId(shop.id) >> []
        final BaseException exception = thrown()
        verifyException(exception, new NotFoundException(ErrorCode.NOT_FOUND_MENU))
    }

}
