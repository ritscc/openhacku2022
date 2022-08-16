package cc.rits.openhacku2022.util

import cc.rits.openhacku2022.BaseSpecification
import cc.rits.openhacku2022.exception.BadRequestException
import cc.rits.openhacku2022.exception.BaseException
import cc.rits.openhacku2022.exception.ErrorCode
import cc.rits.openhacku2022.helper.RandomHelper
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder

/**
 * AuthUtilの単体テスト
 */
class AuthUtil_UT extends BaseSpecification {

    @Autowired
    AuthUtil sut

    @SpringBean
    PasswordEncoder passwordEncoder = Mock()

    def "hashingPassword: パスワードをハッシュ化する"() {
        given:
        final password = RandomHelper.alphanumeric(12)
        final hash = RandomHelper.alphanumeric(80)

        when:
        final result = this.sut.hashingPassword(password)

        then:
        result == hash
        1 * this.passwordEncoder.encode(password) >> hash
    }

    def "isMatchPasswordAndHash: パスワードとハッシュ値が一致するか確認"() {
        when:
        def result = this.sut.isMatchPasswordAndHash(password, hash)

        then:
        result == expetedResult
        1 * this.passwordEncoder.matches(password, hash) >> expetedResult

        where:
        password                      | hash                          | expetedResult
        RandomHelper.alphanumeric(12) | RandomHelper.alphanumeric(80) | true
        RandomHelper.alphanumeric(12) | RandomHelper.alphanumeric(80) | false
    }

    def "checkIsPasswordValid: 有効なパスワードの場合は例外をスローしない"() {
        when:
        AuthUtil.checkIsPasswordValid(password)

        then:
        noExceptionThrown()

        where:
        password << [
            // 8文字
            "b9Fj5QYP",
            // 32文字
            "b9Fj5QYPf6scACPW5QQ4C7vfQyNp5EtF"
        ]
    }

    def "checkIsPasswordValid: パスワードが無効なら400エラー"() {
        when:
        AuthUtil.checkIsPasswordValid(password)

        then:
        final BaseException exception = thrown()
        verifyException(exception, expectedException)

        where:
        password    | expectedException
        // 8文字未満
        "b9Fj5QY"   | new BadRequestException(ErrorCode.INVALID_PASSWORD_LENGTH)
        // 32文字より多い
        "." * 33    | new BadRequestException(ErrorCode.INVALID_PASSWORD_LENGTH)
        // 大文字・小文字・数字のいずれかが欠如している
        "bFjQYVPg"  | new BadRequestException(ErrorCode.PASSWORD_IS_TOO_SIMPLE)
        "b9fj5qyv"  | new BadRequestException(ErrorCode.PASSWORD_IS_TOO_SIMPLE)
        "B9FJ5QYVP" | new BadRequestException(ErrorCode.PASSWORD_IS_TOO_SIMPLE)
    }

    def "isEmailValid: メールアドレスの有効性をチェック"() {
        expect:
        AuthUtil.isEmailValid(email) == expectedResult

        where:
        // @formatter:off
        email                                         | expectedResult
        RandomHelper.email()                          | true
        "email@example.com"                           | true
        "firstname.lastname@example.com"              | true
        "email@subdomain.example.com"                 | true
        "firstname+lastname@example.com"              | true
        "\"email\"@example.com"                       | true
        "1234567890@example.com"                      | true
        "email@example-one.com"                       | true
        "_______@example.com"                         | true
        "email@example.name"                          | true
        "email@example.museum"                        | true
        "email@example.co.jp"                         | true
        "firstname-lastname@example.com"              | true
        null                                          | false
        "#@%^%#\$@#\$@#.com"                          | false
        "@example.com"                                | false
        "Joe Smith <email@example.com>"               | false
        "email.example.com"                           | false
        "email@example@example.com"                   | false
        ".email@example.com"                          | false
        "email.@example.com"                          | false
        "email..email@example.com"                    | false
        "email@example.com (Joe Smith)"               | false
        "email@example"                               | false
        "email@-example.com"                          | false
        "email@example.web"                           | false
        "email@111.222.333.44444"                     | false
        "email@example..com"                          | false
        "Abc..123@example.com"                        | false
        "()|false:;<>[\\]@example.com"                | false
        "this\\ is\"really\"not\\allowed@example.com" | false
        // @formatter:on
    }

}