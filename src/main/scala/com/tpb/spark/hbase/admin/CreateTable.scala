package com.tpb.spark.hbase.admin

import org.apache.hadoop.hbase.{HBaseConfiguration, HColumnDescriptor, HTableDescriptor, TableName}
import org.apache.hadoop.hbase.client.ConnectionFactory

object CreateTable extends App {

  //Creating HBaseConfiguration
  val configuration = HBaseConfiguration.create()
  // Create Connection
  val connection = ConnectionFactory.createConnection()
  // Get Admin
  val admin = connection.getAdmin
  // Provide Table Name
  val tableDescriptor = new HTableDescriptor(TableName.valueOf("Persons2"))
  // Add Column Family
  tableDescriptor.addFamily(new HColumnDescriptor("personal data"))
  tableDescriptor.addFamily(new HColumnDescriptor("professional data"))
  // Add the collum to Admin

  admin.createTable(tableDescriptor)
  // Check the success message
  println(" Table is created ...")
}
