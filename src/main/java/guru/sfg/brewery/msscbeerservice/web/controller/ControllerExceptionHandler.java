package guru.sfg.brewery.msscbeerservice.web.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List> methodArgumentNotValidExceptionHandler(
      final MethodArgumentNotValidException e) {
    final List<String> errors =
        e.getBindingResult().getFieldErrors().stream()
            .map(
                c ->
                    String.format(
                        "Exception validating input %s -> Field: [%s], value: [%s], reason: [%s]",
                        c.getObjectName(),
                        c.getField(),
                        c.getRejectedValue(),
                        c.getDefaultMessage()))
            .collect(Collectors.toList());
    return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<List> constraintValidationHandler(final ConstraintViolationException e) {
    final List<String> errors =
        e.getConstraintViolations().stream()
            .map(c -> String.format("%s : %s", c.getPropertyPath(), c.getMessage()))
            .collect(Collectors.toList());
    return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler(BindException.class)
  public ResponseEntity<List> bindExceptionHandler(final BindException e) {
    return ResponseEntity.badRequest().body(e.getAllErrors());
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity resourceNotFoundExceptionHandler(final NotFoundException e) {
    final URI location = null; // TODO: Should I provide a location?
    return ResponseEntity.notFound().location(location).build();
  }
}
