package com.tempo.teams.presenter;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

@ApiModel(description = "Objeto para tratamento de erro padrão")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseError {

    @JsonProperty("errors")
    @ApiModelProperty(position = 0)
    @Valid
    private List<Errors> erros = null;
}
