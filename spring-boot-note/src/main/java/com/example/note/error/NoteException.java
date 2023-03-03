package com.example.note.error;

import com.example.note.enumeration.ReturnCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 自定義NoteException
 */
@Getter
@Setter
public class NoteException extends RuntimeException {

    private ReturnCode returnCode;

    public NoteException(ReturnCode returnCode) {
        super(returnCode.getMsg());
        this.returnCode = returnCode;
    }

}
