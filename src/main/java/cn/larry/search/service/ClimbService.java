package cn.larry.search.service;

import cn.larry.search.dao.AnswerMapper;
import cn.larry.search.dao.QuestionMapper;
import cn.larry.search.dao.TopicMapper;
import cn.larry.search.dao.ZhihuUserMapper;
import cn.larry.search.model.Answer;
import cn.larry.search.model.Question;
import cn.larry.search.util.RandomString;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ClimbService {

    @Resource
    AnswerMapper answerMapper;

    @Resource
    QuestionMapper questionMapper;
    @Resource
    TopicMapper topicMapper;
    @Resource
    ZhihuUserMapper zhihuUserMapper;

    public static void main(String[] args) throws IOException, InterruptedException, ParseException {
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring/applicationcontext.xml");
        ClimbService climbService = (ClimbService) context.getBean("climbService");
        List<String> topicToCLimb = Arrays.asList(Constants.MACHINE_LEARNING_UNANSWER,
                Constants.DEEP_LEADNING_UNANSWER, Constants.nlp, Constants.cv, Constants.voicere, Constants.ai, Constants.rfl);
        for (String topic : topicToCLimb) {
            climbService.climbTopic(topic);
        }

    }

    public void climbTopic(String topicPath) throws IOException, InterruptedException, ParseException {
        try {
            String baseUrl = Constants.ZHIUHU_URL + topicPath;
            Document doc = Jsoup.connect(baseUrl).get();
            Elements pagerElements = doc.select("div.border-pager div.zm-invite-pager  span");
            int page = 1;
            if (pagerElements.size() >= 3) {
                page = parseInt(pagerElements.get(pagerElements.size() - 2).text());
            }
            for (int i = 1; i < page; i++) {
                climbPageQuestions(topicPath + "?page=" + i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private List<Question> climbPageQuestions(String path) throws IOException, ParseException, InterruptedException {
        String baseUrl = Constants.ZHIUHU_URL + path;
        Document doc = Jsoup.connect(baseUrl).get();
        Elements elements = doc.select("#zh-topic-questions-list .question-item");
        if (elements != null)
            for (Element element : elements) {
                try {
                    System.out.println(element.select("h2").text());
                    String questionUrl = element.select("h2 a").attr("href");
                    Question question = climbQuestion(questionUrl);
                    System.out.println(toJson(question));
                    questionMapper.insertQuestion(question);
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        return null;
    }


    public Question climbQuestion(String path) throws IOException, ParseException {
        try {
            Question question = new Question();
            int questionZhihuId = parseInt(path.replace("/question/", ""));
            String baseUrl = Constants.ZHIUHU_URL + path;
            Document doc = Jsoup.connect(baseUrl).get();
            String name = doc.select("h1.QuestionHeader-title").text();
            List<String> tags = new ArrayList<>();
            Elements elements = doc.select("div.QuestionHeader-topics");
            for (Element element : elements) {
                tags.add(element.text());
            }
            String des = doc.select("div.QuestionHeader-detail").text();
            Elements elements1 = doc.select("div.NumberBoard-item strong");
            int followNum = parseInt(elements1.get(0).text().replaceAll(",", ""));
            int lookTime = parseInt(elements1.get(1).text().replaceAll(",", ""));
            question.setName(name);
            question.setDescription(des);
            question.setFollowNum(followNum);
            question.setLookTime(lookTime);
            question.setTag(String.join("-", tags));
            question.setCreateTime(new Date());
            question.setId(questionZhihuId);
            System.out.println("question: " + name);
            Elements answers = doc.select("div#QuestionAnswers-answers div.list-item");
            for (Element element : answers) {
                Answer answer = parseAnswer(element, questionZhihuId);
                answerMapper.insertAnswer(answer);
            }
            return question;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private int parseInt(String num) {
        try {
            return Integer.parseInt(num);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static final ObjectMapper mapper = new ObjectMapper();

    private static String toJson(Object object) throws JsonProcessingException {
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        return mapper.writeValueAsString(object);
    }

    public Answer parseAnswer(Element answerElement, int questionId) throws ParseException {
        Answer answer = new Answer();
        try {
            answer.setQuestionId(questionId);
            Element answerUser = answerElement.selectFirst("span.AuthorInfo-name a");
            String zhihu_id = null;
            if (answerUser != null) {
                String name = answerUser.text();
                zhihu_id = answerUser.attr("href");
                if (zhihu_id != null) zhihu_id = zhihu_id.replace("/people/", "");
            }
            if (StringUtils.isBlank(zhihu_id)) {
                zhihu_id = "nimin_" + RandomString.randomString(10);
            }
            answer.setAuthorid(zhihu_id);

            String num = answerElement.selectFirst("div.ContentItem-actions button.VoteButton--up").text();
            int likeNum = parseInt(num.trim());
            String content = answerElement.select("div.RichContent div.RichContent-inner").text();
            String time = answerElement.select("div.RichContent div.ContentItem-time").text().replace("编辑于", "");
            time = regularGetString(time, "\\d{4}-\\d{1,2}-\\d{1,2}");
            Date answerTime = parseDate(time, "yyyy-MM-dd");
            System.out.println("answer :" + content);
            answer.setAnswerTime(answerTime);
            String commentNumStr = answerElement.selectFirst("div.ContentItem-actions>button.ContentItem-action").text().replace("条评论", "").trim();
            int commentNum = 0;
            try {
                commentNum = parseInt(commentNumStr);
            } catch (Exception e) {
            }
            answer.setAnswerTime(answerTime);
            answer.setCreateTime(new Date());
            answer.setCommentNum(commentNum);
            answer.setLength(content.length());
            answer.setLikeNum(likeNum);
            answer.setUpdateTime(new Date());
            answer.setContent(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answer;
    }

    private static Date parseDate(String strDate, String pattern) throws ParseException {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            return simpleDateFormat.parse(strDate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String regularGetString(String string, String regExp) {
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(string);
        if (matcher.find())
            return matcher.group();
        else return null;
    }


}
