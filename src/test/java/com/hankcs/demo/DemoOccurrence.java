/*
 * <summary></summary>
 * <author>Hankcs</author>
 * <email>me@hankcs.com</email>
 * <create-date>2016-05-28 AM9:44</create-date>
 *
 * <copyright file="DemoOccurrence.java" company="码农场">
 * Copyright (c) 2008-2016, 码农场. All Right Reserved, http://www.hankcs.com/
 * This source is subject to Hankcs. Please contact Hankcs to get more information.
 * </copyright>
 */
package com.hankcs.demo;

import com.hankcs.hanlp.corpus.occurrence.Occurrence;
import com.hankcs.hanlp.corpus.occurrence.PairFrequency;
import com.hankcs.hanlp.corpus.occurrence.TermFrequency;
import com.hankcs.hanlp.corpus.occurrence.TriaFrequency;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 演示词共现统计
 *
 * @author hankcs
 */
public class DemoOccurrence
{
    public static void main(String[] args)
    {
        Occurrence occurrence = new Occurrence();
        occurrence.addAll(readFromProperties("json.properties","json","测试"));
        occurrence.compute();

        Set<Map.Entry<String, TermFrequency>> uniGram = occurrence.getUniGram();
        for (Map.Entry<String, TermFrequency> entry : uniGram)
        {
            TermFrequency termFrequency = entry.getValue();
            System.out.println(termFrequency);
        }

        Set<Map.Entry<String, PairFrequency>> biGram = occurrence.getBiGram();
        for (Map.Entry<String, PairFrequency> entry : biGram)
        {
            PairFrequency pairFrequency = entry.getValue();
            if (pairFrequency.isRight())
                System.out.println(pairFrequency);
        }

        Set<Map.Entry<String, TriaFrequency>> triGram = occurrence.getTriGram();
        for (Map.Entry<String, TriaFrequency> entry : triGram)
        {
            TriaFrequency triaFrequency = entry.getValue();
            if (triaFrequency.isRight())
                System.out.println(triaFrequency);
        }
    }

    /**
     * 从指定的配置文件里面根据指定的key读取value
     * @param propertiesPath 配置文件地址
     * @param key            key值
     * @param defaultValue   如果该key没有找到，则返回的默认值
     * @return value值
     */
    public static String readFromProperties(String propertiesPath, String key, String defaultValue) {

        try {
            Properties properties = new Properties();

            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            if (loader == null)
            {  // IKVM (v.0.44.0.5) doesn't set context classloader
                loader = DemoOccurrence.class.getClassLoader();
            }
            properties.load(loader.getResourceAsStream(propertiesPath));




//            InputStream in = DemoOccurrence.class.getResourceAsStream(json.properties);
//            properties.load(in);
//            in.close();
            if (properties.containsKey(key)) {
                return properties.getProperty(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;

    }
}
