package org.daijie.dome.jdbc.database;

import java.sql.SQLException;

import org.junit.Test;

public class ExportDatabaseHtmlTest {

//	private String path = "E:\\work\\svn\\Documents\\project\\Collect\\数据结构\\数据结构.html";
//	private String path = "E:\\work\\svn\\Documents\\project\\Recordcenter-ws\\1.0.2\\数据结构\\数据结构.html";
//	private String path = "E:\\work\\svn\\Documents\\project\\Recordcenter\\1.0.2\\数据结构\\数据结构.html";
//	private String path = "E:\\work\\svn\\Documents\\project\\Platform\\1.0.2\\数据结构\\数据结构.html";
	private String path = "d:\\数据结构.html";
	
	@Test
	public void test() {
		ExportDatabaseHtml edh = new ExportDatabaseHtml();
		try {
//			String db = "collect_db";
//			String db = "recordcenter_ws_db";
//			String db = "recordcenter_db";
//			String db = "platform_db";
			String db = "zima_user";
			edh.getHtml("114.113.112.197", "18061", db, "javagroup", "jN2hbH0Saht#GaQKzUQJ*RM3wO=a", path);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
