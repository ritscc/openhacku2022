package cc.rits.openhacku2022.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transaction.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transaction.shop_id
     *
     * @mbg.generated
     */
    private Integer shopId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transaction.table_id
     *
     * @mbg.generated
     */
    private Integer tableId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transaction.code
     *
     * @mbg.generated
     */
    private String code;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column transaction.number_of_people
     *
     * @mbg.generated
     */
    private Integer numberOfPeople;
}