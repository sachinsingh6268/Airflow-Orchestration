FROM apache/airflow:2.7.1-python3.11

USER root
RUN apt-get update && \
    apt-get install -y openjdk-11-jdk && \
    apt-get clean

ENV JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:$PATH"

USER airflow

# please only use any module or library versions that are compatible with base image
RUN pip install --no-cache-dir pyspark==3.4.1 apache-airflow-providers-apache-spark==4.4.0
