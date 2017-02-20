package com.mingsoft.weixin.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.weixin.entity.WeixinPeopleEntity;
/**
 * 
 * 微信用户持久层层接口
 * @author 付琛  QQ1658879747
 * @version 
 * 版本号：100-000-000<br/>
 * 创建日期：2015年11月17日
 * 历史修订：<br/>
 */
public interface IWeixinPeopleDao extends IBaseDao{
	
	/**
	 * 根据应用ID和微信ID查询微信用户列表（带分页）
	 * @param appId 应用id
	 * @param weixinId 微信ID
	 * @param pageNo 当前页
	 * @param pageSize 一页显示的条数
	 * @param orderBy 排序字段
	 * @param order 顺序or倒叙
	 * @return 微信用户列表
	 */
	public List<WeixinPeopleEntity> queryList(@Param("appId")int appId,@Param("weixinId") int weixinId,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize,@Param("orderBy")String orderBy,@Param("order") boolean order);
	
	
	/**
	 * 根据应用Id和微信ID查询微信用户列表(不带分页)
	 * @param appId 应用Id
	 * @param weixinId 微信ID
	 * @return 微信用户列表
	 */
	public List<WeixinPeopleEntity> queryListByAppIdAndWeixinId(@Param("appId")int appId,@Param("weixinId") int weixinId);
	
	
	/**
	 * 根据应用ID和微信id查找用户总数
	 * @param appId 应用ID
	 * @param weixinId 微信id
	 * @return 用户总数
	 */
	public int queryCount(@Param("appId")int appId,@Param("weixinId") int weixinId);
	
	/**
	 * 根据自定义字段查找微信用户实体
	 * @param whereMap 查询条件
	 * @return 用户实体
	 */
	public WeixinPeopleEntity getEntity(@Param("whereMap")Map<String,Object> whereMap);
	
	/**
	 * 根据具体字段查询微信用户实体</br>
	 * @param peopleId 用户ID
	 * @param appId 应用Id
	 * @param weixinId 关联的微信Id
	 * @param openId 微信对用户的唯一标识
	 * @return 微信用户实体
	 */
	public WeixinPeopleEntity getWeixinPeopleEntity(@Param("peopleId") Integer peopleId,@Param("appId") Integer appId,@Param("weixinId") Integer weixinId,@Param("openId") String openId);
	
}
