
1： **API  json多层数据实体类封装（利用泛型技术）**

  ![image](https://github.com/Arisono/ApplicationExample/blob/master/images/image_json_00.png)
  
  ![image](https://github.com/Arisono/ApplicationExample/blob/master/images/image_json_01.png)
        
   RootEntity.java
   
    public class RootEntity<T>{
	
    private int showapi_res_code;
    private String showapi_res_error;
    private PageBeanBody<T> showapi_res_body;
    //省略get set方法
    }

 PageBeanBody.java
 
    public class PageBeanBody<T> {
    
        private PageBean<T> pagebean;
        private int ret_code;
         //省略get set方法
    }

PageBean.java
        
    public class PageBean<T> {
	
    private int allNum; 
    private int allPages;
    private int currentPage;
    private int maxResult;
    private List<T> contentlist;
    //省略get set方法
    }


泛型方法

     public static <T> RootEntity<T> parseNewsList(Response response,Class<T> mClazz) {
    		JSONObject root=JSON.parseObject(response.getResponseText());
    		JSONObject body=JSON.parseObject(response.getResponseText()).getJSONObject("showapi_res_body");
    		JSONObject page=JSON.parseObject(response.getResponseText()).getJSONObject("showapi_res_body").getJSONObject("pagebean");
    	    String contentList=JSON.parseObject(response.getResponseText()).getJSONObject("showapi_res_body").getJSONObject("pagebean").getJSONArray("contentlist").toJSONString();
    	    //实例化分页类
    	    PageBean<T> pageBean=new PageBean<T>();
    	    pageBean.setContentlist(JSON.parseArray(contentList, mClazz));//可能报错
    	    pageBean.setAllNum(page.getIntValue("allNum"));
    	    pageBean.setAllPages(page.getIntValue("allPages"));
    	    pageBean.setCurrentPage(page.getIntValue("currentPage"));
    	    pageBean.setMaxResult(page.getIntValue("maxResult"));
    	    //实例化Body类
    	    PageBeanBody<T> pageBeanBody=new PageBeanBody<T>();
    	    pageBeanBody.setPagebean(pageBean);
    	    pageBeanBody.setRet_code(body.getIntValue("ret_code"));
    	    //实例化Root类
    	    RootEntity<T> rootEntity=new RootEntity<T>();
    	    rootEntity.setShowapi_res_body(pageBeanBody);
    	    rootEntity.setShowapi_res_code(root.getIntValue("showapi_res_code"));
    	    rootEntity.setShowapi_res_error(root.getString("showapi_res_error"));
    	    return rootEntity;
    	}

调用泛型方法

    public static void loadNewsList(String url, Map<String, Object> param, LinkedHashMap<String, Object> headers,
    			String bodyJson, String post) {
    		Response response = StaticUtil.commomHttpMethod(url, param, headers, bodyJson, post);
    		//调用泛型方法 
    		RootEntity<NewEntity> data=	parseNewsList(response, NewEntity.class);
    		System.out.println("desc:"+data.getShowapi_res_body().getPagebean().getContentlist().get(0).getImageurls().size());
    		System.out.println("code:"+data.getShowapi_res_code());
    		System.out.println("error:"+data.getShowapi_res_error());
    		System.out.println("allnum:"+data.getShowapi_res_body().getPagebean().getAllNum());
    		System.out.println("allpages:"+data.getShowapi_res_body().getPagebean().getAllPages());
    		System.out.println("currentpages:"+data.getShowapi_res_body().getPagebean().getCurrentPage());
    		System.out.println("maxresult:"+data.getShowapi_res_body().getPagebean().getMaxResult());
    	}