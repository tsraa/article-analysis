package com.xiaohuli.hanlp.controller;


import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.Viterbi.ViterbiSegment;
import com.hankcs.hanlp.seg.common.Term;
import com.xiaohuli.hanlp.HanlpApplication;
import com.xiaohuli.hanlp.dto.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ApiController {

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
        Segment shortestSegment = new ViterbiSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
        List<Term> segmentList = shortestSegment.seg(document);
        return segmentList;
    }

    private List<String> summary(String document,int limit){
        List<String> summaryList = HanLP.extractSummary(document, limit);
        return summaryList;
    }
}
