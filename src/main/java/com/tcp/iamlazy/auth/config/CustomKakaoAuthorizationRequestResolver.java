package com.tcp.iamlazy.auth.config;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;

/**
 * Created with intellij IDEA. by 2020 03 24/03/2020 2:31 오전 24 User we at 02 31 To change this
 * template use File | Settings | File Templates.
 */
public class CustomKakaoAuthorizationRequestResolver implements OAuth2AuthorizationRequestResolver {

  private OAuth2AuthorizationRequestResolver defaultResolver;
  private final String clientId;

  public CustomKakaoAuthorizationRequestResolver(ClientRegistrationRepository repo,
                                                 String authorizationRequestBaseUri,
                                                 OAuth2ClientProperties properties) {
      this.defaultResolver = new DefaultOAuth2AuthorizationRequestResolver(repo, authorizationRequestBaseUri);
      this.clientId = properties.getRegistration().get("kakao").getClientId();
  }

  @Override
  public OAuth2AuthorizationRequest resolve(HttpServletRequest request) {
    OAuth2AuthorizationRequest req = defaultResolver.resolve(request);
    if (req != null) {
      req = customizeAuthorizationRequest(req);
    }
    return req;
  }

  @Override
  public OAuth2AuthorizationRequest resolve(HttpServletRequest request,
                                            String clientRegistrationId) {
    OAuth2AuthorizationRequest req = defaultResolver.resolve(request, clientRegistrationId);
    if (req != null) {
      req = customizeAuthorizationRequest(req);
    }
    return req;
  }

  private OAuth2AuthorizationRequest customizeAuthorizationRequest(OAuth2AuthorizationRequest req) {
    Map<String, Object> extraParams = new HashMap<>();
    extraParams.putAll(req.getAdditionalParameters());
    extraParams.put("client_id", clientId);

    return OAuth2AuthorizationRequest
        .from(req)
        .additionalParameters(extraParams)
        .build();
  }
}
