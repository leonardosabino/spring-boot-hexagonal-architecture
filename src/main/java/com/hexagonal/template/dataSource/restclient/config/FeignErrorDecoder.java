package com.hexagonal.template.dataSource.restclient.config;

import com.hexagonal.template.dataSource.restclient.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import org.springframework.stereotype.Component;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

  @Override
  public Exception decode(String methodKey, Response response) {

    String responseObject = getResponseObject(response);
    return new FeignException(responseObject);
  }

  private String getResponseObject(Response response) {
    String result;
    try {
      Reader responseReader = response.body().asReader(Charset.defaultCharset());
      BufferedReader reader = new BufferedReader(responseReader);
      StringBuilder sb = new StringBuilder();
      String str;
      while ((str = reader.readLine()) != null) {
        sb.append(str);
      }
      result = sb.toString();
    } catch (IOException e) {
      throw new FeignException(e.getMessage());
    }
    return result;
  }

}
