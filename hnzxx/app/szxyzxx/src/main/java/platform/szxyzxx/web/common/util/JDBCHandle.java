package platform.szxyzxx.web.common.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import platform.education.generalTeachingAffair.model.ExamQuestion;
import platform.education.generalTeachingAffair.model.ExamStat;
import platform.education.generalTeachingAffair.model.ExamStudent;


/**
 * 2017-08-02
 * 
 * JDBC操作类
 * @author 【pantq】
 *
 */
public class JDBCHandle {

	public static void jdbcUpdateExamStudent(String sql,List<ExamStudent> datas) {
		if(datas == null || datas.size() == 0) {
			return;
		}
		//获取数据库链接
		Connection connection = null;
        PreparedStatement ps = null;
		try {
			
			connection = JDBCUitl.getConnection();
			if(connection != null) {
				//开启事务
				JDBCUitl.beginTx(connection,false);
				//开始时间，测试效率用。
				//long beginTime = System.currentTimeMillis();
				//预处理SQL
				ps = connection.prepareStatement(sql);
				int len = datas.size();
				for(int i = 0; i<len; i++) {
					ExamStudent examStudent = datas.get(i);
					ps.setFloat(1, examStudent.getScore());
					ps.setInt(2, examStudent.getTeamRank());
					ps.setInt(3, examStudent.getGradeRank());
					ps.setInt(4, examStudent.getAnswerCount());
					ps.setInt(5, examStudent.getRightAnswerCount());
					ps.setInt(6, examStudent.getId());
					 //积攒SQL
	                ps.addBatch();
	                //当积攒到一定程度,就执行一次,并且清空记录
	                if((i+1) % 300==0){
	                    ps.executeBatch();
	                    //提交事务
	        			JDBCUitl.commit(connection);
	                    ps.clearBatch();
	                }

				}
				
				//总条数不是批量值整数倍,则还需要在执行一次
                if(len % 300 != 0){
                    ps.executeBatch();
                    ps.clearBatch();
                }

				//long endTime = System.currentTimeMillis();
			//	System.out.println("jdbcUpdateExamStudent : "+(endTime - beginTime));
			}
			
		}catch (Exception e) {
			//遇到错误事务回滚
			JDBCUitl.rollback(connection);
			e.printStackTrace();
		}finally{
			//提交事务
			JDBCUitl.commit(connection);
			//把事务设置为自动提交
			JDBCUitl.beginTx(connection,true);
			//最后释放资源
	        JDBCUitl.free(connection, ps);
        }

        
	}
	
	
	
