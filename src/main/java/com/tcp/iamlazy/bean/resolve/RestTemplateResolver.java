package com.tcp.iamlazy.bean.resolve;

import io.vavr.control.Try;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 이 클래스는 RestTemplate 클래스의 생성을 도맡는다.
 *
 * 기존의 Try-Catch 구문과 예외처리에 대한 처리 때문에 가독성이 상당히 떨어졌던 코드 내용을 줄여내도록 하는 시도가 반영된다.
 *
 * Created with intellij IDEA. by 2020 03 28/03/2020 9:49 오후 28 User we at 21 49 To change this
 *  * template use File | Settings | File Templates.
 */
public class RestTemplateResolver {

  private final SSLConnectionSocketFactory sslsf;

  public RestTemplateResolver() {
    final Try<SSLConnectionSocketFactory> optionTry = Try.of(this::methodRef);
    this.sslsf = optionTry
        .getOrElseThrow(() -> new RuntimeException(new NoSuchAlgorithmException()));
  }

  private SSLConnectionSocketFactory methodRef() throws NoSuchAlgorithmException {
    return new SSLConnectionSocketFactory(SSLContext.getDefault(),
                                          NoopHostnameVerifier.INSTANCE);
  }

  public RestTemplate resolve() {
    final Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
        .register("http", new PlainConnectionSocketFactory())
        .register("https", sslsf)
        .build();

    final PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
    cm.setMaxTotal(100);

    CloseableHttpClient httpClient = HttpClients.custom()
        .setSSLSocketFactory(sslsf)
        .setConnectionManager(cm)
        .build();

    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(
        httpClient);

    requestFactory.setConnectTimeout(10_000);
    requestFactory.setReadTimeout(10_000);

    return new RestTemplate(requestFactory);
  }
}
