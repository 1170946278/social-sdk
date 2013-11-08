package com.belerweb.social.weixin.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;

import com.belerweb.social.SDK;
import com.belerweb.social.bean.Result;
import com.belerweb.social.weixin.api.OAuth2;
import com.belerweb.social.weixin.api.User;
import com.belerweb.social.weixin.bean.AccessToken;

/**
 * 微信SDK
 */
public final class Weixin extends SDK {

  private String appId;
  private String secret;
  private String redirectUri;
  private String token;

  private OAuth2 oAuth2;
  private User user;

  private AccessToken accessToken;
  private Date accessTokenTime;

  /**
   * 只传入token实例化微信SDK，适合于只开发基于微信基础接口的被动接受消息类应用，如智能应答机器人。不推荐适用。
   * 
   * @param token 在公众平台网站的高级功能 –
   *        开发模式页，点击“成为开发者”按钮，填写URL和Token，其中URL是开发者用来接收微信服务器数据的接口URL。Token可由开发者可以任意填写
   *        ，用作生成签名（该Token会和接口URL中包含的Token进行比对，从而验证安全性）。
   */
  public Weixin(String token) {
    this.token = token;
  }

  /**
   * 通过appId和secret实例化微信SDK，适合于只开发基于微信高级接口的OAuth2应用，如客服功能。
   * 
   * @param appId 公众号的唯一标识
   * @param secret 公众号的appsecret
   */
  public Weixin(String appId, String secret) {
    this.appId = appId;
    this.secret = secret;
  }


  /**
   * 通过token、appId和secret实例化微信SDK，支持微信基础和高级接口。推荐适用
   * 
   * @param appId 公众号的唯一标识
   * @param secret 公众号的appsecret
   * @param token 在公众平台网站的高级功能 –
   *        开发模式页，点击“成为开发者”按钮，填写URL和Token，其中URL是开发者用来接收微信服务器数据的接口URL。Token可由开发者可以任意填写
   *        ，用作生成签名（该Token会和接口URL中包含的Token进行比对，从而验证安全性）。
   */
  public Weixin(String appId, String secret, String token) {
    this(appId, secret);
    this.token = token;
  }

  public Weixin(String appid, String secret, String redirectUri, String token) {
    this(appid, secret, token);
    this.redirectUri = redirectUri;
  }

  /**
   * 验证消息真实性
   * 
   * 文档地址：http://mp.weixin.qq.com/wiki/index.php?title=验证消息真实性
   * 
   * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
   * @param timestamp 时间戳
   * @param nonce 随机数
   * @return 消息有效返回true，否则返回false
   */
  public boolean validate(String signature, String timestamp, String nonce) {
    String[] chars = new String[] {token, timestamp, nonce,};
    Arrays.sort(chars);
    String sha1 = DigestUtils.shaHex(StringUtils.join(chars));
    if (sha1.equals(signature)) {
      return true;
    }

    return false;
  }

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public String getRedirectUri() {
    return redirectUri;
  }

  public void setRedirectUri(String redirectUri) {
    this.redirectUri = redirectUri;
  }

  /**
   * 获取access token
   * 
   * access_token是公众号的全局唯一票据，公众号调用各接口时都需使用access_token。正常情况下access_token有效期为7200秒，
   * 重复获取将导致上次获取的access_token失效。
   * 
   * 公众号可以使用AppID和AppSecret调用本接口来获取access_token。AppID和AppSecret可在开发模式中获得（需要已经成为开发者，且帐号没有异常状态）。
   */
  public AccessToken getAccessToken() {
    if (accessToken == null || accessTokenTime == null
        || (new Date().getTime() - accessTokenTime.getTime()) / 1000 > accessToken.getExpiresIn()) {
      List<NameValuePair> params = new ArrayList<NameValuePair>();
      addParameter(params, "appid", appId);
      addParameter(params, "secret", secret);
      addParameter(params, "grant_type", "client_credential");
      String json =
          get("https://api.weixin.qq.com/cgi-bin/token?" + StringUtils.join(params, "&"), params);
      Result<AccessToken> result = Result.parse(json, AccessToken.class);
      if (result.success()) {
        accessToken = result.getResult();
        accessTokenTime = new Date();
      }
    }

    return accessToken;
  }

  /**
   * 在公众平台网站的高级功能 –
   * 开发模式页，点击“成为开发者”按钮，填写URL和Token，其中URL是开发者用来接收微信服务器数据的接口URL。Token可由开发者可以任意填写，用作生成签名（
   * 该Token会和接口URL中包含的Token进行比对，从而验证安全性）
   */
  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public OAuth2 getOAuth2() {
    if (oAuth2 == null) {
      oAuth2 = new OAuth2(this);
    }

    return oAuth2;
  }

  public User getUser() {
    if (user == null) {
      user = new User(this);
    }

    return user;
  }

}
