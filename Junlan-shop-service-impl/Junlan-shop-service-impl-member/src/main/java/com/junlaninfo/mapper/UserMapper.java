package com.junlaninfo.mapper;

import org.springframework.data.repository.query.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;


import com.junlaninfo.UserEntity;
import com.junlaninfo.DO.UserDo;

public interface UserMapper {
	/*
	 * 注册会员
	 */
	@Insert("INSERT INTO `junlan_user` VALUES (null,#{mobile}, #{email}, #{password}, #{userName}, null, null, null, '1', null, null, null);")
	int  insertUser(UserEntity userEntity);
	
	/*
	 * 根据手机号码查询会员是否存在
	 */
	@Select("SELECT * FROM junlan_user WHERE MOBILE=#{mobile};")
	UserEntity existMobile(@Param("mobile") String mobile);
	
	/*
	 * 会员登陆
	 */
	@Select("SELECT USER_ID AS USERID ,MOBILE AS MOBILE,EMAIL AS EMAIL,PASSWORD AS PASSWORD, USER_NAME AS USER_NAME ,SEX AS SEX ,AGE AS AGE ,CREATE_TIME AS CREATETIME,IS_AVALIBLE AS ISAVALIBLE,PIC_IMG AS PICIMG,QQ_OPENID AS QQOPENID,WX_OPENID AS WXOPENID "
			+ "  FROM junlan_user  WHERE  mobile=#{0};")
	UserDo login( @Param("mobile") String mobile);
	
	
	@Select("SELECT USER_ID AS USERID ,MOBILE AS MOBILE,EMAIL AS EMAIL,PASSWORD AS PASSWORD, USER_NAME AS USER_NAME ,SEX AS SEX ,AGE AS AGE ,CREATE_TIME AS CREATETIME,IS_AVALIBLE AS ISAVALIBLE,PIC_IMG AS PICIMG,QQ_OPENID AS QQOPENID,WX_OPENID AS WXOPENID"
			+ " FROM junlan_user WHERE user_Id=#{userId}")
	UserDo findByUserId(@Param("userId") Long userId);
	
	 
}
