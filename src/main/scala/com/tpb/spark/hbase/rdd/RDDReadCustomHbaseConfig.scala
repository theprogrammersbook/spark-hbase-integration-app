package com.tpb.spark.hbase.rdd

import org.apache.hadoop.hbase.client.{Get, Result}
import org.apache.hadoop.hbase.spark.HBaseContext
import org.apache.hadoop.hbase.spark.HBaseRDDFunctions._
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.{CellUtil, HBaseConfiguration, TableName}
import org.apache.spark.sql.SparkSession

object RDDReadCustomHbaseConfig {

  def main(args: Array[String]): Unit = {

    val spark: SparkSession = SparkSession.builder()
      .master("local[1]")
      .appName("HbaseSparkRDDReadExample")
      .getOrCreate()

    val conf = HBaseConfiguration.create()
    //conf.set("hbase.master","localhost:600") // I have given wrong value but it is able to connect.
    //Becuase zookkerp is main here.
   // conf.set("hbase.zookeeper.property.clientPort", "2000")// This should not work because , in Our case
    // zookeeper is running on port 2181

    /*
    hbase.master : localhost:60010
    hBaseConfiguration.set("fs.defaultFS", "hdfs://ns1"); // nameservices的路径
    hBaseConfiguration.set("dfs.nameservices", "ns1");  //
    hBaseConfiguration.set("dfs.ha.namenodes.ns1", "nn1,nn2"); //namenode的路径
    hBaseConfiguration.set("dfs.namenode.rpc-address.ns1.nn1", "server3:9000"); // namenode 通信地址
    hBaseConfiguration.set("dfs.namenode.rpc-address.ns1.nn2", "server4:9000"); // namenode 通信地址
    // 设置namenode自动切换的实现类
    hBaseConfiguration.set("dfs.client.failover.proxy.provider.ns1", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider")
    hBaseConfiguration.set("hbase.rootdir", "hdfs://ns1/hbase")
    hBaseConfiguration.set("hbase.zookeeper.quorum", "server0,server1,server2")
    hBaseConfiguration.set("hbase.zookeeper.property.clientPort", "2181")
     */

    val hbaseContext = new HBaseContext(spark.sparkContext, conf)
    try{
      val rdd = spark.sparkContext.parallelize(Array(
        Bytes.toBytes("1"),
        Bytes.toBytes("2"),Bytes.toBytes("5")))

      //make sure you import import org.apache.hadoop.hbase.spark.HBaseRDDFunctions._
      val getRdd = rdd.hbaseBulkGet[String](hbaseContext, TableName.valueOf("employee"), 2,
          record => {
            System.out.println("making Get"+record)
            new Get(record)
          },
          (result: Result) => {

            val it = result.listCells().iterator()
            val b = new StringBuilder

            b.append(Bytes.toString(result.getRow) + ":")

            while (it.hasNext) {
              val cell = it.next()
              val q = Bytes.toString(CellUtil.cloneQualifier(cell))
                b.append("(" + q + "," + Bytes.toString(CellUtil.cloneValue(cell)) + ")")
            }
            b.toString()
          }
      )
      //////////////

      getRdd.collect().foreach(v => println(v))

  } finally
  {
    spark.sparkContext.stop()
  }

  }
}
