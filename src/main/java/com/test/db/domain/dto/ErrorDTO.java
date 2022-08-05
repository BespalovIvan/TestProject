package com.test.db.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ErrorDTO {

   private String type;
   private String message;

}
