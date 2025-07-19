package com.sachin.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaPairRDD;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.regex.Pattern;


import scala.Tuple2;

public class WordCountJob
{
    private static final Pattern SPACE = Pattern.compile(" ");
    public static void main( String[] args )
    {
        // Create Spark Configuration file
        SparkConf conf = new SparkConf().setAppName("word count").setMaster("local");
        // Creating SparkContext using defined Configuration
        JavaSparkContext sc = new JavaSparkContext(conf);

        List<String> sentences = new ArrayList<String>();
        sentences.addAll(Arrays.asList("Hello Docker","Hello Sachin","Hello Spark","Hello Java","Hello Airflow","Hello Spark-SQL",
                        "Hello Spark-Core","Hello Spark-Master","Hello Spark-Worker","Bye Spark and Docker"));

        // parallelize the data
        JavaRDD<String> lines = sc.parallelize(sentences);

        // split into words and perform the count
        JavaPairRDD<String,Integer> counts = lines
                            .flatMap(sentence -> Arrays.asList(SPACE.split(sentence)).iterator())
                            .mapToPair(word -> new Tuple2<>(word,1))
                            .reduceByKey((a,b) -> a+b);

        // collect & print the word counts
        List<Tuple2<String,Integer>> output = counts.collect();
        for (Tuple2<?,?> tuple : output){
            System.out.println(tuple._1() + ": " + tuple._2());
        }

        // close the spark context & session
        sc.close();
    }
}
