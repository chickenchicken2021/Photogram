package com.cos.photogramstart.domain.Subscribe;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cos.photogramstart.domain.User.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor // 전체 생성자
@NoArgsConstructor // 빈 생성자
@Data // 게터세터
@Entity //DB에 테이블을 생성
@Table( // 두개를 복합적으로 유니크 키를 걸때 쓰는방법
		uniqueConstraints = {
				@UniqueConstraint(
						name = "subscribe_uk",
						columnNames = {"fromUserid","toUserid"} // 실제 데이터베이스에서 쓰는 컬럼명
						)
				
		}
		)
public class Subscribe {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다.
		private int id;
		
		@JoinColumn(name = "fromUserid")
		@ManyToOne // 자동으로 테이블을 생성
		private User fromUser;
		
		@JoinColumn(name = "toUserid")
		@ManyToOne // 자동으로 테이블을 생성
		private User toUser;
		
		private LocalDateTime createDate;
		
		@PrePersist // 디비에 INSERT 되기 직전에 실행 
		public void createDate() {
			this.createDate = LocalDateTime.now();
		}

}
