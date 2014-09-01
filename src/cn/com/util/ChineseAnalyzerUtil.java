package cn.com.util;
/**
 * @author Xianxiaofei
 * @date:2014年7月30日 下午3:28:03
 */

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

public class ChineseAnalyzerUtil {

	/**
	 * getTextDef对传入的参数进行分词，并统计好每个次的频率
	 * 代码很简单，主要介绍下IK中的类，IKSegmenter是分词的主要类，其参数分别是分词的句子或者文章，后面的参数是是否开启智能模式，
	 * 不开启就按最小词义分。 分词的结果是Lexeme这个类，用其中的getLexemeText()方法就能取出相关的分词结果。
	 * 
	 */
	public  Map<String, Integer> getTextDef(String text)
			throws IOException {
		Map<String, Integer> wordsFren = new HashMap<String, Integer>();
		//true使用智能分词策略
		IKSegmenter ikSegmenter = new IKSegmenter(new StringReader(text), true);
		//词元对象
		Lexeme lexeme;
		//next分词，获取下一个词元
		while ((lexeme = ikSegmenter.next()) != null) {
			if (lexeme.getLexemeText().length() > 1) {
				//Map  containsKey(String Key)判断key有没有对应的value值；有，则返回true
				if (wordsFren.containsKey(lexeme.getLexemeText())) {
					wordsFren.put(lexeme.getLexemeText(),
							//存在把当前分词的次数加1
							wordsFren.get(lexeme.getLexemeText()) + 1);
				} else {
					//不存在就添加
					wordsFren.put(lexeme.getLexemeText(), 1);
				}
			}
		}
		return wordsFren;
	}

	/**
	 * 接下来是计算词频，将分词结果和出现次数放到一个map结构中，map的value对应了词的出现次数。这里注意一下，
	 * 我只记录两个字及两个字以上的分词结果。
	 * 
	 * @param wordsFrenMaps
	 * @param topWordsCount
	 */
	public  List<Map.Entry<String, Integer>> getAnalyzerKeywordsString(Map<String, Integer> wordsFrenMaps) {

		List<Map.Entry<String, Integer>> oldWordFrenList = new ArrayList<Map.Entry<String, Integer>>(
				wordsFrenMaps.entrySet());
		//这个方法主要对分词结果及词频按照出现次数排序，没有自己去写实现，主要借用了collections的sort方法。
		Collections.sort(oldWordFrenList,
				new Comparator<Map.Entry<String, Integer>>() {
					public int compare(Map.Entry<String, Integer> obj1,
							Map.Entry<String, Integer> obj2) {
						return obj2.getValue() - obj1.getValue();
					}
				});
		return oldWordFrenList;
	}
	
	//对已有问题进行分词处理
	public static void main(String args[]) throws IOException {
		
		ChineseAnalyzerUtil chineseAnalyzerUtil = new ChineseAnalyzerUtil();
		List<QuestionBean> listQuestionBeans = new ArrayList<QuestionBean>();
		List<AnswerBean> listAnswerBeans = new 	ArrayList<AnswerBean>();
		
		QuestionDaoImp questionDaoImp = new QuestionDaoImp();
		AnswerDaoImp answerDaoImp = new AnswerDaoImp();
		
		//这里所传参数只在Dao方法中的无参数的语句使用
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
			//取分词频率最高的前20
			for (int i1 = 0; i1 < 20 && i1 < map.size(); i1++) {
				Map.Entry<String, Integer> wordFrenEntry = map.get(i1);
				questionContext2.append(wordFrenEntry.getKey()+",");
				countTopN.append(wordFrenEntry.getValue()+",");
				}
			questionKeywords.setQuesitons_keywords_topN(questionContext2.toString());
			questionKeywords.setQuestions_keywords_counts(countTopN.toString());
			
			if(questionsKeywordsDaoImp.addQuestionsKeywordsBean(questionKeywords)){
				System.out.println("第"+(i+1)+"个问题"+"success！");
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
			//取分词频率最高的前20
			for (int i1 = 0; i1 < 20 && i1 < map.size(); i1++) {
				Map.Entry<String, Integer> wordFrenEntry = map.get(i1);
				answerContext2.append(wordFrenEntry.getKey()+",");
				countTopN.append(wordFrenEntry.getValue()+",");
				}
			answersKeywordsBean.setAnswers_keywords_topN(answerContext2.toString());
			answersKeywordsBean.setAnswers_keywords_counts(countTopN.toString());
			
			if(answersKeywordsDaoImp.addAnswersKeywordsBean(answersKeywordsBean)){
				System.out.println("第"+(j+1)+"个回答"+"success！");
			}
			
			
		}

	}
	
	public List<String> getSearchKeyWords(String text)
			throws IOException {
		List<String> listKeyWords = new ArrayList<String>();
		//true使用智能分词策略
		IKSegmenter ikSegmenter = new IKSegmenter(new StringReader(text), true);
		//词元对象
		Lexeme lexeme;
		//next分词，获取下一个词元
		while ((lexeme = ikSegmenter.next()) != null) {
			if (lexeme.getLexemeText().length() > 1) {
				//查找是否已经存在该分词
				boolean bool = false;
				for(int i=0; i<listKeyWords.size();i++){
					if(listKeyWords.get(i).equals(lexeme.getLexemeText())){
						bool = true;
						break;
					}
				}
				if (!bool) {
					listKeyWords.add(lexeme.getLexemeText());
				} 
			}
		}
		return listKeyWords;
	}

}
