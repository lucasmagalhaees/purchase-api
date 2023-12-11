package com.lucasbarbosa.purchase.driver.exception;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {
  private int status;
  private List<String> message;
  private String path;

  public static ErrorResponse ofErrorMessage(
      HttpServletRequest request, ErrorMessage errorMessage, int status) {
    return ErrorResponse.builder()
        .message(errorMessage.getErrors())
        .path(request.getRequestURI())
        .status(status)
        .build();
  }
}
