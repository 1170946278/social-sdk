package com.belerweb.social.weibo.bean;

import org.json.JSONObject;

/**
 * 地理信息
 * 
 * 文档地址：http://open.weibo.com/wiki/常见返回对象数据结构#.E5.9C.B0.E7.90.86.E4.BF.A1.E6.81.AF.EF.BC.88geo.EF.BC
 * .89
 */
public class Geo {

  /**
   * 经度坐标
   */
  private Double longitude;

  /**
   * 维度坐标
   */
  private Double latitude;

  /**
   * 所在城市的城市代码
   */
  private String city;

  /**
   * 所在省份的省份代码
   */
  private String province;

  /**
   * 所在城市的城市名称
   */
  private String cityName;

  /**
   * 所在省份的省份名称
   */
  private String provinceName;

  /**
   * 所在的实际地址，可以为空
   */
  private String address;

  /**
   * 地址的汉语拼音，不是所有情况都会返回该字段
   */
  private String pinyin;

  /**
   * 更多信息，不是所有情况都会返回该字段
   */
  private String more;

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public String getProvinceName() {
    return provinceName;
  }

  public void setProvinceName(String provinceName) {
    this.provinceName = provinceName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPinyin() {
    return pinyin;
  }

  public void setPinyin(String pinyin) {
    this.pinyin = pinyin;
  }

  public String getMore() {
    return more;
  }

  public void setMore(String more) {
    this.more = more;
  }

  public static Geo parse(JSONObject jsonObject) {
    if (jsonObject == null) {
      return null;
    }
    Geo obj = new Geo();
    obj.longitude = Result.perseDouble(jsonObject.get("longitude"));
    obj.latitude = Result.perseDouble(jsonObject.get("latitude"));
    obj.city = Result.toString(jsonObject.opt("city"));
    obj.province = Result.toString(jsonObject.opt("province"));
    obj.cityName = Result.toString(jsonObject.opt("city_name"));
    obj.provinceName = Result.toString(jsonObject.opt("province_name"));
    obj.address = Result.toString(jsonObject.opt("address"));
    obj.pinyin = Result.toString(jsonObject.opt("pinyin"));
    obj.more = Result.toString(jsonObject.opt("more"));
    return obj;
  }

}
