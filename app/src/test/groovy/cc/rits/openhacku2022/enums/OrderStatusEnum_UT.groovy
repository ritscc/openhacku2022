package cc.rits.openhacku2022.enums

import cc.rits.openhacku2022.BaseSpecification
import cc.rits.openhacku2022.exception.BaseException
import cc.rits.openhacku2022.exception.ErrorCode
import cc.rits.openhacku2022.exception.InternalServerErrorException

/**
 * OrderStatusEnumの単体テスト
 */
class OrderStatusEnum_UT extends BaseSpecification {

    def "find: 注文ステータスを検索"() {
        when:
        def result = OrderStatusEnum.find(id)

        then:
        result == expectedOrderStatus

        where:
        id || expectedOrderStatus
        0  || OrderStatusEnum.ACCEPTED
        1  || OrderStatusEnum.IN_PREPARATION
        2  || OrderStatusEnum.COMPLETED
    }

    def "find: 存在しないIDの場合は500エラー"() {
        when:
        OrderStatusEnum.find(-1)

        then:
        final BaseException exception = thrown()
        verifyException(exception, new InternalServerErrorException(ErrorCode.UNEXPECTED_ERROR))
    }

}
