package com.belerweb.social.weixin.bean;

import java.util.Date;

import org.json.JSONObject;
import org.json.XML;

import com.belerweb.social.bean.JsonBean;
import com.belerweb.social.bean.Result;

/**
 * 普通消息
 */
public class ReceivedMessage extends JsonBean {

  public ReceivedMessage() {}

  private ReceivedMessage(JSONObject jsonObject) {
    super(jsonObject);
  }

  private Long msgId;// 消息id
  private String fromUser;// 发送方帐号（一个OpenID）
  private String toUser;// 开发者微信号
  private Date createTime;// 消息创建时间
  private MsgType msgType;// 消息类型
  private String content;// 文本消息内容
  private String mediaId;// 消息媒体id，可以调用多媒体文件下载接口拉取数据。
  private String picUrl;// 图片链接
  private VoiceType voiceType;// 语音格式
  private String recognition;// 语音识别结果
  private String thumbMediaId;// 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
  private Double lon;// 地理位置经度
  private Double lat;// 地理位置纬度
  private Integer scale;// 地图缩放大小
  private String label;// 地理位置信息
  private String title;// 消息标题
  private String description;// 消息描述
  private String url;// 消息链接

  /**
   * 消息id
   */
  public Long getMsgId() {
    return msgId;
  }

  public void setMsgId(Long msgId) {
    this.msgId = msgId;
  }

  /**
   * 发送方帐号
   */
  public String getFromUser() {
    return fromUser;
  }

  public void setFromUser(String fromUser) {
    this.fromUser = fromUser;
  }

  /**
   * 接受方帐号
   */
  public String getToUser() {
    return toUser;
  }

  public void setToUser(String toUser) {
    this.toUser = toUser;
  }

  /**
   * 消息创建时间
   */
  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  /**
   * 消息类型
   */
  public MsgType getMsgType() {
    return msgType;
  }

  public void setMsgType(MsgType msgType) {
    this.msgType = msgType;
  }

  /**
   * 文本消息内容
   */
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  /**
   * 图片／语音／视频特有：消息媒体id，可以调用多媒体文件下载接口拉取数据。
   */
  public String getMediaId() {
    return mediaId;
  }

  public void setMediaId(String mediaId) {
    this.mediaId = mediaId;
  }

  /**
   * 图片消息特有：图片链接
   */
  public String getPicUrl() {
    return picUrl;
  }

  public void setPicUrl(String picUrl) {
    this.picUrl = picUrl;
  }

  /**
   * 语音消息特有：语音格式
   */
  public VoiceType getVoiceType() {
    return voiceType;
  }

  public void setVoiceType(VoiceType voiceType) {
    this.voiceType = voiceType;
  }


  /**
   * 语音消息特有： 语音识别结果，UTF8编码，只有在开启语音识别后才有此结果
   */
  public String getRecognition() {
    return recognition;
  }

  public void setRecognition(String recognition) {
    this.recognition = recognition;
  }

  /**
   * 视频消息特有：视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
   */
  public String getThumbMediaId() {
    return thumbMediaId;
  }

  public void setThumbMediaId(String thumbMediaId) {
    this.thumbMediaId = thumbMediaId;
  }

  /**
   * 位置消息特有：地理位置经度
   */
  public Double getLon() {
    return lon;
  }

  public void setLon(Double lon) {
    this.lon = lon;
  }

  /**
   * 位置消息特有：地理位置纬度
   */
  public Double getLat() {
    return lat;
  }

  public void setLat(Double lat) {
    this.lat = lat;
  }

  /**
   * 位置消息特有： 地图缩放大小
   */
  public Integer getScale() {
    return scale;
  }

  public void setScale(Integer scale) {
    this.scale = scale;
  }

  /**
   * 位置消息特有： 地理位置信息
   */
  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  /**
   * 链接消息特有：消息标题
   */
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * 链接消息特有：消息描述
   */
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * 链接消息特有：消息链接
   */
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public static ReceivedMessage parse(String xml) {
    return parse(XML.toJSONObject(xml).getJSONObject("xml"));
  }

  public static ReceivedMessage parse(JSONObject jsonObject) {
    if (jsonObject == null) {
      return null;
    }
    ReceivedMessage obj = new ReceivedMessage(jsonObject);
    obj.msgId = Result.parseLong(jsonObject.get("MsgId"));
    MsgType type = MsgType.parse(jsonObject.get("MsgType"));
    obj.msgType = type;
    obj.createTime = new Date(Result.parseLong(jsonObject.get("CreateTime")) * 1000);
    obj.fromUser = Result.toString(jsonObject.get("FromUserName"));
    obj.toUser = Result.toString(jsonObject.get("ToUserName"));
    if (type == MsgType.TEXT) {
      obj.content = Result.toString(jsonObject.opt("Content"));
    }
    if (type == MsgType.IMAGE) {
      obj.mediaId = Result.toString(jsonObject.opt("MediaId"));
      obj.content = Result.toString(jsonObject.opt("PicUrl"));
    }
    if (type == MsgType.VOICE) {
      obj.mediaId = Result.toString(jsonObject.opt("MediaId"));
      obj.voiceType = VoiceType.parse(jsonObject.opt("Format"));
      obj.recognition = Result.toString(jsonObject.opt("Recognition"));
    }
    if (type == MsgType.VIDEO) {
      obj.mediaId = Result.toString(jsonObject.opt("MediaId"));
      obj.thumbMediaId = Result.toString(jsonObject.opt("ThumbMediaId"));
    }
    if (type == MsgType.LOCATION) {
      obj.lon = Result.parseDouble(jsonObject.opt("Location_Y"));
      obj.lat = Result.parseDouble(jsonObject.opt("Location_X"));
      obj.scale = Result.parseInteger(jsonObject.opt("Scale"));
      obj.label = Result.toString(jsonObject.opt("Label"));
    }
    if (type == MsgType.LINK) {
      obj.title = Result.toString(jsonObject.opt("Title"));
      obj.description = Result.toString(jsonObject.opt("Description"));
      obj.url = Result.toString(jsonObject.opt("Url"));
    }
    return obj;
  }

}
