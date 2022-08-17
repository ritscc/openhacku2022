/**
 * アラートモデル
 */
export type AlertModel = {
    /**
     * ステータスコード
     */
    statusCode: number;

    /**
     * メッセージ
     */
    message: string;

    /**
     * レベル
     */
    level: "SUCCESS" | "INFO" | "WARN" | "ERROR";
};
