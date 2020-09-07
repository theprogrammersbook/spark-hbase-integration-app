package com.tpb.spark.hbase.client

import org.apache.hadoop.hbase.client.{ConnectionFactory, Scan}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}

object ScanTable extends App {
  //Creating HBaseConfiguration
  val configuration = HBaseConfiguration.create()
  val connection = ConnectionFactory.createConnection(configuration)
  //Get table from Connection
  val table = connection.getTable(TableName.valueOf("Employee"))
  // Instantiating the Scan class
  val scan = new Scan()
  // Scanning the required columns
  scan.addColumn(Bytes.toBytes("professional data"), Bytes.toBytes("salary"))
  // Getting the scan result
  val scanner = table.getScanner(scan)
  // Reading values from scan result
  var result = scanner.next
  while ( {
    result != null
  }) {
    System.out.println("Found row : " + result)
    result = scanner.next
  }
  // closing the scanner
  table.close()

}
