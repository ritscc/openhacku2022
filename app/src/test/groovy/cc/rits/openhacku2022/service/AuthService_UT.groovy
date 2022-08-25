package cc.rits.openhacku2022.service

import cc.rits.openhacku2022.BaseSpecification
import cc.rits.openhacku2022.api.request.LoginRequest
import cc.rits.openhacku2022.exception.BadRequestException
import cc.rits.openhacku2022.exception.BaseException
import cc.rits.openhacku2022.exception.ErrorCode
import cc.rits.openhacku2022.exception.NotFoundException
import cc.rits.openhacku2022.helper.RandomHelper
import cc.rits.openhacku2022.model.ShopModel
import cc.rits.openhacku2022.model.TableModel
import cc.rits.openhacku2022.model.TransactionModel
import cc.rits.openhacku2022.repository.ShopRepository
import cc.rits.openhacku2022.repository.TableRepository
import cc.rits.openhacku2022.repository.TransactionRepository
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

import static org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME

/**
 * AuthServiceの単体テスト
 */
class AuthService_UT extends BaseSpecification {

    @Autowired
    AuthService sut

    @SpringBean
    ShopRepository shopRepository = Mock()

    @SpringBean
    TableRepository tableRepository = Mock()

    @SpringBean
    TransactionRepository transactionRepository = Mock()

    @SpringBean
    HttpSession httpSession = Mock()

    @SpringBean
    HttpServletRequest httpServletRequest = Mock()

    def "login: ログインに成功すると、取引が開始されセッションに記録される"() {
        given:
        final shop = RandomHelper.mock(ShopModel)
        final table = Spy(TableModel)

        final requestBody = RandomHelper.mock(LoginRequest)

        when:
        this.sut.login(requestBody)

        then:
        1 * this.shopRepository.selectById(requestBody.shopId) >> Optional.of(shop)
        1 * this.tableRepository.selectByShopId(shop.id) >> [table]
        table.available(requestBody.numberOfPeople) >> true
        1 * this.transactionRepository.insert(_)
        this.httpSession.setAttribute(PRINCIPAL_NAME_INDEX_NAME, _);
        1 * this.httpServletRequest.changeSessionId()
    }

    def "login: 店舗が存在しない場合は404エラー"() {
        given:
        final table = Spy(TableModel)

        final requestBody = RandomHelper.mock(LoginRequest)

        when:
        this.sut.login(requestBody)

        then:
        1 * this.shopRepository.selectById(requestBody.shopId) >> Optional.empty()
        final BaseException exception = thrown()
        verifyException(exception, new NotFoundException(ErrorCode.NOT_FOUND_SHOP))
    }

    def "login: 座れるテーブルが存在しない場合は404エラー"() {
        given:
        final shop = RandomHelper.mock(ShopModel)
        final table = Spy(TableModel)

        final requestBody = RandomHelper.mock(LoginRequest)

        when:
        this.sut.login(requestBody)

        then:
        1 * this.shopRepository.selectById(requestBody.shopId) >> Optional.of(shop)
        1 * this.tableRepository.selectByShopId(shop.id) >> [table]
        table.available(requestBody.numberOfPeople) >> false
        final BaseException exception = thrown()
        verifyException(exception, new BadRequestException(ErrorCode.ALL_TABLES_ARE_BOOKED))
    }

    def "logout: ログアウトすると取引が終了し、セッションが廃棄される"() {
        given:
        final transaction = RandomHelper.mock(TransactionModel)

        when:
        this.sut.logout(transaction)

        then:
        1 * this.transactionRepository.delete(transaction)
        1 * this.httpSession.invalidate()
    }

}
