package org.example.lesson;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.CompareOperator;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptor;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.TableDescriptorBuilder;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.RowFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.function.BiConsumer;

public class HBaseController {
    private Connection connection;

    public HBaseController() {
        this("127.0.0.1:60010");
    }

    public HBaseController(String hMaster) {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.master", hMaster);
        try {
            this.connection = ConnectionFactory.createConnection(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName, List<String> columns) {
        try {
            Admin admin = this.connection.getAdmin();
            TableName tn = TableName.valueOf(tableName);
            if (admin.tableExists(tn)) {
                System.out.println("表已存在！");
            } else {
                TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(tn);
                List<ColumnFamilyDescriptor> columnFamilyDescriptors = new ArrayList<>();
                for (String col : columns) {
//                    ColumnFamilyDescriptorBuilder.newBuilder(col.getBytes()).setMaxVersions(3).build();
//                    ColumnFamilyDescriptorBuilder.of(col)
                    columnFamilyDescriptors.add(ColumnFamilyDescriptorBuilder.of(col));
                }
                tableDescriptorBuilder.setColumnFamilies(columnFamilyDescriptors);
                admin.createTable(tableDescriptorBuilder.build());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void alterTable() {
        try {
            Admin admin = this.connection.getAdmin();
//            admin.addColumnFamily();
//            admin.deleteColumnFamily();
//            admin.modifyColumnFamily();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteTable(String tableName) {
        try {
            TableName tn = TableName.valueOf(tableName);
            Admin admin = this.connection.getAdmin();
            // 也是需要先禁用表，才能删除表
            admin.disableTable(tn);
            admin.deleteTable(tn);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void putData(String tableName, Put put) {
//        Put put = new Put("1234".getBytes());
//        put.addColumn("columnFamily".getBytes(), "columnQualifier".getBytes(), "value".getBytes());
//        put.addColumn("columnFamily".getBytes(), "columnQualifier".getBytes(), 1, "value".getBytes());

        try {
            TableName tn = TableName.valueOf(tableName);
            Table table = this.connection.getTable(tn);
            table.put(put);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteData(String tableName, Delete delete) {
//        Delete delete = new Delete("1234".getBytes());
//        delete.addColumn("columnFamily".getBytes(), "columnQualifier".getBytes());
//        delete.addColumn("columnFamily".getBytes(), "columnQualifier".getBytes(), 1);
//        delete.addFamily("columnFamily".getBytes());

        try {
            TableName tn = TableName.valueOf(tableName);
            Table table = this.connection.getTable(tn);
            table.delete(delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getData(String tableName, Get get) {
        try {
            TableName tn = TableName.valueOf(tableName);
            Table table = this.connection.getTable(tn);
            Result result = table.get(get);

            // 考虑时间戳
            // 通过<列族,<列标识符,<时间戳,值>>>遍历
            result.getMap().forEach((family, qualifierAndTsValue) -> {
                System.out.println(new String(family));
                qualifierAndTsValue.forEach((qualifier, tsAndValue) -> {
                    System.out.println("--" + new String(qualifier));
                    tsAndValue.forEach((ts, value) -> {
                        System.out.println("----" + ts);
                        System.out.println("----" + new String(value));
                    });
                });
            });

            // 上面那串代码不用lambda写就是这样
            result.getMap().forEach(new BiConsumer<byte[], NavigableMap<byte[], NavigableMap<Long, byte[]>>>() {
                @Override
                public void accept(byte[] family, NavigableMap<byte[], NavigableMap<Long, byte[]>> qualifierAndTsValue) {
                    System.out.println(new String(family));
                    qualifierAndTsValue.forEach(new BiConsumer<byte[], NavigableMap<Long, byte[]>>() {
                        @Override
                        public void accept(byte[] qualifier, NavigableMap<Long, byte[]> tsAndValue) {
                            System.out.println("--" + new String(qualifier));
                            tsAndValue.forEach(new BiConsumer<Long, byte[]>() {
                                @Override
                                public void accept(Long ts, byte[] value) {
                                    System.out.println("----" + ts);
                                    System.out.println("----" + new String(value));
                                }
                            });
                        }
                    });
                }
            });

            // 不考虑时间戳
            for (byte[] family : result.getMap().keySet()) {
                NavigableMap<byte[], byte[]> familyMap = result.getFamilyMap(family);
                familyMap.forEach((qualifier, value) -> {
                    System.out.println(new String(qualifier));
                    System.out.println(new String(value));
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void scanData(String tableName) {
        try {
            TableName tn = TableName.valueOf(tableName);
            Table table = this.connection.getTable(tn);
            Scan scan = new Scan();
            scan.setFilter(new RowFilter(CompareOperator.LESS_OR_EQUAL, new BinaryComparator("1234".getBytes())));
            ResultScanner scanner = table.getScanner(scan);
            for (Result result : scanner) {
                // 同getData方法里处理result流程一致
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