	public static void jdbcUpdateExamStat(String sql,List<ExamStat> datas) {
		if(datas == null || datas.size() == 0) {
			return;
		}
		//获取数据库链接
		Connection connection = null;
        PreparedStatement ps = null;
		try {
			
			connection = JDBCUitl.getConnection();
			if(connection != null) {
				//开启事务
				JDBCUitl.beginTx(connection,false);
				//开始时间，测试效率用。
				//long beginTime = System.currentTimeMillis();
				//预处理SQL
				ps = connection.prepareStatement(sql);
				int len = datas.size();
				for(int i = 0; i<len; i++) {
					ExamStat examStat = datas.get(i);
					ps.setInt(1, examStat.getStudentCount());
					ps.setFloat(2, examStat.getTotalScore());
					ps.setFloat(3, examStat.getAverageScore());
					ps.setFloat(4, examStat.getFullScore());
					ps.setFloat(5, examStat.getHighScore());
					ps.setFloat(6, examStat.getLowScore());
					ps.setFloat(7, examStat.getPassScore());
					ps.setFloat(8, examStat.getSdScore());
					ps.setFloat(9, examStat.getMadValue());
					ps.setFloat(10, examStat.getMovValue());
					
					ps.setFloat(11, examStat.getHighCount());
					ps.setFloat(12, examStat.getLowCount());
					ps.setFloat(13, examStat.getPassCount());
					
					ps.setFloat(14, examStat.getHighestScore());
					ps.setFloat(15, examStat.getLowestScore());
					ps.setInt(16, examStat.getGradeRank());
					ps.setInt(17, examStat.getId());
					 //积攒SQL
	                ps.addBatch();
	                //当积攒到一定程度,就执行一次,并且清空记录
	                if((i+1) % 300==0){
	                    ps.executeBatch();
	                    //提交事务
	        			JDBCUitl.commit(connection);
	                    ps.clearBatch();
	                }

				}
				
				 //总条数不是批量值整数倍,则还需要在执行一次
                if(len % 300 != 0){
                    ps.executeBatch();
                    ps.clearBatch();
                }
                JDBCUitl.commit(connection);
			//	long endTime = System.currentTimeMillis();
			//	System.out.println("jdbcUpdateExamStat : "+(endTime - beginTime));
			}
			
		}catch (Exception e) {
			//遇到错误事务回滚
			JDBCUitl.rollback(connection);
			e.printStackTrace();
		}finally{
			//提交事务
			JDBCUitl.commit(connection);
			//把事务设置为自动提交
			JDBCUitl.beginTx(connection,true);
			//最后释放资源
	        JDBCUitl.free(connection, ps);
        }

        
	}
	
	
public static void jdbcUpdateExamQuestion(String sql,List<ExamQuestion> datas) {
	if(datas == null || datas.size() == 0) {
		return;
	}
		//获取数据库链接
		Connection connection = null;
        PreparedStatement ps = null;
		try {
			
			connection = JDBCUitl.getConnection();
			if(connection != null) {
				//开启事务
				JDBCUitl.beginTx(connection,false);
				//开始时间，测试效率用。
				//long beginTime = System.currentTimeMillis();
				//预处理SQL
				ps = connection.prepareStatement(sql);
				int len = datas.size();
				for(int i = 0; i<len; i++) {
					ExamQuestion examQuestion = datas.get(i);
					ps.setInt(1, examQuestion.getAnswerCount());
					ps.setFloat(2, examQuestion.getEmptyCount());
					ps.setFloat(3, examQuestion.getRightAnswerCount());
					ps.setDouble(4, examQuestion.getScore());
					ps.setFloat(5, examQuestion.getAverageScore());
					ps.setFloat(6, examQuestion.getTeamScoringRate());
				
					ps.setInt(7, examQuestion.getGradeRank());
					ps.setFloat(8, examQuestion.getGradeScoringRate());
					ps.setInt(9, examQuestion.getTotalTime());
					ps.setFloat(10, examQuestion.getId());
					 //积攒SQL
	                ps.addBatch();
	                //当积攒到一定程度,就执行一次,并且清空记录
	                if((i+1) % 300==0){
	                    ps.executeBatch();
	                    //提交事务
	        			JDBCUitl.commit(connection);
	                    ps.clearBatch();
	                }

				}
				
				//总条数不是批量值整数倍,则还需要在执行一次
                if(len % 300 != 0){
                    ps.executeBatch();
                    ps.clearBatch();
                }

				//long endTime = System.currentTimeMillis();
				//System.out.println("jdbcUpdateExamQuestion : "+(endTime - beginTime));
			}
			
		}catch (Exception e) {
			//遇到错误事务回滚
			JDBCUitl.rollback(connection);
			e.printStackTrace();
		}finally{
			//提交事务
			JDBCUitl.commit(connection);
			//把事务设置为自动提交
			JDBCUitl.beginTx(connection,true);
			//最后释放资源
	        JDBCUitl.free(connection, ps);
        }

        
	}
	
public static void jdbcUpdateExamQuestionGradeScoreRate(String sql,List<ExamQuestion> datas) {
	if(datas == null || datas.size() == 0) {
		return;
	}
	//获取数据库链接
	Connection connection = null;
    PreparedStatement ps = null;
	try {
		
		connection = JDBCUitl.getConnection();
		if(connection != null) {
			//开启事务
			JDBCUitl.beginTx(connection,false);
			//开始时间，测试效率用。
			//long beginTime = System.currentTimeMillis();
			//预处理SQL
			ps = connection.prepareStatement(sql);
			int len = datas.size();
			for(int i = 0; i<len; i++) {
				ExamQuestion examQuestion = datas.get(i);
				ps.setFloat(1, examQuestion.getGradeScoringRate());
				ps.setInt(2, examQuestion.getId());
				 //积攒SQL
                ps.addBatch();
                //当积攒到一定程度,就执行一次,并且清空记录
                if((i+1) % 300==0){
                    ps.executeBatch();
                    //提交事务
        			JDBCUitl.commit(connection);
                    ps.clearBatch();
                }

			}
			
			//总条数不是批量值整数倍,则还需要在执行一次
            if(len % 300 != 0){
                ps.executeBatch();
                ps.clearBatch();
            }

           
			//long endTime = System.currentTimeMillis();
			//System.out.println("jdbcUpdateExamQuestion : "+(endTime - beginTime));
		}
		
	}catch (Exception e) {
		//遇到错误事务回滚
		JDBCUitl.rollback(connection);
		e.printStackTrace();
	}finally{
		//提交事务
		JDBCUitl.commit(connection);
		//把事务设置为自动提交
		JDBCUitl.beginTx(connection,true);
		//最后释放资源
        JDBCUitl.free(connection, ps);
    }

}
}

