package cc.rits.openhacku2022.model

import cc.rits.openhacku2022.BaseSpecification

/**
 * OrderModelの単体テスト
 */
class OrderModel_UT extends BaseSpecification {

    def "getOrderMenu: メニューIDから注文メニューを取得"() {
        given:
        final order = OrderModel.builder()
            .menus([
                OrderMenuModel.builder().menuId(1).build(),
                OrderMenuModel.builder().menuId(2).build(),
            ])
            .build()

        when:
        final result = order.getOrderMenu(menuId)

        then:
        result.isPresent() == expectedIsPresent

        where:
        menuId || expectedIsPresent
        1      || true
        2      || true
        3      || false
    }

}
