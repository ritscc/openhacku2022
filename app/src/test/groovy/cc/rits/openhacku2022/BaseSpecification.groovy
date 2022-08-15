package cc.rits.openhacku2022

import groovy.sql.Sql
import cc.rits.openhacku2022.exception.BaseException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

import javax.sql.DataSource

/**
 * テストの基底クラス
 */
@SpringBootTest
@Transactional
abstract class BaseSpecification extends Specification {

    /**
     * SQL Handler
     */
    protected static Sql sql

    @Autowired
    private dataSource(final DataSource dataSource) {
        if (Objects.isNull(sql)) {
            sql = new Sql(new TransactionAwareDataSourceProxy(dataSource))
        }
    }

    /**
     * cleanup after test case
     */
    def cleanup() {
        // DBを初期化するために、テスト開始前にロールバック
        sql.rollback()
    }

    /**
     * 例外を検証
     *
     * @param thrownException 発生した例外
     * @param expectedException 期待する例外
     */
    static void verifyException(final BaseException thrownException, final BaseException expectedException) {
        assert thrownException.httpStatus == expectedException.httpStatus
        assert thrownException.errorCode == expectedException.errorCode
    }

}
