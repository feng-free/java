import com.zsl.file.FileRead;
import com.zsl.mysql.DataResult;
import com.zsl.tablehash.TableHash;

import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
//        FileRead fileRead = new FileRead();
//        String srcPath = "";//数据集文件地址
//        Set<String> dataRead = fileRead.readData(srcPath);//获取文件中的数据
//        TableHash tableHash = new TableHash();
//        Map<String,String> map = tableHash.getTableNameByHash(dataRead);//通过hash值获取表名，并将的hash放到相应的数据库名中
//        String URL = "";//数据库地址
//        String dataName = "";//用户名
//        String password = "";//密码
//        DataResult dataResult = new DataResult(URL,dataName,password);
//        Set<String> resultSet = dataResult.resultMysql(map);//通过MD5获取数据库数据
//        String armPath = "";//数据结果集文件地址
//        fileRead.writeData(armPath,resultSet);//将结果写入文件中
    
        //String name = "abner chai";
        String name = null;
        assert (name!=null):"变量name为空null";
        System.out.println(name);
    
    
    
    }
}
