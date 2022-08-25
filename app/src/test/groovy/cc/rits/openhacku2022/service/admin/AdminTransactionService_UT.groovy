package cc.rits.openhacku2022.service.admin

import cc.rits.openhacku2022.BaseSpecification
import cc.rits.openhacku2022.exception.BaseException
import cc.rits.openhacku2022.exception.ErrorCode
import cc.rits.openhacku2022.exception.ForbiddenException
import cc.rits.openhacku2022.exception.NotFoundException
import cc.rits.openhacku2022.helper.RandomHelper
import cc.rits.openhacku2022.model.ShopModel
import cc.rits.openhacku2022.model.TransactionModel
import cc.rits.openhacku2022.query_service.TransactionQueryService
import cc.rits.openhacku2022.query_service.dto.TransactionWithOrderDto
import cc.rits.openhacku2022.repository.ShopRepository
import cc.rits.openhacku2022.repository.TransactionRepository
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired

/**
 * AdminTransactionService_UTの単体テスト
 */
class AdminTransactionService_UT extends BaseSpecification {

    @Autowired
    AdminTransactionService sut

    @SpringBean
    ShopRepository shopRepository = Mock()

    @SpringBean
    TransactionRepository transactionRepository = Mock()

    @SpringBean
    TransactionQueryService transactionQueryService = Mock()

    def "getTransactions: 取引リストを取得"() {
        given:
        final shop = RandomHelper.mock(ShopModel)
        final transactions = [RandomHelper.mock(TransactionWithOrderDto)]

        when:
        final result = this.sut.getTransactions(shop.id, shop)

        then:
        1 * this.shopRepository.selectById(shop.id) >> Optional.of(shop)
        1 * this.transactionQueryService.getTransactions(shop.id) >> transactions
        result == transactions
    }

    def "getTransactions: 店舗が存在しない場合は404エラー"() {
        given:
        final shop = RandomHelper.mock(ShopModel)

        when:
        this.sut.getTransactions(shop.id, shop)

        then:
        1 * this.shopRepository.selectById(shop.id) >> Optional.empty()
        final BaseException exception = thrown()
        verifyException(exception, new NotFoundException(ErrorCode.NOT_FOUND_SHOP))
    }

    def "getTransactions: ログイン店舗以外の場合は403エラー"() {
        given:
        final shop = Spy(ShopModel)

        when:
        this.sut.getTransactions(1, shop)

        then:
        1 * this.shopRepository.selectById(1) >> Optional.of(shop)
        1 * shop.id >> 2
        final BaseException exception = thrown()
        verifyException(exception, new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION))
    }

    def "getTransaction: 取引を取得"() {
        given:
        final shop = RandomHelper.mock(ShopModel)
        final transaction = RandomHelper.mock(TransactionWithOrderDto)

        when:
        final result = this.sut.getTransaction(shop.id, transaction.id, shop)

        then:
        1 * this.shopRepository.selectById(shop.id) >> Optional.of(shop)
        1 * this.transactionQueryService.getTransaction(shop.id, transaction.id) >> transaction
        result == transaction
    }

    def "getTransaction: 店舗が存在しない場合は404エラー"() {
        given:
        final shop = RandomHelper.mock(ShopModel)
        final transaction = RandomHelper.mock(TransactionWithOrderDto)

        when:
        this.sut.getTransaction(shop.id, transaction.id, shop)

        then:
        1 * this.shopRepository.selectById(shop.id) >> Optional.empty()
        final BaseException exception = thrown()
        verifyException(exception, new NotFoundException(ErrorCode.NOT_FOUND_SHOP))
    }

    def "getTransaction: ログイン店舗以外の場合は403エラー"() {
        given:
        final shop = Spy(ShopModel)
        final transaction = RandomHelper.mock(TransactionWithOrderDto)

        when:
        this.sut.getTransaction(1, transaction.id, shop)

        then:
        1 * this.shopRepository.selectById(1) >> Optional.of(shop)
        1 * shop.id >> 2
        final BaseException exception = thrown()
        verifyException(exception, new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION))
    }

    def "deleteTransaction: 取引を削除"() {
        given:
        final shop = RandomHelper.mock(ShopModel)
        final transaction = RandomHelper.mock(TransactionModel)

        when:
        this.sut.deleteTransaction(shop.id, transaction.id, shop)

        then:
        1 * this.shopRepository.selectById(shop.id) >> Optional.of(shop)
        1 * this.transactionRepository.selectByIdAndShopId(transaction.id, shop.id) >> Optional.of(transaction)
        1 * this.transactionRepository.delete(transaction)
    }

    def "deleteTransaction: 店舗が存在しない場合は404エラー"() {
        given:
        final shop = RandomHelper.mock(ShopModel)
        final transaction = RandomHelper.mock(TransactionModel)

        when:
        this.sut.deleteTransaction(shop.id, transaction.id, shop)

        then:
        1 * this.shopRepository.selectById(shop.id) >> Optional.empty()
        final BaseException exception = thrown()
        verifyException(exception, new NotFoundException(ErrorCode.NOT_FOUND_SHOP))
    }

    def "deleteTransaction: ログイン店舗以外の場合は403エラー"() {
        given:
        final shop = Spy(ShopModel)
        final transaction = RandomHelper.mock(TransactionModel)

        when:
        this.sut.deleteTransaction(1, transaction.id, shop)

        then:
        1 * this.shopRepository.selectById(1) >> Optional.of(shop)
        1 * shop.id >> 2
        final BaseException exception = thrown()
        verifyException(exception, new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION))
    }

    def "deleteTransaction: 取引が存在しない場合は404エラー"() {
        given:
        final shop = RandomHelper.mock(ShopModel)
        final transaction = RandomHelper.mock(TransactionModel)

        when:
        this.sut.deleteTransaction(shop.id, transaction.id, shop)

        then:
        1 * this.shopRepository.selectById(shop.id) >> Optional.of(shop)
        1 * this.transactionRepository.selectByIdAndShopId(transaction.id, shop.id) >> Optional.empty()
        final BaseException exception = thrown()
        verifyException(exception, new NotFoundException(ErrorCode.NOT_FOUND_TRANSACTION))
    }

    def "deleteTransactions: 店舗の全取引を削除"() {
        given:
        final shop = Spy(ShopModel)

        when:
        this.sut.deleteTransactions(shop.id, shop)

        then:
        1 * this.shopRepository.selectById(shop.id) >> Optional.of(shop)
        1 * this.transactionRepository.deleteByShopId(shop.id)
    }

    def "deleteTransactions: 店舗が存在しない場合は404エラー"() {
        given:
        final shop = Spy(ShopModel)

        when:
        this.sut.deleteTransactions(shop.id, shop)

        then:
        1 * this.shopRepository.selectById(shop.id) >> Optional.empty()
        final BaseException exception = thrown()
        verifyException(exception, new NotFoundException(ErrorCode.NOT_FOUND_SHOP))
    }

    def "deleteTransactions: ログイン店舗以外の場合は403エラー"() {
        given:
        final shop = Spy(ShopModel)

        when:
        this.sut.deleteTransactions(1, shop)

        then:
        1 * this.shopRepository.selectById(1) >> Optional.of(shop)
        1 * shop.id >> 2
        final BaseException exception = thrown()
        verifyException(exception, new ForbiddenException(ErrorCode.USER_HAS_NO_PERMISSION))
    }

}
