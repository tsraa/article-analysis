package com.xiaohuli.hanlp.service;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.CRF.CRFSegment;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.Viterbi.ViterbiSegment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.SpeedTokenizer;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class AnalysisService {

    public void analysisImportentSentence(String title,String content){
        Segment shortestSegment = new ViterbiSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
        List<Term> segmentList = shortestSegment.seg(title);
    }

    public static void main(String[] args) {
        String content = "华夏经纬网11月2日讯：据台湾媒体报道，赦扁议题一直是台湾地区领导人蔡英文上任以来避谈的话题，但“行政院长”赖清德日前在“立法院”备询时，曾公开表态仍支持特赦陈水扁。蔡英文2日在索罗门与媒体茶叙时表示，如果觉得社会有一定共识了，“我们就来考虑”。\n" +
                "\n" +
                "据报道，民进党“立委”许智杰日前在“立法院”质询赖清德关于特赦陈水扁议题时表示，赖清德任台南市长时曾表态支持，现在是否还支持？赖清德也公开表示，“我个人是支持”，并强调他的想法没变。\n" +
                "\n" +
                "不过，虽然上任后已多次被要求特赦陈水扁，但蔡英文始终没有正面响应，她今天在媒体茶叙被询问对特赦扁的态度时，首度表示，这件事社会持续都有一些讨论，她也会听这些人士的想法及说法，“如果我们觉得社会有一定共识了，时间也适合的话，我们就来考虑”。";
//        List<String> keywordList = HanLP.extractPhrase(content,10);
//        //        List<String> summaryList = HanLP.extractSummary(content, 10);
//        keywordList.stream().forEach(System.out::println);
        String title = "蔡英文首度回应特赦陈水扁";
//        Segment nShortSegment = new NShortSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
//
//        Segment shortestSegment = new ViterbiSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
//        List<Term> segmentList = nShortSegment.seg(title);
//        segmentList.stream().forEach(System.out::println);
//        Segment segment = new CRFSegment();
//        segment.enablePartOfSpeechTagging(true);
        Segment segment = HanLP.newSegment();
        System.out.println(segment.seg(title));
        List<Term> segTerms = segment.seg(title);
        Set<String> set = new LinkedHashSet<>();
        String[] list = content.split("，|。");
        for(Term term : segTerms){
            for(String c : list){
                if(c.contains(term.word)){
                    set.add(c);
                }
            }
        }
        set.stream().forEach(System.out::println);
    }
}
