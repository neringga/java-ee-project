package mybatis.dao;

import java.util.List;
import org.mybatis.cdi.Mapper;
import mybatis.model.Course;

@Mapper
public interface CourseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.COURSE
     *
     * @mbg.generated Sun Mar 29 17:36:45 EEST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.COURSE
     *
     * @mbg.generated Sun Mar 29 17:36:45 EEST 2020
     */
    int insert(Course record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.COURSE
     *
     * @mbg.generated Sun Mar 29 17:36:45 EEST 2020
     */
    Course selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.COURSE
     *
     * @mbg.generated Sun Mar 29 17:36:45 EEST 2020
     */
    List<Course> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.COURSE
     *
     * @mbg.generated Sun Mar 29 17:36:45 EEST 2020
     */
    int updateByPrimaryKey(Course record);
}