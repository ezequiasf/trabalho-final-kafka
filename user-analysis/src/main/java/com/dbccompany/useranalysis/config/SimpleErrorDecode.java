package com.dbccompany.useranalysis.config;

import com.dbccompany.useranalysis.exceptions.ObjetoNaoEncontradoException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.rmi.UnexpectedException;

public class SimpleErrorDecode implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        Response.Body body = response.body();
        if (body == null) {
            return new UnexpectedException("Erro inesperado");
        }

        try {
            String bodyString = IOUtils.toString(body.asInputStream());
            if (response.status() == 400) {
                return new ObjetoNaoEncontradoException(bodyString);
            }
            return new Exception("Generic error");
        } catch (IOException e) {
            e.printStackTrace();
            return e;
        }
    }
}