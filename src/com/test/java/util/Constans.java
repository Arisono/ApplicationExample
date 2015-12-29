package com.test.java.util;

/**
 * @author :LiuJie 2015年11月3日 下午5:33:02
 * @注释:全局接口
 */
public interface Constans {
	
	 /**@注释：erp 根路径  */
	 public static String ERP_BASIC="http://218.17.158.219:8090/ERP/";
     //public static String ERP_BASIC="http://218.18.115.198:8081/ERP/";
	 public static String ERP_BASIC_YINTANG="http://218.18.115.198:8888/ERP/";
	 /**@注释：erp测试 根路径  */
	 public static String ERP_BASIC_TEST="http://192.168.253.158:8088/ERP/";
	
	
	 /**@注释：erp 新闻列表  */
	 public static String ERP_NEWS=ERP_BASIC_YINTANG+"common/datalist/data.action";
	 /**@注释：erp 新闻列表详细  */
	 public static String ERP_NEWS_DETAIL=ERP_BASIC+"/oa/news/getNews.action";
	 /**@注释：通知详细  */
	 public static String ERP_NOTICES_DETAIL=ERP_BASIC+"/oa/note/getNote.action";
	 /**@注释：下载  */
	 public static String ERP_DOWNLOAD_ID=ERP_BASIC+"/common/downloadbyId.action";
	
	 public static String ERP_GETALLHRORGEMPS=ERP_BASIC+"mobile/getAllHrorgEmps.action";
	 /**@注释：erp 登录  */
	 public static String ERP_LOGIN=ERP_BASIC+"mobile/login.action";
	
	 /**@注释：新闻中心  */
	 public static String API_NEWS="http://route.showapi.com/109-35";
	 /**@注释：新闻_频道 */
	 public final static String API_NEWS_CHANNEL="http://route.showapi.com/109-34";
	 /**@注释：身份证  */
     public static String API_IDSERVICE="http://apis.baidu.com/apistore/idservice/id?id=";
     /**@注释：获取微信精选  */
     public static String API_WXHOT="http://apis.baidu.com/txapi/weixin/wxhot";
     /**@注释：获取微信精选 HUCEO */
     public static String API_WXHOT_HUCEO="http://api.huceo.com/wxnew/other/";
     /**@注释：天狗图片  */
     public static String API_TIANGOU_GALLERY="http://apis.baidu.com/tngou/gallery/news";
     /**@注释：音乐接口  */
     public static String API_MUSIC="http://apis.baidu.com/geekery/music/query";
     /**@注释：视频接口  */
     public static String API_VIDEO="http://apis.baidu.com/baidu_openkg/shipin_kg/shipin_kg";
}
