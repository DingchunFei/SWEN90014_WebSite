package com.fei.mapper;


import com.fei.domain.User;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 功能描述：访问数据库的接口
 */
public interface UserMapper {

	/**
	 * 查看所有用户
	 */
	@Select("SELECT * FROM t_user")
	@Results({
			@Result(column = "id",property = "id"),
			@Result(column = "username",property = "username"),
			@Result(column = "password",property = "password"),
			@Result(column = "email",property = "email"),
			@Result(column = "status",property = "status"),
			@Result(column = "full_name",property = "full_name"),
			@Result(column = "instituion",property = "instituion"),
			@Result(column = "gender",property = "gender"),
            @Result(column = "img_src",property = "img_src")
	})
	List<User> getAllUser();

	/**
	 * 用户登录
	 * 根据username与password，获得当前用户所有数据->包括 Lis<WebApp>
	 * SELECT * FROM t_user u LEFT JOIN t_web_app w ON u.`id`=w.`user_id`
	 */
	@Select("SELECT * FROM t_user WHERE email=#{email} and password=#{password}")
	@Results({
			@Result(column = "id",property = "id"),
			@Result(column = "username",property = "username"),
			@Result(column = "password",property = "password"),
			@Result(column = "email",property = "email"),
			@Result(column = "status",property = "status"),
			@Result(column = "full_name",property = "full_name"),
			@Result(column = "instituion",property = "instituion"),
			@Result(column = "gender",property = "gender"),
            @Result(column = "img_src",property = "img_src"),
			@Result(property = "favourites" , column="id",
				many = @Many(select ="com.fei.mapper.FavouriteMapper.findFavouriteByUserId")),

	})
	User getUserInfo(String email, String password);


	/**
	 * 用来辅助favourite里找出practitioner使用者.只需要拿到 user_id 和 email即可 ,practitioner的status为0
	 */
	@Select("SELECT id , email FROM t_user WHERE id = #{user_id} AND status=0")
	@Results({
			@Result(column = "id",property = "id"),
			@Result(column = "email",property = "email"),
	})
	User findPractitionerByUserId(String user_id);



	@Select("SELECT * FROM t_user WHERE id=#{id}")
	@Results({
			@Result(column = "id",property = "id"),
			@Result(column = "username",property = "username"),
			@Result(column = "password",property = "password"),
			@Result(column = "email",property = "email"),
			@Result(column = "status",property = "status"),
			@Result(column = "full_name",property = "full_name"),
			@Result(column = "instituion",property = "instituion"),
			@Result(column = "gender",property = "gender"),
            @Result(column = "img_src",property = "img_src"),
			@Result(property = "favourites" , column="id",
					many = @Many(select ="com.fei.mapper.FavouriteMapper.findFavouriteByUserId")),
	})
	User refreshUserInfo(String id);

	/**
	 * 不返回favourites List
	 * SELECT * FROM t_user u LEFT JOIN t_web_app w ON u.`id`=w.`user_id`
	 */
	@Select("SELECT * FROM t_user WHERE id=#{user_id}")
	@Results({
			@Result(column = "id",property = "id"),
			@Result(column = "username",property = "username"),
			@Result(column = "password",property = "password"),
			@Result(column = "email",property = "email"),
			@Result(column = "status",property = "status"),
			@Result(column = "full_name",property = "full_name"),
			@Result(column = "instituion",property = "instituion"),
			@Result(column = "gender",property = "gender"),
            @Result(column = "img_src",property = "img_src"),
	})
	User getUserInfoWithoutFavourites(String user_id);


	@Select("SELECT COUNT(*) FROM `t_user` WHERE email=#{email}")
	Integer checkNewUserByEmail(String email);

	/**
	 * 插入新用户
	 */
	@SelectKey(keyProperty = "id",resultType = String.class, before = true,
			statement = "select uuid()")
	@Options(useGeneratedKeys=true, keyProperty="id")   //keyProperty java对象的属性；keyColumn表示数据库的字段
	@Insert("INSERT INTO t_user(id,username,password,gender,email,status,full_name,institution)" +
			"VALUES(#{id},#{username}, #{password}, #{gender},#{email},#{status},#{full_name},#{institution})")
	Integer insertUser(User user);

	/**
	 * 根据ID删除用户
	 */
	@Delete("DELETE FROM t_user WHERE id =#{id}")
	int deleteUserById(String id);

	/**
	 * 根据ID修改用户信息
	 */
	@Update("UPDATE t_user SET password=#{password}, gender=#{gender}, email=#{email}, " +
			"full_name=#{full_name}, institution=#{institution} WHERE id =#{id}")
	int updateUserById(User user);


	/**
	 * 暂时只用来更新用户的头像地址
	 */
	@Update("UPDATE t_user SET img_src=#{img_src} WHERE id =#{id}")
	int updateUser(User user);


	/**
	 * 注册账户
	 * 查看Email是否已经被占用
	 */
	@Select("SELECT COUNT(*) FROM `t_user` WHERE email=#{email}")
	Long checkEmail(String email);

	/**
	 * 输入邮箱，返回对应的用户id
	 */
	@Select("SELECT id FROM t_user WHERE email = #{email}")
	String findUserIdByEmail(String email);
}