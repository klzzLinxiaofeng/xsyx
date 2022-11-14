package platform.education.paper.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import platform.education.generalTeachingAffair.model.ExamQuestion;
import platform.education.paper.model.UserFileQuestion;
import platform.education.paper.model.UserQuestion;
import platform.education.paper.model.UserWrong;


/**
 * 2017-08-02
 * 
 * JDBC操作类
 * @author 【pantq】
 *
 */
public class JDBCHandle {

	public static void jdbcInsertUserQuestion(List<UserQuestion> datas) {
		if(datas == null || datas.size() == 0) {
			return;
		}
		
		String sql = "INSERT INTO pa_user_question(paper_uuid,paper_question_id,question_uuid,user_id,team_id,answer,score,is_correct,create_date,modify_date,type,owner_id,answer_time,object_id,uuid) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
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
					UserQuestion userQuestion = datas.get(i);
					
					//入参数
					ps.setString(1, userQuestion.getPaperUuid());
					ps.setInt(2, userQuestion.getPaperQuestionId());
					ps.setString(3, userQuestion.getQuestionUuid());
					ps.setInt(4, userQuestion.getUserId());
					ps.setInt(5, userQuestion.getTeamId());
					ps.setString(6, userQuestion.getAnswer());
					ps.setDouble(7, userQuestion.getScore());
					ps.setInt(8, userQuestion.getIsCorrect()==true?1:0);
					ps.setTimestamp(9, new Timestamp(userQuestion.getCreateDate().getTime()));
					ps.setTimestamp(10, new Timestamp(userQuestion.getModifyDate().getTime()));
					ps.setInt(11, userQuestion.getType());
					ps.setInt(12, userQuestion.getOwnerId());
					ps.setInt(13, userQuestion.getAnswerTime());
					
					if(userQuestion.getObjectId() != null) {
						ps.setInt(14, userQuestion.getObjectId());
					}else {
						ps.setInt(14, 0);
					}
					ps.setString(15, userQuestion.getUuid());
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
	
	
	
	public static void jdbcInsertUserWrong(List<UserWrong> datas) {
		if(datas == null || datas.size() == 0) {
			return;
		}
		
		String sql = "INSERT INTO pa_user_wrong (create_date,user_id,paper_uuid,modify_date,paper_question_id,question_uuid,answer,is_correct,is_deleted,is_offline,last_time,last_answer,right_count,wrong_count,user_question_uuid)	VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
					UserWrong userWrong = datas.get(i);
				
					//入参数
					ps.setTimestamp(1, new Timestamp(userWrong.getCreateDate().getTime()));
					ps.setInt(2, userWrong.getUserId());
					ps.setString(3, userWrong.getPaperUuid());
					ps.setTimestamp(4, new Timestamp(userWrong.getModifyDate().getTime()));
					ps.setInt(5, userWrong.getPaperQuestionId());
					ps.setString(6, userWrong.getQuestionUuid());
					ps.setString(7, userWrong.getAnswer());
					ps.setInt(8, 0);
					ps.setInt(9, 0);
					ps.setInt(10, 0);
					//ps.setInt(11, 0);
					ps.setTimestamp(11, new Timestamp(new Date().getTime()));
					ps.setString(12, userWrong.getLastAnswer());
					ps.setInt(13, 0);
					ps.setInt(14, 0);
					ps.setString(15, userWrong.getUserQuestionUuid());
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
	
	
public static void jdbcInsertUserFileQuestion(List<UserFileQuestion> datas) {
	if(datas == null || datas.size() == 0) {
		return;
	}
	
		String sql = "INSERT INTO pa_user_file_question(user_file_id,create_date,modify_date,user_question_uuid)	VALUES (?,?,?,?)"; 
			
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
					UserFileQuestion userFileQuestion = datas.get(i);
					ps.setInt(1, userFileQuestion.getUserFileId());
					//ps.setFloat(2, userFileQuestion.getUserQuestionId());
					ps.setTimestamp(2, new Timestamp(userFileQuestion.getCreateDate().getTime()));
					ps.setTimestamp(3, new Timestamp(userFileQuestion.getModifyDate().getTime()));
					ps.setString(4, userFileQuestion.getUserQuestionUuid());
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

