package excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: online_edu_parent
 * @description:
 * @author: qinda
 * @create: 2020-07-10 19:45
 **/
public class TestExcel {
    public static void main(String[] args) {
        //写操作
//        String filename = "D:\\IdeaProject\\online_edu_parent\\service\\service_edu\\src\\test\\java\\excel\\01.xlsx";
//        // 1. 文件名 2. 实体类
//        EasyExcel.write(filename, DemoData.class).sheet("学生列表").doWrite(TestExcel.getData());


        //读操作
        String filename = "D:\\IdeaProject\\online_edu_parent\\service\\service_edu\\src\\test\\java\\excel\\01.xlsx";
        EasyExcel.read(filename,DemoData.class, new ExcelListener()).sheet().doRead();


    }

    public static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for (int i=0;i<10;i++){
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("qin"+i);
            list.add(data);
        }
        return list;
    }

}


