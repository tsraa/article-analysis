package com.xiaohuli.hanlp.controller;


import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.Viterbi.ViterbiSegment;
import com.hankcs.hanlp.seg.common.Term;
import com.xiaohuli.hanlp.HanlpApplication;
import com.xiaohuli.hanlp.dto.RequestParam;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ApiController {

    @ApiOperation(value = "新闻文本提取接口", httpMethod = "POST" ,notes = "WillGet=1时， 只返回关键词列表（每个关键词及其出现的次数，KeyWordsLimit 为返回的关键词个数,这时 SentenceWordsLimit,SummaryWordsLimit 可以忽略)<br>" +
            "WillGet=2时， 只返回分词结果，按原文的结构分词，段落，句子顺序，标点都不变(这时 SentenceWordsLimit,SummaryWordsLimit 可以忽略)<br>" +
            "WillGet=3时， 返回按原文的结构分词的结果；同时返回重点句子；字数限制 SentenceWordsLimit 内，先按句子的重要性降序排列，然后按原文的顺序返回给我。 (这时SummaryWordsLimit 可以忽略)<br>" +
            "WillGet=4时， 返回按原文的结构分词的结果、总字数限制在SentenceWordsLimit以内的重点句子、总字数限制在SummaryWordsLimit 以内的摘要。<br>")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "title", value = "标题", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "content", value = "正文", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "willGet", value = "返回结果类型", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "keyWordsLimit", value = "关键词个数", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "sentenceWordsLimit", value = "重点句子的字数限制", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "summaryWordsLimit", value = "摘要文本的字数限制",  paramType = "query", dataType = "Integer")
    })
    @RequestMapping("/api/request")
    @ResponseBody
    public Map<String,Object> request(RequestParam param){

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("status","0");
        resultMap.put("msg","OK");
        if(param == null){
            resultMap.put("status","-1");
            resultMap.put("msg","参数不能为空");
            return resultMap;
        }
        switch (param.getWillGet()){
            case 1:
                List<String> keywordList = HanLP.extractKeyword(param.getContent(),param.getKeyWordsLimit());
                resultMap.put("keywords",keywordList);
                break;
            case 2:
                resultMap.put("segments",segment(param.getContent()));
                break;
            case 3:
                resultMap.put("segments",segment(param.getContent()));
                resultMap.put("summary",summary(param.getContent(),param.getSummaryWordsLimit()));
                break;
            case 4:
                resultMap.put("segments",segment(param.getContent()));
                resultMap.put("summary",summary(param.getContent(),param.getSummaryWordsLimit()));
                break;
        }
        return resultMap;
    }

    private List<Term> segment(String document){
        Segment segment = HanLP.newSegment();
        List<Term> segmentList = segment.seg(document);
        return segmentList;
    }

    private List<String> summary(String document,int limit){
        List<String> summaryList = HanLP.extractSummary(document, limit);
        return summaryList;
    }
}
