package com.junlaninfo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.repository.query.Param;

import com.junlaninfo.DO.UserTokenDo;

/** 

* @author 作者 xuexionghui: 
   @emil:41366915@qq.com
* @version 创建时间：2019年1月27日 下午10:56:44 

* 类说明    将生成的token存储到数据库中

*/ 

public interface UserTokenMapper {
   
	@Insert("insert into junlan_user_token values (null,#{token},#{loginType},#{deviceInfor},0,#{userId})")
	int  insertLoginToken(UserTokenDo userToken);
	
	@Select("SELECT id as id ,token as token ,login_type as LoginType, device_infor as deviceInfor ,is_availability as isAvailability,user_id as userId   FROM junlan_user_token WHERE user_id=#{userId}  and is_availability ='0'; ")
	UserTokenDo selectByUserIdAndLoginType(@Param("userId") Long userId);

	@Update("    update junlan_user_token set is_availability ='1'  where user_id=#{userId} and is_availability ='0' ")
	int updateTokenAvailability(@Param("userId") Long userId);
}

