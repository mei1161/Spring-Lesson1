package com.example.demo.login.domain;

import java.util.Date;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
public class SignupForm {
	
	//必須入力、メールアドレス形式
	@NotBlank(message = "{require_check}")
	@Email(message = "{email_check}")
	private String userId;//ユーザーID
	
	//必須入力、半角英数字のみ
	@NotBlank(message = "{require_check}")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message= "{pattern_check}")
	private String password;//パスワード
	
	//必須入力
	@NotBlank(message = "{require_check}")
	private String userName;//ユーザー名
	
	//ポイント:@DateTimeFormat
	//必須入力
	@NotNull(message = "{require_check}")
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date birthday;//誕生日
	
	//値が20-100まで
	@Min(value = 20, message = "{min_check}" )
	@Max(value = 100, message = "{max_check}")
	private int age;//年齢
	
	//falseのみ可能
	@AssertFalse(message = "{false_check}")
	private boolean marriage;//結婚ステータス
	
	public Date getBirthday() {
	    return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isMarriage() {
		return marriage;
	}
	public void setMarriage(boolean marriage) {
		this.marriage = marriage;
	}
}
