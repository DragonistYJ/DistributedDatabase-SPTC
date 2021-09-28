package org.example.lesson;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        HBaseController controller = new HBaseController();
        Get get = new Get("0001".getBytes());
        controller.getData("student", get);
    }
}
