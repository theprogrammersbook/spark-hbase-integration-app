package com.tpb.spark.hbase.client

import org.apache.hadoop.hbase.client.{ConnectionFactory, Get}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}

object GetData extends App {
  //Creating HBaseConfiguration
  val configuration = HBaseConfiguration.create()
  val connection = ConnectionFactory.createConnection(configuration)
  //Get table from Connection
  val table = connection.getTable(TableName.valueOf("emp"))
  // Get 1st row values
  val get = new Get(Bytes.toBytes("1"))
  // check this  1st row in table
  val result = table.get(get)
  // get name value
  val nameValues = result.getValue(Bytes.toBytes("personal data"), Bytes.toBytes("name"))
  val name = Bytes.toString(nameValues)
   // get salary value
  val salaryValues = result.getValue(Bytes.toBytes("professional data"), Bytes.toBytes("salary"))
  val salary = Bytes.toInt(salaryValues)
  // Check Name and salary value
  println("1st row : => Name :" + name + " , salary :" + salary)
  table.close()

}
