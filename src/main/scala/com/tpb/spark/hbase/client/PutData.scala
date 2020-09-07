package com.tpb.spark.hbase.client

import org.apache.hadoop.hbase.client.{ConnectionFactory, Put}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}

object PutData extends App {
  //Creating HBaseConfiguration
  val configuration = HBaseConfiguration.create()
  val connection = ConnectionFactory.createConnection(configuration)
  //Get table from Connection
  val table = connection.getTable(TableName.valueOf("Persons"))
  // Creating row
  val put = new Put(Bytes.toBytes("2"))
  // Adding data
  put.addColumn(Bytes.toBytes("personal data"), Bytes.toBytes("name"), Bytes.toBytes("nagaraju"))
  put.addColumn(Bytes.toBytes("personal data"), Bytes.toBytes("city"), Bytes.toBytes("Narpala"))
  put.addColumn(Bytes.toBytes("personal data"), Bytes.toBytes("town"), Bytes.toBytes("Anantapur"))
  put.addColumn(Bytes.toBytes("personal data"), Bytes.toBytes("state"), Bytes.toBytes("Andrapradesh"))

  put.addColumn(Bytes.toBytes("professional data"), Bytes.toBytes("salary"), Bytes.toBytes(10000))
  put.addColumn(Bytes.toBytes("professional data"), Bytes.toBytes("empCode"), Bytes.toBytes("MU100"))
  put.addColumn(Bytes.toBytes("professional data"), Bytes.toBytes("project"), Bytes.toBytes("Sampling"))
  // Put the data to Hbase
  table.put(put)
  table.close()
}
