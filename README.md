# spark-hbase-connector-examples

This application explains how to integratte spark with hbase

I have installed the following softwares.

##Added these in  ~/.bashrc

Java Environment Variables Setting

export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export PATH=$PATH:$JAVA_HOME/bin

Hadoop Environment Varaibles

export HADOOP_HOME=/usr/local/softwares/hadoop-2.10.0
export PATH=$PATH:$HADOOP_HOME/bin:$HADOOP_HOME/sbin

Hbase Environment Variables

https://stackoverflow.com/questions/31980584/how-to-connect-spark-sql-to-remote-hive-metastore-via-thrift-protocol-with-no

export HBASE_HOME=/usr/local/softwares/hbase-2.2.3
export PATH=$PATH:$HBASE_HOME/bin
export CLASSPATH=$CLASSPATH:$HBASE_HOME/lib/*

Spark Environment Variables
export SPARK_HOME=/usr/local/softwares/spark-2.4.5
export PATH=$PATH:$SPARK_HOME/bin:$SPARK_HOME/sbin

##Configuration
1. Have used default hadoop-pseudo distributed mode 
2. Have used default hbase-pseudo distributed mode

##How to add external jar file to maven build
  Step 1: Add the jar file to local maven repository
  
`mvn install:install-file -Dfile=spark-core_2.11-1.5.2.logging.jar -DgroupId=com.local.spark.logging -DartifactId=spark-logging -Dversion=1.0.0 -Dpackaging=jar`

Step 2: Now add this dependency to pom.xml file

        <dependency>
            <groupId>com.local.spark.logging</groupId>
            <artifactId>spark-logging</artifactId>
            <version>1.0.0</version>
        </dependency>



##How to run the Application
The following is the get operation, before calling this operation, we have to insert some data using 
`com.tpb.spark.hbase.admin.CreateTable
com.tpb.spark.hbase.client.PutData
`

In any IDE Simply Run the App.

`HBaseBulkGetExample`

## we can create jar file with the help of any IDE , if IDE is not able to create jar file then we can use the following way

##How to create jar file  from maven command line

`mvn clean install -U`


##How to submit his application to spark
`spark-submit --class "com.tpb.spark.hbase.streaming.HBaseBulkGetExample" --master local[*] --packages org.apache.hbase:hbase-common:2.0.0-alpha4 spark-hbase-integration-1.0-SNAPSHOT.jar
`
