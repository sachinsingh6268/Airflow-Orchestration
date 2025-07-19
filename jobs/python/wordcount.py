from pyspark.sql import SparkSession

session = SparkSession.builder.appName("python_spark_session").getOrCreate()

text = "Hello World Hello Airflow Hello Postgres Hello Spark Hello Docker"

words = session.sparkContext.parallelize(text.split(" "))

wordCounts = words.map(lambda word:(word,1)).reduceByKey(lambda a,b : a+b)

for wc in wordCounts.collect():
    print(wc[0], wc[1])

session.stop()
