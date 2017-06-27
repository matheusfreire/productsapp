package com.msf.rest;

import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.msf.rest.bd.EntityManagerUtil;

@RunWith(Suite.class)
@SuiteClasses({ ProductsTest.class,ImagesTest.class })
public class TesteSuite {
	
	@After
	public void close(){
		EntityManagerUtil.getEntityManager().close();
	}

}
