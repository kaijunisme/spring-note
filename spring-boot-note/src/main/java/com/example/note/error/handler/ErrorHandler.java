package com.example.note.error.handler;


import com.example.note.dto.ResponseDto;
import com.example.note.enumeration.ReturnCode;
import com.example.note.error.NoteException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 統一處理註釋@RestController 的控制器層所拋出的例外狀況
 */
@RestControllerAdvice(annotations = {RestController.class})
@Slf4j
public class ErrorHandler {

    /**
     * 處理MethodArgumentNotValidException 例外狀況
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.info("MethodArgumentNotValidException occurred, Error argument:");

        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        fieldErrors.forEach(error -> {
            log.info("{}: {}", error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.badRequest()
                .body(ResponseDto.of(ReturnCode.VALID_ERROR, fieldErrors.get(0).getDefaultMessage(), null));
    }

    /**
     * 處理NoteException 例外狀況
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NoteException.class)
    public ResponseEntity<ResponseDto> handleNoteException(NoteException e) {
        log.error("NoteException occurred, Error code : {}, Error message: {}",
                e.getReturnCode().getCode(), e.getReturnCode().getMsg());

        return ResponseEntity.badRequest()
                .body(ResponseDto.error(e.getReturnCode()));
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

        return ResponseEntity.badRequest()
                .body(ResponseDto.error(ReturnCode.ERROR));
    }

}
