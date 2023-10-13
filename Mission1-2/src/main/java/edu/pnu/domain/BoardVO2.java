package edu.pnu.domain;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class BoardVO2 {

	private Integer seq;
	private String title;
	private String writer;
	private String content;
	private Date date;
	private Integer cnt;
	
}
