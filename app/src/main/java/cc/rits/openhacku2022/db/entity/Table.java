package cc.rits.openhacku2022.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Table {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table.shop_id
     *
     * @mbg.generated
     */
    private Integer shopId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column table.capacity
     *
     * @mbg.generated
     */
    private Integer capacity;
}