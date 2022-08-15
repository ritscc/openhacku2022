package cc.rits.openhacku2022.helper

import groovy.sql.Sql
import cc.rits.openhacku2022.helper.table.TableParser

/**
 * DBテーブルのヘルパー
 */
class TableHelper {

    /**
     * データを挿入
     *
     * @param sql sql
     * @param tableName テーブル名
     * @param tableData テーブルデータ
     */
    def static insert(final Sql sql, final String tableName, final Closure rows) {
        TableParser.asTable(rows).toMapList().each {
            sql.dataSet("`$tableName`").add(it)
        }
    }

    /**
     * データを挿入
     * 全レコードで共通の値を指定
     *
     * @param sql sql
     * @param tableName テーブル名
     * @param tableData テーブルデータ
     * @param append 全レコードで共通の値
     */
    def static insert(final Sql sql, final String tableName, final Closure rows, final Map append) {
        TableParser.asTable(rows).toMapList().each {
            sql.dataSet("`$tableName`").add(it + append)
        }
    }

}
