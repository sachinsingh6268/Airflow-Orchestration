# MultiLangOrchestrator

**MultiLangOrchestrator** is a demo project that shows how to orchestrate workflows using **multiple programming languages (Python & Java)** within **Apache Airflow** and **Apache Spark**, all running inside Docker containers.

The goal is to illustrate that Python and Java Spark jobs can coexist and run in the same DAG (workflow) using Airflow, making it a great learning tool for multi-language orchestration.

---

## 🧰 Tech Stack

- Apache Airflow `2.7.1 (Python 3.11)`
- Apache Spark `3.4.1` (`bitnami/spark`)
- Java `11`
- Scala `2.13`
- Spark Core `3.5.2`
- Docker & Docker Compose
- PySpark
- Maven (for Java JARs)
- Airflow Spark Provider

---

## 📁 Project Structure

```
.
├── dags/                  # Airflow DAGs (Python)
├── jobs/                  # Spark code (Python & Java)
├── Dockerfile             # Custom Airflow image with Spark, Java, PySpark
├── docker-compose.yml     # Defines Airflow & Spark services
└── airflow.env            # containing Airflow configurations
```

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/MultiLangOrchestrator.git
cd MultiLangOrchestrator
```

### 2. Build the Custom Airflow Image

```bash
docker build -t custom-airflow:latest .
```

### 3. Launch the Stack

```bash
docker-compose up --build
```

### Access UIs

- Airflow: [http://localhost:8080](http://localhost:8080)
- Spark UI: [http://localhost:9090](http://localhost:9090)

---

## 🔁 What It Does

- Runs both PySpark and Java-based Spark jobs inside a single Airflow DAG
- Demonstrates orchestration across multiple languages
- Uses Spark master/worker setup via `bitnami/spark` Docker images

---

## 🧪 Maven Dependencies

Java jobs use the following dependency via Maven:

```xml
<dependency>
  <groupId>org.apache.spark</groupId>
  <artifactId>spark-core_2.13</artifactId>
  <version>3.5.2</version>
</dependency>
```

---

## 🛠 Prerequisites

- Docker & Docker Compose installed
- Java 11 and Maven installed
