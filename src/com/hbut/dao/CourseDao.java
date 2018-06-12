package com.hbut.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hbut.bean.Course;
import com.hbut.util.ConnectSQL;

public class CourseDao {
	
	public static void insertCourse(Course c){
		Connection conn = ConnectSQL.getConnection();
		String sql = "INSERT INTO hbut_course ("
				+ "CourseNO, CourseName, CourseType, CourseCredit, CourseTime) "
				+ " VALUES ("
				+ "'"+c.getCourseNO()+"', '"+c.getCourseName()
				+ "', '"+c.getCourseType()+"', '"+c.getCredie()
				+ "', '"+c.getCourseTime()+"' )";
		System.out.println("CourseDao:"+sql);
		try {
			PreparedStatement pstate = conn.prepareStatement(sql);
			// System.out.println(sql);
			int rs = pstate.executeUpdate();
			if (rs==1) {
				System.out.println("课程添加成功");
			}
		} catch (SQLException e1) {
			System.out.println("课程添加失败");
			e1.printStackTrace();
		}
	}
	
	public static void insertSelectClass(Course c,int peopleNum){
		Connection conn = ConnectSQL.getConnection();
		String sql = "INSERT INTO hbut_class(ClassNO,PeopleNum,Grade,Major,Ind)"
				+" VALUES( "
				+"'"+c.getCourseNO()+"00"+"',"+peopleNum+","
				+0+",'"+c.getCourseName()+"',"+0+")";
		System.out.println("CourseDao:"+sql);
		try {
			PreparedStatement pstate = conn.prepareStatement(sql);
			int rs = pstate.executeUpdate();
			if (rs==1) {
				System.out.println("选修班级添加成功");
			}
		} catch (SQLException e1) {
			System.out.println("选修班级添加失败");
			e1.printStackTrace();
		}
	}
	
//	// 返回所用no-name键值对
//	public static Map<String,String> queryCourse() {
//		
//		Map<String,String> map=new HashMap<String, String>();
//		Connection conn = ConnectSQL.getConnection();
//		String sql = "select CourseNO,CourseName from hbut_course ";
//		try {
//			PreparedStatement pstate = conn.prepareStatement(sql);
//			ResultSet rs = pstate.executeQuery();
//			while (rs.next()) {
//				map.put(rs.getString("CourseNO"), rs.getString("CourseName"));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return map;
//	}
	
	// 查询所有事件类型
	public static ResultSet queryCourse() {
		ResultSet rs = null;
		Connection conn = ConnectSQL.getConnection();
		String sql = "select CourseNO,CourseName from hbut_course ";
		try {
			PreparedStatement pstate = conn.prepareStatement(sql);
			rs = pstate.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public static void saveCourse(Course c,int peopleNum){
		insertCourse(c);
		//不是公选课
		if( c.getCourseType() == 3 ){
			insertSelectClass(c,peopleNum);
		}
	}

}
