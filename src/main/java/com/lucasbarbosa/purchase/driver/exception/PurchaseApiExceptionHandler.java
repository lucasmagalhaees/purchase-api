package com.lucasbarbosa.purchase.driver.exception;

import static com.lucasbarbosa.purchase.driver.utils.ExceptionUtils.*;
import static com.lucasbarbosa.purchase.driver.utils.PurchaseUtils.createEmptyStringArray;

import com.lucasbarbosa.purchase.driver.exception.custom.AttributeInUseException;
import com.lucasbarbosa.purchase.driver.exception.custom.EntityNotFoundException;
import com.lucasbarbosa.purchase.driver.exception.custom.FeignIntegrationException;
import com.lucasbarbosa.purchase.driver.exception.custom.PurchaseNotConvertedException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PurchaseApiExceptionHandler {

  private final MessageSource messageSource;

  public PurchaseApiExceptionHandler(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ExceptionHandler(FeignIntegrationException.class)
  @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
  public ErrorResponse handleFeignIntegrationException(
      HttpServletRequest request, FeignIntegrationException ex) {
    var errorMessage =
        new ErrorMessage(
            messageSource.getMessage(
                getFeignIntegration(),
                buildWithSingleParam(ex.getFirst()),
                LocaleContextHolder.getLocale()));
    return ErrorResponse.ofErrorMessage(request, errorMessage, NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleValidationExceptions(
      HttpServletRequest request, MethodArgumentNotValidException ex) {
    var errorMessage = new ErrorMessage(ex.getBindingResult());
    return ErrorResponse.ofErrorMessage(request, errorMessage, BAD_REQUEST);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleValidationExceptions(
      HttpServletRequest request, ConstraintViolationException ex) {
    var errorMessage = new ErrorMessage(ex.getConstraintViolations());
    return ErrorResponse.ofErrorMessage(request, errorMessage, BAD_REQUEST);
  }

  @ExceptionHandler(AttributeInUseException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleAttributeInUseException(
      HttpServletRequest request, AttributeInUseException ex) {
    var errorMessage =
        new ErrorMessage(
            messageSource.getMessage(
                getAttributeInUseReference(),
                buildWithThreeParams(ex.getFirst(), ex.getSecond(), ex.getThird()),
                LocaleContextHolder.getLocale()));
    return ErrorResponse.ofErrorMessage(request, errorMessage, BAD_REQUEST);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse handleEntityNotFoundException(
      HttpServletRequest request, EntityNotFoundException ex) {
    var errorMessage =
        new ErrorMessage(
            messageSource.getMessage(
                getEntityNotFoundReference(),
                buildWithThreeParams(ex.getFirst(), ex.getSecond(), ex.getThird()),
                LocaleContextHolder.getLocale()));
    return ErrorResponse.ofErrorMessage(request, errorMessage, NOT_FOUND);
  }

  @ExceptionHandler(PurchaseNotConvertedException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse handlePurchaseNotConvertedException(
      HttpServletRequest request, PurchaseNotConvertedException ex) {
    var errorMessage =
        new ErrorMessage(
            messageSource.getMessage(
                getPurchaseNotConverted(),
                createEmptyStringArray(),
                LocaleContextHolder.getLocale()));
    return ErrorResponse.ofErrorMessage(request, errorMessage, NOT_FOUND);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleMessageNotReadableException(
      HttpServletRequest request, HttpMessageNotReadableException ex) {
    var errorMessage =
        new ErrorMessage(
            messageSource.getMessage(
                getMessageNotReadableReference(),
                createEmptyStringArray(),
                LocaleContextHolder.getLocale()));
    return ErrorResponse.ofErrorMessage(request, errorMessage, BAD_REQUEST);
  }
}
