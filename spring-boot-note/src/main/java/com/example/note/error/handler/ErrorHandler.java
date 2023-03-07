package com.example.note.error.handler;


import com.example.note.dto.ResponseDto;
import com.example.note.enumeration.ReturnCode;
import com.example.note.error.NoteException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Locale;

/**
 * 統一處理註釋@RestController 的控制器層所拋出的例外狀況
 */
@RestControllerAdvice(annotations = {RestController.class})
@Slf4j
public class ErrorHandler {

    @Autowired
    private MessageSource messageSource;

    /**
     * 處理MethodArgumentNotValidException 例外狀況
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.info("MethodArgumentNotValidException occurred, Error argument:");

        Locale locale = LocaleContextHolder.getLocale();

        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        fieldErrors.forEach(error -> {
            log.info("{}: {}", error.getField(), messageSource.getMessage(error.getDefaultMessage(), null, locale));
        });

        String message = messageSource.getMessage(fieldErrors.get(0).getDefaultMessage(), null, locale);

        return ResponseEntity.badRequest()
                .body(ResponseDto.of(ReturnCode.VALID_ERROR, message, null));
    }

    /**
     * 處理NoteException 例外狀況
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NoteException.class)
    public ResponseEntity<ResponseDto> handleNoteException(NoteException e) {
        String message = messageSource.getMessage(e.getReturnCode().getMsg(), null, LocaleContextHolder.getLocale());

        log.error("NoteException occurred, Error code : {}, Error message: {}",
                e.getReturnCode().getCode(), message);

        return ResponseEntity.badRequest()
                .body(ResponseDto.of(e.getReturnCode(), message, null));
    }

    /**
     * 處理非上述例外狀況
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> handleException(Exception e) {
        log.error("Exception occurred, Error stack: ", e);

        String message = messageSource.getMessage(ReturnCode.ERROR.getMsg(), null, LocaleContextHolder.getLocale());

        return ResponseEntity.badRequest()
                .body(ResponseDto.of(ReturnCode.ERROR, message, null));
    }

}
