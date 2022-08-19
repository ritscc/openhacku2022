package cc.rits.openhacku2022.model

import cc.rits.openhacku2022.BaseSpecification

/**
 * TableModelの単体テスト
 */
class TableModel_UT extends BaseSpecification {

    def "available: テーブルが利用可能かチェック"() {
        given:
        final table = TableModel.builder()
            .isUsed(isUsed)
            .capacity(capacity)
            .build()

        when:
        final result = table.available(numberOfPeople)

        then:
        result == expectedResult

        where:
        isUsed | capacity | numberOfPeople || expectedResult
        true   | 4        | 4              || false
        false  | 4        | 4              || true
        false  | 4        | 5              || false
    }

}
