package com.example.demo;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.example.demo.login.domain.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	//Point2: モックの戻り値設定
	@MockBean
	private UserService service;
	
	//Point1: @WithMockUser
	@Test
	@WithMockUser
	public void ユーザーリスト画面のユーザー件数のテスト() throws Exception{
		//Point2: モックの戻り値設定
		when(service.count()).thenReturn(10);
		
		//ユーザー一覧画面チェック
		mockMvc.perform(get("/userList"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("合計:10件")));
	}
	
}
