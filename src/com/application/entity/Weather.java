package com.application.entity;

import java.util.ArrayList;
import java.util.List;


/**
 * @author :LiuJie 2015年11月2日 上午11:10:37
 * @注释:天气预报实体类
 * @技能提升：fastjson解析json对象，非常强大;
 *          它支持内部类实例化；关键是注意属性变量名和json串一致，对象可以不为一致；
 */
public class Weather {
	//空气质量，仅限国内部分城市，国际城市无此字段
	public aqi aqi;
	 //基本信息
	public basic basic;
	//7天天气预报
	public List<DailyForecast> daily_forecast=new ArrayList<DailyForecast>();
	//每三小时天气预报，全能版为每小时预 报
	public List<HourlyForecast> hourly_forecast =new ArrayList<HourlyForecast>();
	//实况天气
	public now now;
	//接口状态，参考http://www.heweather.com/documents/api 
	public String status;
	//生活指数，仅限国内城市，国际城市无此字段
	public suggestion suggestion;

	public class aqi {
		public city city;

		public void setCity(city city) {
			this.city = city;
		}

		public city getCity() {
			return this.city;
		}
		
		public class city {
			public String aqi;

			public String co;

			public String no2;

			public String o3;

			public String pm10;

			public String pm25;

			public String qlty;

			public String so2;

			public void setAqi(String aqi) {
				this.aqi = aqi;
			}

			public String getAqi() {
				return this.aqi;
			}

			public void setCo(String co) {
				this.co = co;
			}

			public String getCo() {
				return this.co;
			}

			public void setNo2(String no2) {
				this.no2 = no2;
			}

			public String getNo2() {
				return this.no2;
			}

			public void setO3(String o3) {
				this.o3 = o3;
			}

			public String getO3() {
				return this.o3;
			}

			public void setPm10(String pm10) {
				this.pm10 = pm10;
			}

			public String getPm10() {
				return this.pm10;
			}

			public void setPm25(String pm25) {
				this.pm25 = pm25;
			}

			public String getPm25() {
				return this.pm25;
			}

			public void setQlty(String qlty) {
				this.qlty = qlty;
			}

			public String getQlty() {
				return this.qlty;
			}

			public void setSo2(String so2) {
				this.so2 = so2;
			}

			public String getSo2() {
				return this.so2;
			}

		}
		

	}
	
	
	/**
	 * @author LiuJie
	 * @功能:基本信息
	 */
	public class basic {
		public String city;

		public String cnty;

		public String id;

		public String lat;

		public String lon;

		public update update;

		public void setCity(String city){
		this.city = city;
		}
		public String getCity(){
		return this.city;
		}
		public void setCnty(String cnty){
		this.cnty = cnty;
		}
		public String getCnty(){
		return this.cnty;
		}
		public void setId(String id){
		this.id = id;
		}
		public String getId(){
		return this.id;
		}
		public void setLat(String lat){
		this.lat = lat;
		}
		public String getLat(){
		return this.lat;
		}
		public void setLon(String lon){
		this.lon = lon;
		}
		public String getLon(){
		return this.lon;
		}
		public void setUpdate(update update){
		this.update = update;
		}
		public update getUpdate(){
		return this.update;
		}
		
		/**
		 * @author :LiuJie 2015年11月2日 上午11:29:10
		 * @注释:更新時間
		 */
		public class update {
			public String loc;

			public String utc;

			public void setLoc(String loc){
			this.loc = loc;
			}
			public String getLoc(){
			return this.loc;
			}
			public void setUtc(String utc){
			this.utc = utc;
			}
			public String getUtc(){
			return this.utc;
			}

			}

		}
	
	
	
	/**
	 * @author :LiuJie 2015年11月2日 上午11:32:44
	 * @注释:现在天气状况
	 */
	public class now {
		public cond cond;

		public String fl;

		public String hum;

		public String pcpn;

		public String pres;

		public String tmp;

		public String vis;

		public wind wind;

		public void setCond(cond cond){
		this.cond = cond;
		}
		public cond getCond(){
		return this.cond;
		}
		public void setFl(String fl){
		this.fl = fl;
		}
		public String getFl(){
		return this.fl;
		}
		public void setHum(String hum){
		this.hum = hum;
		}
		public String getHum(){
		return this.hum;
		}
		public void setPcpn(String pcpn){
		this.pcpn = pcpn;
		}
		public String getPcpn(){
		return this.pcpn;
		}
		public void setPres(String pres){
		this.pres = pres;
		}
		public String getPres(){
		return this.pres;
		}
		public void setTmp(String tmp){
		this.tmp = tmp;
		}
		public String getTmp(){
		return this.tmp;
		}
		public void setVis(String vis){
		this.vis = vis;
		}
		public String getVis(){
		return this.vis;
		}
		public void setWind(wind wind){
		this.wind = wind;
		}
		public wind getWind(){
		return this.wind;
		}

		
		/**
		 * @author :LiuJie 2015年11月2日 上午11:30:49
		 * @注释:天下状况
		 */
		public class cond {
			public String code;

