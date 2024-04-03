package org.weslleycabral.cadastro_pessoas.services;

import org.springframework.stereotype.Service;
import org.weslleycabral.cadastro_pessoas.utils.SuccessResponse;

import java.time.LocalDateTime;

@Service
public class SuccessResponseService {

    public SuccessResponse successResponse(String message, Object data, LocalDateTime dataUpdate){
        return new SuccessResponse(message, data, dataUpdate);
    }
}
