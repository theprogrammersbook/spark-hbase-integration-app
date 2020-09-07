package com.tpb.spark.hbase.admin

import org.apache.hadoop.hbase.client.ConnectionFactory
import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}

object ListTables extends App {
        //Creating HBaseConfiguration
          val configuration = HBaseConfiguration.create()
         // Create Connection
          val connection = ConnectionFactory.createConnection()
        // Get Admin
         val admin = connection.getAdmin
        // Get list of table names
         val tableNames = admin.listTableNames()
         // Check Table Names
             tableNames.foreach( tName => println(tName.getNameAsString))
      // Check is table exists
      println(admin.tableExists(TableName.valueOf("Customer")))
  admin.close()
}
