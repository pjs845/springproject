package pjs.md.domain;

import java.sql.Date;

import lombok.Data;
@Data
public class Board2 {
	private long seq;
	private String writer;
	private String email;
	private String subject;
	private String content;
	private Date rdate;
	private String fname;
	private String ofname;
	private long fsize;
}
