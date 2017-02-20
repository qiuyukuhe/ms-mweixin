package com.mingsoft.weixin.biz;

import java.util.List;
import com.mingsoft.base.biz.IBaseBiz;
import com.mingsoft.util.PageUtil;
import com.mingsoft.weixin.entity.WeixinQrcodeEntity;

/** 
 * 微信二维码业务层接口
 * @author  付琛  QQ:1658879747 
 * @version 1.0 
 * 创建时间：2015年11月13日 下午3:59:21  
 * 版本号：100-000-000<br/>
 * 历史修订<br/>
 */
public interface IWeixinQrcodeBiz extends IBaseBiz{

	/**
	 * 根据应用ID和微信ID查询二维码总数
	 * @param appId 应用ID
	 * @param WeixinId 微信ID
	 * @return 二维码总数
	 */
	public int queryCount(int appId,int WeixinId);
	
	/**
	 * 批量删除
	 * @param ids 需要删除的二维码ID集合
	 */
	public void deleteByIds(int[] ids);
	
	/**
	 * 分页带查询
	 * @param appId 应用ID
	 * @param weixinId 微信ID
	 * @param page 分页对象
	 * @return 微信二维码集合
	 */
	public List<WeixinQrcodeEntity> queryList(int appId,int weixinId,PageUtil page);
}
