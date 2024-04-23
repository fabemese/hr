package hu.cubix.hr.borcsi.web;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
  /*  @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MyError> handleValidationError(MethodArgumentNotValidException e, WebRequest req){
        MyError myError=new MyError(e.getMessage(), 1002);
        myError.setFieldErrors(e.getBindingResult().getFieldErrors());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myError);
    }*/
}
