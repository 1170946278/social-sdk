package com.belerweb.social.qq.connect;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;

import com.belerweb.social.qq.connect.bean.Display;
import com.belerweb.social.qq.connect.bean.Gut;
import com.belerweb.social.qq.connect.bean.Scope;

public final class OAuth2 {

  private QQConnect connect;

  OAuth2(QQConnect connect) {
    this.connect = connect;
  }

  /**
   * @see OAuth2#authorize(Boolean)
   */
  public String authorize() {
    return authorize(false);
  }


  /**
   * @see OAuth2#authorize(String, Boolean)
   */
  public String authorize(String redirectUri) {
    return authorize(redirectUri, false);
  }

  /**
   * 从 {@link QQConnect} 从获取clientId，redirectUri，responseType为code，state使用authorize，scope使用
   * {@link Scope#ALL}，其余参数默认
   * 
   * @see OAuth2#authorize(String, String, String, String, Scope[], Display, Gut, Boolean)
   */
  public String authorize(Boolean wap) {
    return authorize(connect.getClientId(), connect.getRedirectUri(), "code", "authorize",
        Scope.ALL, null, null, wap);
  }


  /**
   * 从 {@link QQConnect} 从获取clientId，responseType为code，state使用authorize，scope使用 {@link Scope#ALL}
   * ，其余参数默认
   * 
   * @see OAuth2#authorize(String, String, String, String, Scope[], Display, Gut, Boolean)
   */
  public String authorize(String redirectUri, Boolean wap) {
    return authorize(connect.getClientId(), redirectUri, "code", "authorize", Scope.ALL, null,
        null, wap);
  }

  /**
   * 获取Authorization Code
   * 
   * 文档地址：http://wiki.connect.qq.com/使用authorization_code获取access_token
   * 
   * @param clientId 必须，申请QQ登录成功后，分配给应用的appid。
   * @param redirectUri 必须，成功授权后的回调地址，必须是注册appid时填写的主域名下的地址，建议设置为网站首页或网站的用户中心。注意需要将url进行URLEncode。
   * @param responseType 必须，授权类型，此值固定为“code”。
   * @param state 必须，client端的状态值。用于第三方应用防止CSRF攻击，成功授权后回调时会原样带回。请务必严格按照流程检查用户与state参数状态的绑定。
   * @param scope 可选，请求用户授权时向用户显示的可进行授权的列表
   * @param display 可选，仅PC网站接入时使用。用于展示的样式。不传则默认展示为PC下的样式。如果传入“mobile”，则展示为mobile端下的样式。
   * @param gut 仅WAP网站接入时使用。QQ登录页面版本（1：wml版本； 2：xhtml版本），默认值为1。
   * @param wap 是否使wap版，默认为false
   */
  public String authorize(String clientId, String redirectUri, String responseType, String state,
      Scope[] scope, Display display, Gut gut, Boolean wap) {
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    connect.addParameter(params, "response_type", responseType);
    connect.addParameter(params, "client_id", clientId);
    connect.addParameter(params, "redirect_uri", redirectUri);
    connect.addParameter(params, "state", state);
    if (scope != null) {
      connect.addParameter(params, "scope", StringUtils.join(scope, ","));
    }
    if (Display.MOBILE.equals(display)) {
      connect.addParameter(params, "display", "mobile");
    }
    if (Gut.XHTML.equals(gut)) {
      connect.addParameter(params, "g_ut", gut.toString());
    }

    String url = "https://graph.qq.com/oauth2.0/authorize?";
    if (Boolean.TRUE.equals(wap)) {
      url = "https://graph.z.qq.com/moc2/authorize?";
    }
    return url + StringUtils.join(params, "&");
  }

}
