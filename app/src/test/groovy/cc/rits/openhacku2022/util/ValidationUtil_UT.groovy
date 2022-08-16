package cc.rits.openhacku2022.util

import cc.rits.openhacku2022.BaseSpecification
import cc.rits.openhacku2022.helper.DateHelper
import cc.rits.openhacku2022.helper.RandomHelper

/**
 * ValidationUtilの単体テスト
 */
class ValidationUtil_UT extends BaseSpecification {

    def "checkNumberSize: 数値が範囲に収まるかチェック"() {
        expect:
        ValidationUtil.checkNumberSize(number, min, max) == expectedResult

        where:
        number | min  | max || expectedResult
        10     | 0    | 20  || true
        10     | 10   | 20  || true
        10     | 0    | 10  || true
        10     | 11   | 20  || false
        10     | 0    | 9   || false
        null   | 10   | 20  || false
        10     | null | 20  || true
        10     | null | 9   || false
        10     | 0    | null | true
        10     | 11   | null | false
    }

    def "checkStringLength: 文字列の長さが範囲に収まるかチェック"() {
        expect:
        ValidationUtil.checkStringLength(string, min, max) == expectedResult

        where:
        string                        | min  | max  || expectedResult
        RandomHelper.alphanumeric(10) | 0    | 20   || true
        RandomHelper.alphanumeric(10) | 10   | 20   || true
        RandomHelper.alphanumeric(10) | 0    | 10   || true
        RandomHelper.alphanumeric(10) | 11   | 10   || false
        RandomHelper.alphanumeric(10) | 0    | 9    || false
        null                          | 0    | 10   || false
        RandomHelper.alphanumeric(10) | null | 10   || true
        RandomHelper.alphanumeric(10) | null | 9    || false
        RandomHelper.alphanumeric(10) | 0    | null || true
        RandomHelper.alphanumeric(10) | 11   | null || false
    }

    def "checkIsAlphanumeric: 英数字のみの文字列かチェック"() {
        expect:
        ValidationUtil.checkIsAlphanumeric(string) == expectedResult

        where:
        string                              || expectedResult
        null                                || false
        ""                                  || true
        " "                                 || false
        RandomHelper.alphanumeric(10)       || true
        RandomHelper.alphanumeric(10) + "_" || false
    }

    def "checkIsFuture: 未来の日付かチェック"() {
        expect:
        ValidationUtil.checkIsFuture(date) == expectedResult

        where:
        date                   || expectedResult
        DateHelper.yesterday() || false
        DateHelper.today()     || false
        DateHelper.tomorrow()  || true
    }

}