			public String txt;

			public void setCode(String code){
			this.code = code;
			}
			public String getCode(){
			return this.code;
			}
			public void setTxt(String txt){
			this.txt = txt;
			}
			public String getTxt(){
			return this.txt;
			}

			}
		/**
		 * @author :LiuJie 2015年11月2日 上午11:31:49
		 * @注释:风向风速
		 */
		public class wind {
			public String deg;

			public String dir;

			public String sc;

			public String spd;

			public void setDeg(String deg){
			this.deg = deg;
			}
			public String getDeg(){
			return this.deg;
			}
			public void setDir(String dir){
			this.dir = dir;
			}
			public String getDir(){
			return this.dir;
			}
			public void setSc(String sc){
			this.sc = sc;
			}
			public String getSc(){
			return this.sc;
			}
			public void setSpd(String spd){
			this.spd = spd;
			}
			public String getSpd(){
			return this.spd;
			}

			}
		}
	
	/**
	 * @author :LiuJie 2015年11月2日 下午4:13:06
	 * @注释:天气预报的实体类
	 */
	public class DailyForecast {
		public astro astro;

		public cond cond;

		public String date;

		public String hum;

		public String pcpn;

		public String pop;

		public String pres;

		public tmp tmp;

		public String vis;

		public WindOne wind;

		public class astro {
			public String sr;

			public String ss;

			public void setSr(String sr) {
				this.sr = sr;
			}

			public String getSr() {
				return this.sr;
			}

			public void setSs(String ss) {
				this.ss = ss;
			}

			public String getSs() {
				return this.ss;
			}

		}

		public class cond {
			public String code_d;

			public String code_n;

			public String txt_d;

			public String txt_n;

			public void setCode_d(String code_d) {
				this.code_d = code_d;
			}

			public String getCode_d() {
				return this.code_d;
			}

			public void setCode_n(String code_n) {
				this.code_n = code_n;
			}

			public String getCode_n() {
				return this.code_n;
			}

			public void setTxt_d(String txt_d) {
				this.txt_d = txt_d;
			}

			public String getTxt_d() {
				return this.txt_d;
			}

			public void setTxt_n(String txt_n) {
				this.txt_n = txt_n;
			}

			public String getTxt_n() {
				return this.txt_n;
			}

		}

		public class tmp {
			public String max;

			public String min;

			public void setMax(String max) {
				this.max = max;
			}

			public String getMax() {
				return this.max;
			}

			public void setMin(String min) {
				this.min = min;
			}

			public String getMin() {
				return this.min;
			}

		}

		public class WindOne {
			public String deg;

			public String dir;

			public String sc;

			public String spd;

			public void setDeg(String deg) {
				this.deg = deg;
			}

			public String getDeg() {
				return this.deg;
			}

			public void setDir(String dir) {
				this.dir = dir;
			}

			public String getDir() {
				return this.dir;
			}

			public void setSc(String sc) {
				this.sc = sc;
			}

			public String getSc() {
				return this.sc;
			}

			public void setSpd(String spd) {
				this.spd = spd;
			}

			public String getSpd() {
				return this.spd;
			}

		}

		public astro getAstro() {
			return astro;
		}

		public void setAstro(astro astro) {
			this.astro = astro;
		}

		public cond getCond() {
			return cond;
		}

		public void setCond(cond cond) {
			this.cond = cond;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getHum() {
			return hum;
		}

		public void setHum(String hum) {
			this.hum = hum;
		}

		public String getPcpn() {
			return pcpn;
		}

		public void setPcpn(String pcpn) {
			this.pcpn = pcpn;
		}

		public String getPop() {
			return pop;
		}

		public void setPop(String pop) {
			this.pop = pop;
		}

		public String getPres() {
			return pres;
		}

		public void setPres(String pres) {
			this.pres = pres;
		}

		public tmp getTmp() {
			return tmp;
		}

		public void setTmp(tmp tmp) {
			this.tmp = tmp;
		}

		public String getVis() {
			return vis;
		}

		public void setVis(String vis) {
			this.vis = vis;
		}

		public WindOne getWind() {
			return wind;
		}

		public void setWind(WindOne wind) {
			this.wind = wind;
		}

	}

	/**
	 * @author :LiuJie 2015年11月2日 下午4:16:18
	 * @注释:三小时预报
	 */
	public class HourlyForecast {
		public String date;

		public String hum;

		public String pop;

		public String pres;

		public String tmp;

		public WindTwo wind;

		public class WindTwo {
			
			public String deg;

			public String dir;

			public String sc;

			public String spd;

			public void setDeg(String deg) {
				this.deg = deg;
			}

			public String getDeg() {
				return this.deg;
			}

			public void setDir(String dir) {
				this.dir = dir;
			}

			public String getDir() {
				return this.dir;
			}

			public void setSc(String sc) {
				this.sc = sc;
			}

			public String getSc() {
				return this.sc;
			}

			public void setSpd(String spd) {
				this.spd = spd;
			}

