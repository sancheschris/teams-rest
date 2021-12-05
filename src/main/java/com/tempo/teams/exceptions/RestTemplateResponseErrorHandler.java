package com.tempo.teams.exceptions;

import com.google.gson.Gson;
import com.tempo.teams.presenter.ResponseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Slf4j
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR || response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        var msgErro = new String(response.getBody().readAllBytes());

        log.error("Erro no request - Status:" + response.getStatusCode());
        log.error("Erro no request - Body:" + msgErro);

        if (response.getRawStatusCode() == 400) {
            ResponseError errorGetData;
            try {
                errorGetData = new Gson().fromJson(msgErro, ResponseError.class);
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new InternalServerErrorException("Não foi possível converter o erro em: " + ResponseError.class, null);
            }
            throw new InternalServerErrorException(errorGetData.getErros().get(0).getCode(), errorGetData);
        }
        throw new InternalServerErrorException(msgErro, null);
    }
}
