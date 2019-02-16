import com.zsl.conDiagram.ConnDiagram;
import com.zsl.file.FileRead;
import com.zsl.forkJoinPool.Sorter;
import com.zsl.forkJoinPool.Sum;
import com.zsl.lambda.FunctionInterfaceTest;
import com.zsl.mysql.DataResult;
import com.zsl.reflex.Demo;
import com.zsl.reflex.ReflexData;
import com.zsl.reptile.ConvertHtml2Excel;
import com.zsl.reptile.HTTPUtils;
import com.zsl.reptile.HtmlParser;
import com.zsl.tablehash.TableHash;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        //testReptitleData();//爬虫
        //testCompareData();//比较数据
        //testConDiagram();//连接图
        //testForkJoinPool();//fork分之
        //testLambda();//lamda测试
        testReflex();//反射测试
    }
    /*
     * @ClassName ReptileData
     * @Description 测试爬虫数据
     * @Author 张双亮
     * @Version 1.0
     **/
    public static void testReptitleData(){
        String URL = "http://www.customs.gov.cn/customs/302249/302274/302277/index.html";
        String htmlBody = HTTPUtils.getHtmlbody(URL);
        List<Map<String,String>> list = HtmlParser.getUrlList(htmlBody);
        int index = 0;
        for(Map map : list) {
            if (String.valueOf(map.get("url")).contains("http")) {
                if (index >= 1) {
                    break;
                }
                String childHtmlBody = HTTPUtils.getHtmlbody(String.valueOf(map.get("url")));
                Elements table = HtmlParser.getTagName(childHtmlBody, "table");
                if(table != null){
                    String htmlTable = HTTPUtils.ReplaceSpace(table.outerHtml());
                    //System.out.println(htmlTable);
                    HSSFWorkbook wb = ConvertHtml2Excel.table2Excel(htmlTable);
                    File file = new File("D:\\"+String.valueOf(map.get("title"))+".xls");
                    try{
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        wb.write(fileOutputStream);
                        fileOutputStream.close();
                        index++;
                    }catch(Exception fileError){
                        fileError.printStackTrace();
                    }
                }
            }
        }
    }
    /**
     * @Author 张双亮
     * @Description 测试对比文件与数据库数据
     * @Param $param
     * @return $return
     **/

    public static void testCompareData(){
        FileRead fileRead = new FileRead();
        String srcPath = "";//数据集文件地址
        Set<String> dataRead = fileRead.readData(srcPath);//获取文件中的数据
        TableHash tableHash = new TableHash();
        Map<String,String> map = tableHash.getTableNameByHash(dataRead);//通过hash值获取表名，并将的hash放到相应的数据库名中
        String URL = "";//数据库地址
        String dataName = "";//用户名
        String password = "";//密码
        DataResult dataResult = new DataResult(URL,dataName,password);
        Set<String> resultSet = dataResult.resultMysql(map);//通过MD5获取数据库数据
        String armPath = "";//数据结果集文件地址
        fileRead.writeData(armPath,resultSet);//将结果写入文件中
    }
    
    /**
     * @Author 张双亮
     * @Description 拼图连接测试
     * @Param $param
     * @return $return
     **/

    public static void testConDiagram(){
        String gram = "s--1:2--3--4:5--t,1--4,2--5";
        for(int i = 1; i <= 5; i++){
            System.out.println(""+i+"去掉后："+ConnDiagram.getConnDiagram(""+i+"",gram));
        }
    }
    
    /*
     * @ClassName RecursiveActionTest
     * @Description orkJoinPool就是用来解决这种问题的：将一个大任务拆分成多个小任务后，
     * 使用fork可以将小任务分发给其他线程同时处理，使用join可以将多个线程处理的结果进行汇总；
     * 这实际上就是分治思想的并行版本
     * @Author 张双亮
     * @Version 1.0
     **/
    public static void testForkJoinPool(){
        long[] array = new Random().longs(100_0000).toArray();
        System.out.println(array.length);
        Sorter.sort(array);
        System.out.println(Arrays.toString(array));
        int[] arr = {1,2,3,4,5};
        long res = Sum.sum(arr);
        System.out.println("Sum-Result:"+res);
    }
    /**
     * @Author 张双亮
     * @Description lambda测试
     * @Param $param
     * @return $return
     **/
    public static void testLambda(){
        FunctionInterfaceTest.test();
        FunctionInterfaceTest.testLambda();
    }
    /**
     * @Author 张双亮
     * @Description 测试反射
     * @Param $param
     * @return $return
     **/
    public static void testReflex(){
        List<Demo> list = new ArrayList<>();
        Demo demo = new Demo();
        Demo demo1 = new Demo();
        demo.setId("1");
        demo.setName("s");
        demo.setAge("121");
        demo.setPath("www.baidu.com");
        list.add(demo);
        //ReflexData.getObjFieldByReflex(list);
        int a = 1;
        int b = 2;
        List<String> list1 = new ArrayList<>();
        list1.add("sad");
        list1.add("hgf");
        list1.add("trtry");
        String[] ab = {"1","2","3","4","5"};
        int[] ab1 = {1,2,3,4,5};
        Demo[] demos = {demo,demo1};
        List list2 = new ArrayList();
       //list2.add(ab1);
       // list2.add(demos);
        //list2.add(ab);
//        list2.add(1);
//        list2.add(2);
        Object obj =ReflexData.getObjMethodsByReflex("com.zsl.reflex.Test","getTest",list2);
        System.out.println(obj);
    }
}
