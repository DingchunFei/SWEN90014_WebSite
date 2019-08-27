package com.fei.mapper;


import com.fei.domain.Favourite;
import com.fei.domain.ResultList;
import com.fei.domain.User;
import com.fei.domain.WebApp;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 功能描述：访问数据库的接口
 */

public interface FavouriteMapper {

	/**
	 * 根据web_app_id查看由该web_app下的所有测试信息
	 */
	@Select("SELECT * FROM t_favourite WHERE user_id = #{user_id}")
	@Results({
			@Result(column = "id",property = "id"),
			@Result(column = "flag",property = "flag"),
			@Result(column = "user_id",property = "user_id"),
			@Result(column = "web_app_id",property = "web_app_id"),
			@Result(property = "webApp", column = "web_app_id",
					one = @One(select = "com.fei.mapper.WebAppMapper.findWebAppByWebAppId"))
	})
	List<Favourite> findFavouriteByUserId(String user_id);


	/**
	 * 暂时没有用
	 */
	@Select("SELECT * FROM t_favourite WHERE web_app_id = #{web_app_id}")
	@Results({
			@Result(column = "id",property = "id"),
			@Result(column = "flag",property = "flag"),
			@Result(column = "user_id",property = "user_id"),
			@Result(column = "web_app_id",property = "web_app_id"),
/*			@Result(property = "user", column = "user_id",
					one = @One(select = "com.fei.mapper.UserMapper.getUserInfoWithoutList"))*/
	})
	List<Favourite> findFavouriteByWebAppIdWithoutWebAppId_UserInfo(String web_app_id);



	/**
	 * 根据web_app_id查看该web_app下的所有practitioner使用者.只需要拿到 user_id 和 email即可
	 */
	@Select("SELECT id, user_id FROM t_favourite WHERE web_app_id = #{web_app_id}")
	@Results({
			@Result(column = "id",property = "id"),
			@Result(column = "user_id",property = "user_id"),
			@Result(column = "web_app_id",property = "web_app_id"),
			@Result(property = "user", column = "user_id",
					one = @One(select = "com.fei.mapper.UserMapper.findPractitionerByUserId"))
	})
	List<Favourite> findPractitionersByWebAppId(String web_app_id);



	/**
	 * 查询practitoners的数目
	 */
	@Select("SELECT count(*) FROM t_user ,t_favourite WHERE web_app_id = #{web_app_id} AND t_user.id = t_favourite.user_id AND t_user.status = 0")
	Integer findPractitionersNumByWebAppId(String web_app_id);


	/**
	 * 插入web_app的时候插入web_favourite
	 */
	@SelectKey(keyProperty = "id",resultType = String.class, before = true,
			statement = "select uuid()")
	@Options(useGeneratedKeys=true, keyProperty="id")   //keyProperty java对象的属性；keyColumn表示数据库的字段
	@Insert("INSERT INTO t_favourite(id,user_id,web_app_id) " +
			"VALUES(#{id},#{user_id},#{web_app_id})")
	Integer insertWebFavouriteByNewWebApp (Favourite favourite);


	@Delete("DELETE FROM t_favourite WHERE web_app_id =#{webApp_id}")
	Integer deleteFavouriteByWebAppId(String webApp_id);

	@Delete("DELETE FROM t_favourite WHERE id =#{id}")
	Integer deleteFavouriteByFavouriteId (String id);

	@Select("SELECT id, user_id FROM t_favourite WHERE web_app_id = #{web_app_id}")
	@Results({
			@Result(column = "id",property = "id"),
			@Result(column = "user_id",property = "user_id"),
			@Result(column = "web_app_id",property = "web_app_id"),
	})
	List<Favourite> findFavouritesByWebAppId(String webApp_id);

	/**
	 * 更新favourite的flag
	 */
	@Update("UPDATE t_favourite SET flag=#{flag} WHERE id = #{id}")
	Integer updateFavourite (Favourite favourite);

	/**
	 * 检查favourite表中是否已存在该favourite
	 */
	@Select("SELECT count(*) FROM t_favourite WHERE user_id = #{user_id} AND web_app_id = #{web_app_id}")
	Integer checkAlreadyExist (String user_id,String web_app_id);

}