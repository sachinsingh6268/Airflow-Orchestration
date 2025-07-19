from airflow import DAG
from datetime import datetime
from airflow.operators.python import PythonOperator
from airflow.providers.apache.spark.operators.spark_submit import SparkSubmitOperator

# defining a DAG
dag = DAG(
    dag_id = "spark_with_python",
    default_args = {
        "owner" : "Sachin Singh",
        "start_date" : datetime(2025,7,13)
    },
    schedule_interval = "@daily",
)

# defining tasks
start = PythonOperator(
    task_id = "start",
    python_callable = lambda : print("Job started"),
    dag = dag
)

python_job = SparkSubmitOperator(
    task_id = "python_job",
    conn_id = "spark-conn",
    application = "jobs/python/wordcount.py",
    dag = dag
)

java_job = SparkSubmitOperator(
    task_id = "java_job",
    conn_id = "spark-conn",
    application = "jobs/java/spark-job/target/spark-job-1.0-SNAPSHOT.jar",
    java_class = "com.sachin.spark.WordCountJob",
    dag = dag
)

end = PythonOperator(
    task_id = "end",
    python_callable = lambda : print("Job Completed"),
    dag = dag
)

start >> [python_job, java_job] >> end


