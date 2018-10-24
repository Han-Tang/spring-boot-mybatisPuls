/**
 * @author SargerasWang
 */
package cn.xunhang.common.excel;

import cn.xunhang.modules.basicmanage.entity.InfoProductExcel;
import cn.xunhang.modules.basicmanage.entity.Informations;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 测试导入Excel 97/2003
 */
public class TestImportExcel {

  @Test
  public void importXls() throws FileNotFoundException {
    File f=new File("src/test/resources/test.xls");
    InputStream inputStream= new FileInputStream(f);
    
    ExcelLogs logs =new ExcelLogs();
    Collection<InfoProductExcel> importExcel = ExcelUtil.getInstance().importExcel(InfoProductExcel.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs , 0);
    
    for(InfoProductExcel m : importExcel){
      System.out.println(m);
    }
  }

  @Test
  public void importXlsx() throws FileNotFoundException {
    String path = "src/test/resources/test.xls";
    File f=new File(path);
    InputStream inputStream= new FileInputStream(f);

    ExcelLogs logs =new ExcelLogs();
//    Collection<Map> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs , 0);
    List list = ExcelUtil.getInstance().importExcel(inputStream,InfoProductExcel.class,f.getName());
//    for(Map m : importExcel){
      System.out.println(list);
//    }
  }

}
