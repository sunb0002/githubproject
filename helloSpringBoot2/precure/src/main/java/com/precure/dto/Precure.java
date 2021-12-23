package com.precure.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Precure {
    @NotBlank(message = "Name must not be blank")
    @Size(min = 2, max = 10, message = "Name length must be 2-10")
    private String name;
    @Max(value = 16, message = "too old")
    private Integer age;
    private String sprite;
}
