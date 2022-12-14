package cc.rits.openhacku2022.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.shop_id
     *
     * @mbg.generated
     */
    private Integer shopId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.price
     *
     * @mbg.generated
     */
    private Integer price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.image_url
     *
     * @mbg.generated
     */
    private String imageUrl;
}