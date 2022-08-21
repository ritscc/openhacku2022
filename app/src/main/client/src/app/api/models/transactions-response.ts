/* tslint:disable */
/* eslint-disable */
import { TransactionResponse } from "./transaction-response";

/**
 * 取引リストレスポンス
 */
export interface TransactionsResponse {
    /**
     * 取引リスト
     */
    transactions: Array<TransactionResponse>;
}
