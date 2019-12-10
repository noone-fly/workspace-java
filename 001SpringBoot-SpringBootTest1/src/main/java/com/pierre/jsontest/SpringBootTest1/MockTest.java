package com.pierre.jsontest.SpringBootTest1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MockTest {
	@MockBean
	private RemoteService remoteService;
	
	@Autowired
	private MainService mainService;
	
	@Test
	public void testMainService() {
		//模拟remoteService 的 call 方法返回 angus
		BDDMockito.given(this.remoteService.call()).willReturn("angus");
		mainService.mainService();
	}
}
