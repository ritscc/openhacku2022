package cc.rits.openhacku2022.helper

/**
 * 日時ヘルパー
 */
class DateHelper {

    /**
     * 日時を作成
     *
     * @param year 年
     * @param month 月
     * @param day 日
     * @return 日時
     */
    static Date build(final Integer year, final Integer month, final Integer day) {
        final calendar = Calendar.getInstance()
        calendar.set(year, month - 1, day, 0, 0, 0)
        return calendar.getTime()
    }

    /**
     * 明日の日付を取得
     *
     * @return 明日の日付
     */
    static Date tomorrow() {
        final now = new Date()
        final calendar = Calendar.getInstance()
        calendar.setTime(now)
        calendar.add(Calendar.DATE, 1)
        return calendar.getTime()
    }

    /**
     * 今日の日付を取得
     *
     * @return 今日の日付
     */
    static Date today() {
        final now = new Date()
        final calendar = Calendar.getInstance()
        calendar.setTime(now)
        return calendar.getTime()
    }

    /**
     * 昨日の日付を取得
     *
     * @return 昨日の日付
     */
    static Date yesterday() {
        final now = new Date()
        final calendar = Calendar.getInstance()
        calendar.setTime(now)
        calendar.add(Calendar.DATE, -1)
        return calendar.getTime()
    }

}
