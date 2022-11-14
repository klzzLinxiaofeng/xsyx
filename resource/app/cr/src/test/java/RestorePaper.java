import java.sql.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import platform.education.paper.model.*;
import platform.education.paper.service.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app.xml" })
public class RestorePaper {

//extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	@Qualifier("paPaperService")
	private PaPaperService paPaperService;
	
	@Autowired
	@Qualifier("paPaperCatalogService")
	private PaPaperCatalogService paPaperCatalogService;
	
	@Autowired
	@Qualifier("paPaperTreeService")
	private PaPaperTreeService paPaperTreeService;
	
	@Autowired
	@Qualifier("paQuestionService")
	private PaQuestionService paQuestionService;
	
	@Autowired
	@Qualifier("paQuestionCatalogService")
	private PaQuestionCatalogService paQuestionCatalogService;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private static String oldDatabase = "/old_jdbc.properties";
	
	@Test
	public void test() {
		restore(SchoolCountSQL,SchoolSelectSQL);
		restore(PersonalCountSQL,PersonalSelectSQL);
	}
		
		
	public void restore(String countSQL,String selectSQL) {
		Connection conn=null;
		PreparedStatement ps=null;
		int count=0;
		try {
			conn=JDBCUitl.getConnection(oldDatabase);
			ps=conn.prepareStatement(countSQL);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				count=rs.getInt("COUNT");
				log.debug("总数："+count);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		if(count>0) {
			try {
//				for(int i=0;i<2;i++) {
					ps=conn.prepareStatement(selectSQL); // +" limit "+i+",1");
					ResultSet paperRS=ps.executeQuery();
					while(paperRS.next()) {
						/*
						 * pa_paper
						 */
						PaPaper paper=new PaPaper();
						paper.setId(null);
						paper.setPaperUuid(paperRS.getString("paper_uuid"));
						paper.setTitle(paperRS.getString("title"));
						paper.setPaperType(1);
						paper.setScore((float) paperRS.getDouble("score"));
						paper.setResourceLibId(paperRS.getInt("resource_lib_id"));
						paper.setXepFileId(paperRS.getString("xep_file_id"));
						
						/*校本库*/
						paper.setOwnerMode(paperRS.getInt("owner_mode"));
						
						paper.setOwnerUid(paperRS.getString("owner_uid"));
						paper.setUserId(paperRS.getInt("user_id"));
						paper.setCreateDate(paperRS.getTimestamp("create_date"));
						paper.setModifyDate(paperRS.getTimestamp("modify_date"));
					
						/*默认值失效*/
						paper.setFavCount(0);
						paper.setUsedCount(0);
						paper.setFinishedCount(0);
						
						log.debug("-------------old----");
						log.debug("paperUUID:"+paper.getPaperUuid());
						log.debug("title:"+paper.getTitle());
						log.debug("time:"+paper.getCreateDate());
						log.debug("time:"+paper.getModifyDate());
						
						paper=paPaperService.add(paper);
						
						/*
						 * pa_paper_catalog
						 */
						PaPaperCatalog paperCatalog=new PaPaperCatalog();
						paperCatalog.setId(null);
						paperCatalog.setPaperId(paper.getId());
						paperCatalog.setStageCode(paperRS.getString("stage_code"));
						paperCatalog.setSubjectCode(paperRS.getString("subject_code"));
						paperCatalog.setCatalogCode(paperRS.getString("catalog_code"));
						
						paPaperCatalogService.add(paperCatalog);
						
						/*
						 * pa_paper_tree
						 */
						
						//试卷
						PaPaperTree paPaperTreePaper=new PaPaperTree();
						paPaperTreePaper.setId(null);
						paPaperTreePaper.setPaperId(paper.getId());
						paPaperTreePaper.setParentId(0);
						paPaperTreePaper.setNodeOrder(0);
						paPaperTreePaper.setNodeType(0);
						paPaperTreePaper.setTitle(paper.getTitle());
						paPaperTreePaper.setMemo("");
						paPaperTreePaper.setScore(paper.getScore());
						paPaperTreePaper.setIsDeleted(0);
						paPaperTreePaper.setCreateDate(paper.getCreateDate());
						paPaperTreePaper.setModifyDate(paper.getModifyDate());
						
						paPaperTreePaper=paPaperTreeService.add(paPaperTreePaper);
						
						//默认给个题组
						PaPaperTree paPaperTreeGroup=new PaPaperTree();
						paPaperTreeGroup.setId(null);
						paPaperTreeGroup.setPaperId(paper.getId());
						paPaperTreeGroup.setParentId(paPaperTreePaper.getId());
						paPaperTreeGroup.setNodeOrder(1);
						paPaperTreeGroup.setNodeType(1);
						paPaperTreeGroup.setTitle("");
						paPaperTreeGroup.setMemo("");
						paPaperTreeGroup.setPos(1);
						paPaperTreeGroup.setScore((float) 0);
						paPaperTreeGroup.setIsDeleted(0);
						paPaperTreeGroup.setCreateDate(paper.getCreateDate());
						paPaperTreeGroup.setModifyDate(paper.getModifyDate());
						
						paPaperTreeGroup=paPaperTreeService.add(paPaperTreeGroup);
						
						//试题 包含复合题
						ps=conn.prepareStatement(complexQuestionSQL);
						ps.setString(1,paperRS.getString("old_paper_uuid"));
						ResultSet complexQuestionRS=ps.executeQuery();
						int questionNodeOrder=0;//node_type=2
						int pos=0;
						while(complexQuestionRS.next()) {
							int question_count=complexQuestionRS.getInt("question_count");
							
							//复合题
							if(question_count>1) {
								//新增复合题的题干
								PaQuestion complexQuestion=new PaQuestion();
								complexQuestion.setId(null);
								complexQuestion.setUuid(complexQuestionRS.getString("group_id"));
								complexQuestion.setParentId(0);
								complexQuestion.setQuestionType("complex");
								complexQuestion.setDifficulity((float) complexQuestionRS.getDouble("difficulity"));
								complexQuestion.setCognition("");
								complexQuestion.setSourceType(String.valueOf(complexQuestionRS.getInt("source_type")));
								complexQuestion.setQuestionProperty(0);
								complexQuestion.setContent(complexQuestionRS.getString("extra_content"));
								complexQuestion.setAnswer("[]");
								complexQuestion.setCorrectAnswer("[]");
								complexQuestion.setExplanation("");
								complexQuestion.setOwnerMode(paper.getOwnerMode());
								complexQuestion.setOwnerUid(paper.getOwnerUid());
								complexQuestion.setUserId(paper.getUserId());
								complexQuestion.setUsedCount(0);
								complexQuestion.setFavCount(0);
								complexQuestion.setAnswerCount(0);
								complexQuestion.setRightAnswerCount(0);
								complexQuestion.setTotalTime(0);
								complexQuestion.setTotalTimeCount(0);
								complexQuestion.setAverageTime((float) 0);
								complexQuestion.setIsDeleted(0);
								complexQuestion.setCreateDate(paper.getCreateDate());
								complexQuestion.setModifyDate(paper.getModifyDate());
								
								complexQuestion=paQuestionService.add(complexQuestion);
								
								//题目章节目录
								PaQuestionCatalog questionCatalog=new PaQuestionCatalog();
								questionCatalog.setId(null);
								questionCatalog.setQuestionId(complexQuestion.getId());
								questionCatalog.setStageCode(paperCatalog.getStageCode());
								questionCatalog.setSubjectCode(paperCatalog.getSubjectCode());
//								questionCatalog.setCatalogCode(complexQuestionRS.getString("category_code"));
								questionCatalog.setCatalogCode(paperCatalog.getCatalogCode());
								
								paQuestionCatalogService.add(questionCatalog);
								
								//试卷结构 
								PaPaperTree paperTreeComplexQuestion=new PaPaperTree();
								paperTreeComplexQuestion.setId(null);
								paperTreeComplexQuestion.setPaperId(paper.getId());
								paperTreeComplexQuestion.setParentId(paPaperTreeGroup.getId());
								paperTreeComplexQuestion.setNodeType(2);
								paperTreeComplexQuestion.setNodeOrder(questionNodeOrder++);
								paperTreeComplexQuestion.setPos(0);
								paperTreeComplexQuestion.setNum("0");
								paperTreeComplexQuestion.setTitle("");
								paperTreeComplexQuestion.setMemo("");
								paperTreeComplexQuestion.setQuestionType("complex");
								paperTreeComplexQuestion.setQuestionId(complexQuestion.getId());
								paperTreeComplexQuestion.setScore((float) complexQuestionRS.getDouble("coplexQuestionScore"));	
								paperTreeComplexQuestion.setIsDeleted(0);
								paperTreeComplexQuestion.setCreateDate(paper.getCreateDate());
								paperTreeComplexQuestion.setModifyDate(paper.getModifyDate());
								
								paperTreeComplexQuestion=paPaperTreeService.add(paperTreeComplexQuestion);
								
								//子题目
								ps=conn.prepareStatement(childrenQuestionSQL);
								ps.setString(1, complexQuestion.getUuid());
								ResultSet childrenQuestionRS=ps.executeQuery();
								int childrenQuesionNodeOrder=0;
								while(childrenQuestionRS.next()) {
									PaQuestion childrenQuestion=new PaQuestion();
									childrenQuestion.setId(null);
									childrenQuestion.setUuid(childrenQuestionRS.getString("question_uuid"));
									childrenQuestion.setParentId(complexQuestion.getId());
									childrenQuestion.setQuestionType(childrenQuestionRS.getString("question_type"));
									childrenQuestion.setDifficulity((float) childrenQuestionRS.getDouble("difficulity"));
									childrenQuestion.setCognition(childrenQuestionRS.getString("cognition"));
									childrenQuestion.setSourceType(String.valueOf(childrenQuestionRS.getInt("source_type")));
									childrenQuestion.setQuestionProperty(StaticHandle.handlePaperProperty(childrenQuestionRS.getString("question_type")));
									childrenQuestion.setContent(childrenQuestionRS.getString("content"));
									childrenQuestion.setAnswer(childrenQuestionRS.getString("answer"));
									childrenQuestion.setCorrectAnswer(childrenQuestionRS.getString("correct_answer"));
									childrenQuestion.setExplanation(childrenQuestionRS.getString("explanation"));
									childrenQuestion.setOwnerMode(paper.getOwnerMode());
									childrenQuestion.setOwnerUid(paper.getOwnerUid());
									childrenQuestion.setUserId(paper.getUserId());
									childrenQuestion.setUsedCount(0);
									childrenQuestion.setFavCount(0);
									childrenQuestion.setAnswerCount(0);
									childrenQuestion.setRightAnswerCount(0);
									childrenQuestion.setTotalTime(0);
									childrenQuestion.setTotalTimeCount(0);
									childrenQuestion.setAverageTime((float) 0);
									childrenQuestion.setIsDeleted(0);
									childrenQuestion.setCreateDate(paper.getCreateDate());
									childrenQuestion.setModifyDate(paper.getModifyDate());
									
									childrenQuestion=paQuestionService.add(childrenQuestion);
									
									//题目章节目录
									questionCatalog=new PaQuestionCatalog();
									questionCatalog.setId(null);
									questionCatalog.setQuestionId(childrenQuestion.getId());
									questionCatalog.setStageCode(paperCatalog.getStageCode());
									questionCatalog.setSubjectCode(paperCatalog.getSubjectCode());
//									questionCatalog.setCatalogCode(childrenQuestionRS.getString("category_code"));
									questionCatalog.setCatalogCode(paperCatalog.getCatalogCode());
									paQuestionCatalogService.add(questionCatalog);
									
									//试卷结构 
									PaPaperTree paperTreeChildrenQuestion=new PaPaperTree();
									paperTreeChildrenQuestion.setId(null);
									paperTreeChildrenQuestion.setPaperId(paper.getId());
									paperTreeChildrenQuestion.setParentId(paperTreeComplexQuestion.getId());
									paperTreeChildrenQuestion.setNodeType(3);
									paperTreeChildrenQuestion.setNodeOrder(childrenQuesionNodeOrder++);
									paperTreeChildrenQuestion.setPos(pos++);
									paperTreeChildrenQuestion.setNum(String.valueOf(paperTreeChildrenQuestion.getPos()));
									paperTreeChildrenQuestion.setTitle("");
									paperTreeChildrenQuestion.setMemo("");
									paperTreeChildrenQuestion.setQuestionType(childrenQuestion.getQuestionType());
									paperTreeChildrenQuestion.setQuestionId(childrenQuestion.getId());
									paperTreeChildrenQuestion.setScore((float) childrenQuestionRS.getDouble("score"));	
									paperTreeChildrenQuestion.setIsDeleted(0);
									paperTreeChildrenQuestion.setCreateDate(paper.getCreateDate());
									paperTreeChildrenQuestion.setModifyDate(paper.getModifyDate());
									
									paperTreeChildrenQuestion=paPaperTreeService.add(paperTreeChildrenQuestion);
									
								}
								//单题
							}else {
								PaQuestion complexQuestion=new PaQuestion();
								complexQuestion.setId(null);
								complexQuestion.setUuid(complexQuestionRS.getString("question_uuid"));
								complexQuestion.setParentId(0);
								complexQuestion.setQuestionType(complexQuestionRS.getString("question_type"));
								complexQuestion.setDifficulity((float) complexQuestionRS.getDouble("difficulity"));
								complexQuestion.setCognition(complexQuestionRS.getString("cognition"));
								complexQuestion.setSourceType(String.valueOf(complexQuestionRS.getInt("source_type")));
								complexQuestion.setQuestionProperty(StaticHandle.handlePaperProperty(complexQuestionRS.getString("question_type")));
								complexQuestion.setContent(complexQuestionRS.getString("content"));
								complexQuestion.setAnswer(complexQuestionRS.getString("answer"));
								complexQuestion.setCorrectAnswer(complexQuestionRS.getString("correct_answer"));
								complexQuestion.setExplanation(complexQuestionRS.getString("explanation"));
								complexQuestion.setOwnerMode(paper.getOwnerMode());
								complexQuestion.setOwnerUid(paper.getOwnerUid());
								complexQuestion.setUserId(paper.getUserId());
								complexQuestion.setUsedCount(0);
								complexQuestion.setFavCount(0);
								complexQuestion.setAnswerCount(0);
								complexQuestion.setRightAnswerCount(0);
								complexQuestion.setTotalTime(0);
								complexQuestion.setTotalTimeCount(0);
								complexQuestion.setAverageTime((float) 0);
								complexQuestion.setIsDeleted(0);
								complexQuestion.setCreateDate(paper.getCreateDate());
								complexQuestion.setModifyDate(paper.getModifyDate());
								
								complexQuestion=paQuestionService.add(complexQuestion);
								
								//题目章节目录
								PaQuestionCatalog questionCatalog=new PaQuestionCatalog();
								questionCatalog.setId(null);
								questionCatalog.setQuestionId(complexQuestion.getId());
								questionCatalog.setStageCode(paperCatalog.getStageCode());
								questionCatalog.setSubjectCode(paperCatalog.getSubjectCode());
//								questionCatalog.setCatalogCode(complexQuestionRS.getString("category_code"));
								questionCatalog.setCatalogCode(paperCatalog.getCatalogCode());
								
								paQuestionCatalogService.add(questionCatalog);
								
								//试卷结构 
								PaPaperTree paperTreeQuestion=new PaPaperTree();
								paperTreeQuestion.setId(null);
								paperTreeQuestion.setPaperId(paper.getId());
								paperTreeQuestion.setParentId(paPaperTreeGroup.getId());
								paperTreeQuestion.setNodeType(2);
								paperTreeQuestion.setNodeOrder(questionNodeOrder++);
								paperTreeQuestion.setPos(pos++);
								paperTreeQuestion.setNum(String.valueOf(paperTreeQuestion.getPos()));
								paperTreeQuestion.setQuestionType(complexQuestion.getQuestionType());
								paperTreeQuestion.setTitle("");
								paperTreeQuestion.setMemo("");
								paperTreeQuestion.setQuestionId(complexQuestion.getId());
								paperTreeQuestion.setScore((float) complexQuestionRS.getDouble("score"));	
								paperTreeQuestion.setIsDeleted(0);
								paperTreeQuestion.setCreateDate(paper.getCreateDate());
								paperTreeQuestion.setModifyDate(paper.getModifyDate());
								
								paperTreeQuestion=paPaperTreeService.add(paperTreeQuestion);
								
							}
						}
						paper.setQuestionCount(pos);
						paPaperService.modify(paper);
						
					}
					
//				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
//		
//		Connection newConn=null;
//		newConn=JDBCUitl.getConnection(newDatabase);
//		log.debug(newConn.toString());
	}
	
	/*
	 	只要罗定邦数据：
		AND ps.id=207
	 */
	

	private static String PersonalSelectSQL= "SELECT DISTINCT rr.id, ee.entity_id AS xep_file_id, 2 AS owner_mode, ee.uuid AS paper_uuid, pp.paper_uuid AS old_paper_uuid, pp.title, pp.score, rrl.id AS resource_lib_id,  ps.uuid AS owner_uid, rr.user_id, rr.is_personal, rr.fav_count, rr.create_date,rr.modify_date,4 AS stage_code,pp.subject_code,pp.category_code AS catalog_code FROM res_resource rr JOIN em_exam ee ON rr.object_id=ee.uuid JOIN pa_paper pp ON ee.paper_id=pp.id JOIN res_resource_library rrl ON rr.library_id=rrl.uuid JOIN pj_school ps ON rrl.ower_id=ps.id WHERE rr.is_deleted=0 AND rr.res_type=4 AND rr.verify IN(0,2,4,7,8) AND rr.is_personal=1 AND ps.id=207 ORDER BY rr.modify_date DESC ";
	
	private static String PersonalCountSQL= "SELECT COUNT(DISTINCT rr.id) AS COUNT FROM res_resource rr JOIN em_exam ee ON rr.object_id=ee.uuid JOIN pa_paper pp ON ee.paper_id=pp.id JOIN res_resource_library rrl ON rr.library_id=rrl.uuid JOIN pj_school ps ON rrl.ower_id=ps.id WHERE rr.is_deleted=0 AND rr.res_type=4 AND rr.verify IN(0,2,4,7,8) AND rr.is_personal=1 AND ps.id=207";

	private static String SchoolSelectSQL= "SELECT DISTINCT rr.id,  ee.entity_id AS xep_file_id, 1 AS owner_mode, ee.uuid AS paper_uuid, pp.paper_uuid AS old_paper_uuid, pp.title, pp.score, rrl.id AS resource_lib_id,  ps.uuid AS owner_uid, rr.user_id, rr.is_personal, rr.fav_count, rr.create_date,rr.modify_date,    rcr.stage_code,rcr.subject_code,rcr.catalog_code    FROM res_resource rr    JOIN em_exam ee    ON rr.object_id=ee.uuid    JOIN pa_paper pp    ON ee.paper_id=pp.id     JOIN res_resource_library rrl    ON rr.library_id=rrl.uuid    JOIN pj_school ps    ON rrl.ower_id=ps.id    JOIN res_catalog_resource rcr    ON rr.id=rcr.resource_id    WHERE      rr.is_deleted=0    AND rr.res_type=4    AND rr.verify IN(0,2,4,7,8)    AND rr.is_personal=0  AND ps.id=207  ORDER BY rr.modify_date DESC" ;
	
	private static String SchoolCountSQL= "SELECT COUNT(DISTINCT rr.id) AS COUNT FROM res_resource rr    JOIN em_exam ee    ON rr.object_id=ee.uuid    JOIN pa_paper pp    ON ee.paper_id=pp.id     JOIN res_resource_library rrl    ON rr.library_id=rrl.uuid    JOIN pj_school ps    ON rrl.ower_id=ps.id    JOIN res_catalog_resource rcr    ON rr.id=rcr.resource_id    WHERE      rr.is_deleted=0    AND rr.res_type=4    AND rr.verify IN(0,2,4,7,8)    AND rr.is_personal=0 AND ps.id=207" ;
	
	private static String complexQuestionSQL="SELECT count(*) AS question_count ,SUM(pq.score) AS coplexQuestionScore , ppq.pos AS ppq_pos, pq.pos ,pq.*  FROM pa_question pq JOIN pa_paper_question ppq ON ppq.question_uuid=pq.question_uuid WHERE ppq.paper_uuid= ? GROUP BY ppq.pos ORDER BY ppq.pos";
	
	private static String childrenQuestionSQL="SELECT * FROM pa_question WHERE group_id=? ORDER BY pos";
}
