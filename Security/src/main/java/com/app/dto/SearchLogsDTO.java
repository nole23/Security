package com.app.dto;

public class SearchLogsDTO {

	private FileLogDTO fileDto;
	private String expression;
	private boolean isRegular;
	
	public SearchLogsDTO() {
	}

	public SearchLogsDTO(FileLogDTO fileDto, String expression, boolean isRegular) {
		super();
		this.fileDto = fileDto;
		this.expression = expression;
		this.isRegular = isRegular;
	}

	public FileLogDTO getFileDto() {
		return fileDto;
	}

	public void setFileDto(FileLogDTO fileDto) {
		this.fileDto = fileDto;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public boolean isRegular() {
		return isRegular;
	}

	public void setRegular(boolean isRegular) {
		this.isRegular = isRegular;
	}

	@Override
	public String toString() {
		return "SearchLogsDTO [fileDto=" + fileDto + ", expression=" + expression + ", isRegular=" + isRegular + "]";
	}

}
