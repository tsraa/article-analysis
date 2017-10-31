package com.xiaohuli.hanlp.dto;

import java.io.Serializable;

/**
 * 请求参数
 * 标题：Title
 文本内容：Content
 返回结果：WillGet
 关键词个数：KeyWordsLimit
 重点句子的字数限制：SentenceWordsLimit
 摘要文本的字数限制：SummaryWordsLimit

 */
public class RequestParam implements Serializable{

    private String title;

    private String content;

    private Integer willGet;

    private Integer keyWordsLimit;

    private Integer sentenceWordsLimit;

    private Integer summaryWordsLimit;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getWillGet() {
        return willGet;
    }

    public void setWillGet(Integer willGet) {
        this.willGet = willGet;
    }

    public Integer getKeyWordsLimit() {
        return keyWordsLimit;
    }

    public void setKeyWordsLimit(Integer keyWordsLimit) {
        this.keyWordsLimit = keyWordsLimit;
    }

    public Integer getSentenceWordsLimit() {
        return sentenceWordsLimit;
    }

    public void setSentenceWordsLimit(Integer sentenceWordsLimit) {
        this.sentenceWordsLimit = sentenceWordsLimit;
    }

    public Integer getSummaryWordsLimit() {
        return summaryWordsLimit;
    }

    public void setSummaryWordsLimit(Integer summaryWordsLimit) {
        this.summaryWordsLimit = summaryWordsLimit;
    }
}
