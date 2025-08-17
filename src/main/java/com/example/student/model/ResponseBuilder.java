package com.example.student.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBuilder {

	@JsonProperty("apireqRefNo")
	private String aPIReqRefNo;
	@JsonProperty("apiresRefNo")
	private String aPIResRefNo;

	@JsonProperty("statusCode")
	private String statusCode;

	@JsonProperty("status")
	private String status;

	@JsonProperty("message")
	private String message;
}
