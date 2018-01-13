package cn.larry.search.model;

import java.util.Date;

public class ZhihuUser {

    String zhihuId;
    int gander;
    int type;
    String name;
    String description;

    int followerNum;
    int likeNum;
    int thankNum;
    int answerNum;
    int QuestionNum;
    int essayNum;
    String education;
    String job;
    String industry;
    int collectionNum;
    Date createTime;
    Date updatezTime;


    public String getZhihuId() {
        return zhihuId;
    }

    public void setZhihuId(String zhihuId) {
        this.zhihuId = zhihuId;
    }

    public int getGander() {
        return gander;
    }

    public void setGander(int gander) {
        this.gander = gander;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFollowerNum() {
        return followerNum;
    }

    public void setFollowerNum(int followerNum) {
        this.followerNum = followerNum;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getThankNum() {
        return thankNum;
    }

    public void setThankNum(int thankNum) {
        this.thankNum = thankNum;
    }

    public int getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(int answerNum) {
        this.answerNum = answerNum;
    }

    public int getQuestionNum() {
        return QuestionNum;
    }

    public void setQuestionNum(int questionNum) {
        QuestionNum = questionNum;
    }

    public int getEssayNum() {
        return essayNum;
    }

    public void setEssayNum(int essayNum) {
        this.essayNum = essayNum;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public int getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(int collectionNum) {
        this.collectionNum = collectionNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdatezTime() {
        return updatezTime;
    }

    public void setUpdatezTime(Date updatezTime) {
        this.updatezTime = updatezTime;
    }
}