			public String getSpd() {
				return this.spd;
			}

		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getDate() {
			return this.date;
		}

		public void setHum(String hum) {
			this.hum = hum;
		}

		public String getHum() {
			return this.hum;
		}

		public void setPop(String pop) {
			this.pop = pop;
		}

		public String getPop() {
			return this.pop;
		}

		public void setPres(String pres) {
			this.pres = pres;
		}

		public String getPres() {
			return this.pres;
		}

		public void setTmp(String tmp) {
			this.tmp = tmp;
		}

		public String getTmp() {
			return this.tmp;
		}

		public void setWind(WindTwo wind) {
			this.wind = wind;
		}

		public WindTwo getWind() {
			return this.wind;
		}

	}

	
	
	public class suggestion {
		/**@注释：舒适度指数  */
		public comf comf;
		 //洗车指数
		public cw cw;
		//穿衣指数
		public drsg drsg;
		 //感冒指数
		public flu flu;
		 //运动指数
		public sport sport;
		 //旅游指数
		public trav trav;
		//紫外线指数
		public uv uv;

		public comf getComf() {
			return comf;
		}

		public void setComf(comf comf) {
			this.comf = comf;
		}

		public cw getCw() {
			return cw;
		}

		public void setCw(cw cw) {
			this.cw = cw;
		}

		public drsg getDrsg() {
			return drsg;
		}

		public void setDrsg(drsg drsg) {
			this.drsg = drsg;
		}

		public flu getFlu() {
			return flu;
		}

		public void setFlu(flu flu) {
			this.flu = flu;
		}

		public sport getSport() {
			return sport;
		}

		public void setSport(sport sport) {
			this.sport = sport;
		}

		public trav getTrav() {
			return trav;
		}

		public void setTrav(trav trav) {
			this.trav = trav;
		}

		public uv getUv() {
			return uv;
		}

		public void setUv(uv uv) {
			this.uv = uv;
		}
		
		public class comf {
			public String brf;

			public String txt;

			public void setBrf(String brf){
			this.brf = brf;
			}
			public String getBrf(){
			return this.brf;
			}
			public void setTxt(String txt){
			this.txt = txt;
			}
			public String getTxt(){
			return this.txt;
			}

			}
		
		public class cw {
			public String brf;

			public String txt;

			public void setBrf(String brf){
			this.brf = brf;
			}
			public String getBrf(){
			return this.brf;
			}
			public void setTxt(String txt){
			this.txt = txt;
			}
			public String getTxt(){
			return this.txt;
			}

			}
		
		public class drsg {
			public String brf;

			public String txt;

			public void setBrf(String brf){
			this.brf = brf;
			}
			public String getBrf(){
			return this.brf;
			}
			public void setTxt(String txt){
			this.txt = txt;
			}
			public String getTxt(){
			return this.txt;
			}

			}
		
		public class flu {
			public String brf;

			public String txt;

			public void setBrf(String brf){
			this.brf = brf;
			}
			public String getBrf(){
			return this.brf;
			}
			public void setTxt(String txt){
			this.txt = txt;
			}
			public String getTxt(){
			return this.txt;
			}

			}
		/**@注释：运动指数  */
		public class sport {
			public String brf;

			public String txt;

			public void setBrf(String brf){
			this.brf = brf;
			}
			public String getBrf(){
			return this.brf;
			}
			public void setTxt(String txt){
			this.txt = txt;
			}
			public String getTxt(){
			return this.txt;
			}

			}
		
		/**@注释：旅游指数  */
		public class trav {
			public String brf;

			public String txt;

			public void setBrf(String brf){
			this.brf = brf;
			}
			public String getBrf(){
			return this.brf;
			}
			public void setTxt(String txt){
			this.txt = txt;
			}
			public String getTxt(){
			return this.txt;
			}

			}
		/**@注释：紫外线指数  */
		public class uv {
			public String brf;

			public String txt;

			public void setBrf(String brf){
			this.brf = brf;
			}
			public String getBrf(){
			return this.brf;
			}
			public void setTxt(String txt){
			this.txt = txt;
			}
			public String getTxt(){
			return this.txt;
			}

			}
	}
	
	
	
	

	public aqi getAqi() {
		return aqi;
	}

	public void setAqi(aqi aqi) {
		this.aqi = aqi;
	}

	public basic getBasic() {
		return basic;
	}

	public void setBasic(basic basic) {
		this.basic = basic;
	}

	public now getNow() {
		return now;
	}

	public void setNow(now now) {
		this.now = now;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public suggestion getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(suggestion suggestion) {
		this.suggestion = suggestion;
	}

	public List<DailyForecast> getDaily_forecasts() {
		return daily_forecast;
	}

	public void setDaily_forecasts(List<DailyForecast> daily_forecasts) {
		this.daily_forecast = daily_forecasts;
	}

	public List<HourlyForecast> getHourly_forecasts() {
		return hourly_forecast;
	}

	public void setHourly_forecasts(List<HourlyForecast> hourly_forecasts) {
		this.hourly_forecast = hourly_forecasts;
	}
	
}
