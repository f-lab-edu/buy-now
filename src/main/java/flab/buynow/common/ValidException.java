package flab.buynow.common;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ValidException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgNotValueException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();

        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append(fieldError.getField());
            builder.append("은(는) ");
            builder.append(fieldError.getDefaultMessage());
            builder.append(" 입력값 : " + fieldError.getRejectedValue() + "\n");
        }

        return ResponseEntity.badRequest().body(builder.toString());
    }

}