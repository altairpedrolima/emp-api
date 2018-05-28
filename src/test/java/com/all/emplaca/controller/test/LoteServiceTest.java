package com.all.emplaca.controller.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.all.emplaca.service.LoteService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoteServiceTest {
	
	@Autowired
	private LoteService loteService;
	
	@Test
    public void contexLoads() throws Exception {
        assertThat(loteService).isNotNull();
    }	
	
		
	

}
