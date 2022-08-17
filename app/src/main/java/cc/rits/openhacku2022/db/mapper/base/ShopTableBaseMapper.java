package cc.rits.openhacku2022.db.mapper.base;

import cc.rits.openhacku2022.db.entity.ShopTable;
import cc.rits.openhacku2022.db.entity.ShopTableExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

@Mapper
public interface ShopTableBaseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_table
     *
     * @mbg.generated
     */
    long countByExample(ShopTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_table
     *
     * @mbg.generated
     */
    int deleteByExample(ShopTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_table
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_table
     *
     * @mbg.generated
     */
    int insert(ShopTable row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_table
     *
     * @mbg.generated
     */
    int insertSelective(ShopTable row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_table
     *
     * @mbg.generated
     */
    List<ShopTable> selectByExampleWithRowbounds(ShopTableExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_table
     *
     * @mbg.generated
     */
    List<ShopTable> selectByExample(ShopTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_table
     *
     * @mbg.generated
     */
    ShopTable selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_table
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("row") ShopTable row, @Param("example") ShopTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_table
     *
     * @mbg.generated
     */
    int updateByExample(@Param("row") ShopTable row, @Param("example") ShopTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_table
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ShopTable row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shop_table
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ShopTable row);
}