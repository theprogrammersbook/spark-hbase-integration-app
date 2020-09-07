package com.tpb.spark.hbase.client

import org.apache.hadoop.hbase.client.{ConnectionFactory, Delete, Get}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}

object DeleteColumn extends App {
  //Creating HBaseConfiguration
  val configuration = HBaseConfiguration.create()
  val connection = ConnectionFactory.createConnection(configuration)
  //Get table from Connection
  val table = connection.getTable(TableName.valueOf("emp"))

  val delete = new Delete(Bytes.toBytes("2"))
  delete.addColumn(Bytes.toBytes("personal data"), Bytes.toBytes("name"))
  delete.addFamily(Bytes.toBytes("professional data"))
  // deleting the data
  table.delete(delete)
  // closing the HTable object
  table.close()

}
