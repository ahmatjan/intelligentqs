package cn.com.util;
/**
 * @author Xianxiaofei
 * @date:2014��7��30�� ����3:28:03
 */

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import cn.com.beans.AnswerBean;
import cn.com.beans.AnswersKeywordsBean;
import cn.com.beans.QuestionBean;
import cn.com.beans.QuestionsKeywordsBean;
import cn.com.daos.AnswerDaoImp;
import cn.com.daos.AnswersKeywordsDaoImp;
import cn.com.daos.QuestionDaoImp;
import cn.com.daos.QuestionsKeywordsDaoImp;
//import sun.misc.Sort;

public class ChineseAnalyzerUtil {

	/**
	 * getTextDef�Դ���Ĳ������зִʣ���ͳ�ƺ�ÿ���ε�Ƶ��
	 * ����ܼ򵥣���Ҫ������IK�е��࣬IKSegmenter�Ƿִʵ���Ҫ�࣬������ֱ��Ƿִʵľ��ӻ������£�����Ĳ������Ƿ�������ģʽ��
	 * �������Ͱ���С����֡� �ִʵĽ����Lexeme����࣬�����е�getLexemeText()��������ȡ����صķִʽ����
	 * 
	 */
	public  Map<String, Integer> getTextDef(String text)
			throws IOException {
		Map<String, Integer> wordsFren = new HashMap<String, Integer>();
		//trueʹ�����ִܷʲ���
		IKSegmenter ikSegmenter = new IKSegmenter(new StringReader(text), true);
		//��Ԫ����
		Lexeme lexeme;
		//next�ִʣ���ȡ��һ����Ԫ
		while ((lexeme = ikSegmenter.next()) != null) {
			if (lexeme.getLexemeText().length() > 1) {
				//Map  containsKey(String Key)�ж�key��û�ж�Ӧ��valueֵ���У��򷵻�true
				if (wordsFren.containsKey(lexeme.getLexemeText())) {
					wordsFren.put(lexeme.getLexemeText(),
							//���ڰѵ�ǰ�ִʵĴ�����1
							wordsFren.get(lexeme.getLexemeText()) + 1);
				} else {
					//�����ھ����
					wordsFren.put(lexeme.getLexemeText(), 1);
				}
			}
		}
		return wordsFren;
	}

	/**
	 * �������Ǽ����Ƶ�����ִʽ���ͳ��ִ����ŵ�һ��map�ṹ�У�map��value��Ӧ�˴ʵĳ��ִ���������ע��һ�£�
	 * ��ֻ��¼�����ּ����������ϵķִʽ����
	 * 
	 * @param wordsFrenMaps
	 * @param topWordsCount
	 */
	public  List<Map.Entry<String, Integer>> getAnalyzerKeywordsString(Map<String, Integer> wordsFrenMaps) {

		List<Map.Entry<String, Integer>> oldWordFrenList = new ArrayList<Map.Entry<String, Integer>>(
				wordsFrenMaps.entrySet());
		//���������Ҫ�Էִʽ������Ƶ���ճ��ִ�������û���Լ�ȥдʵ�֣���Ҫ������collections��sort������
		Collections.sort(oldWordFrenList,
				new Comparator<Map.Entry<String, Integer>>() {
					public int compare(Map.Entry<String, Integer> obj1,
							Map.Entry<String, Integer> obj2) {
						return obj2.getValue() - obj1.getValue();
					}
				});
		return oldWordFrenList;
	}
	
	//������������зִʴ���
	public static void main(String args[]) throws IOException {
		
		ChineseAnalyzerUtil chineseAnalyzerUtil = new ChineseAnalyzerUtil();
		List<QuestionBean> listQuestionBeans = new ArrayList<QuestionBean>();
		List<AnswerBean> listAnswerBeans = new 	ArrayList<AnswerBean>();
		
		QuestionDaoImp questionDaoImp = new QuestionDaoImp();
		AnswerDaoImp answerDaoImp = new AnswerDaoImp();
		
		//������������ֻ��Dao�����е��޲��������ʹ��
		listQuestionBeans = questionDaoImp.getAllQuestions(0, 0);
		listAnswerBeans = answerDaoImp.getAnswerByUserId(0);
		QuestionsKeywordsDaoImp questionsKeywordsDaoImp = new QuestionsKeywordsDaoImp();
		AnswersKeywordsDaoImp answersKeywordsDaoImp = new AnswersKeywordsDaoImp();
		
		for(int i=0; i<listQuestionBeans.size();i++){
			int questionId = listQuestionBeans.get(i).getQuestion_id();
			StringBuffer questionContext = new StringBuffer(listQuestionBeans.get(i).getQuestion_title()+listQuestionBeans.get(i).getQuestion_description());
			StringBuffer questionContext2 = new StringBuffer();
			StringBuffer countTopN = new StringBuffer();
			List<Map.Entry<String, Integer>> map = chineseAnalyzerUtil.getAnalyzerKeywordsString(chineseAnalyzerUtil.getTextDef(questionContext.toString()));

			
			QuestionsKeywordsBean questionKeywords = new QuestionsKeywordsBean();
			
			questionKeywords.setQuestion_id(questionId);
			//ȡ�ִ�Ƶ����ߵ�ǰ20
			for (int i1 = 0; i1 < 20 && i1 < map.size(); i1++) {
				Map.Entry<String, Integer> wordFrenEntry = map.get(i1);
				questionContext2.append(wordFrenEntry.getKey()+",");
				countTopN.append(wordFrenEntry.getValue()+",");
				}
			questionKeywords.setQuesitons_keywords_topN(questionContext2.toString());
			questionKeywords.setQuestions_keywords_counts(countTopN.toString());
			
			if(questionsKeywordsDaoImp.addQuestionsKeywordsBean(questionKeywords)){
				System.out.println("��"+(i+1)+"������"+"success��");
			}
			
		}
		
		for(int j=0; j<listAnswerBeans.size();j++){
			int answers_id = listAnswerBeans.get(j).getAnswer_id();
			StringBuffer answerContext = new StringBuffer(listAnswerBeans.get(j).getAnswer_description());
			StringBuffer answerContext2 = new StringBuffer();
			StringBuffer countTopN = new StringBuffer();
			List<Map.Entry<String, Integer>> map = chineseAnalyzerUtil.getAnalyzerKeywordsString(chineseAnalyzerUtil.getTextDef(answerContext.toString()));

			AnswersKeywordsBean answersKeywordsBean = new AnswersKeywordsBean();
			
			answersKeywordsBean.setAnswers_id(answers_id);
			//ȡ�ִ�Ƶ����ߵ�ǰ20
			for (int i1 = 0; i1 < 20 && i1 < map.size(); i1++) {
				Map.Entry<String, Integer> wordFrenEntry = map.get(i1);
				answerContext2.append(wordFrenEntry.getKey()+",");
				countTopN.append(wordFrenEntry.getValue()+",");
				}
			answersKeywordsBean.setAnswers_keywords_topN(answerContext2.toString());
			answersKeywordsBean.setAnswers_keywords_counts(countTopN.toString());
			
			if(answersKeywordsDaoImp.addAnswersKeywordsBean(answersKeywordsBean)){
				System.out.println("��"+(j+1)+"���ش�"+"success��");
			}
			
			
		}

	}

}
