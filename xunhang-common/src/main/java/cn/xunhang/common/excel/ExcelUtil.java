package cn.xunhang.common.excel;

import cn.xunhang.common.exception.RRException;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Excel工具类
 * 该类实现了将一组对象转换为Excel表格，并且可以从Excel表格中读取到一组List对象中
 * 该类利用了BeanUtils框架中的反射完成
 * 使用该类的前提，在相应的实体对象上通过ExcelReources来完成相应的注解
 * @author tyj
 * @email 36780272@qq.com
 * @date 2018年10月18日 10:40:10
 */
public class ExcelUtil {

    private Logger LG = LoggerFactory.getLogger(ExcelUtil.class);

    private static ExcelUtil eu = new ExcelUtil();
    private ExcelUtil(){}

    public static ExcelUtil getInstance() {
        return eu;
    }

    private final static String XLS = "xls";
    private final static String XLSX = "xlsx";

    /**
     * 用来验证excel与Vo中的类型是否一致 <br>
     * Map<栏位类型,只能是哪些Cell类型>
     */
    private static Map<Class<?>, CellType[]> validateMap = new HashMap<>();

    static {
        validateMap.put(String[].class, new CellType[]{CellType.STRING});
        validateMap.put(Double[].class, new CellType[]{CellType.NUMERIC});
        validateMap.put(String.class, new CellType[]{CellType.STRING});
        validateMap.put(Double.class, new CellType[]{CellType.NUMERIC});
        validateMap.put(Date.class, new CellType[]{CellType.NUMERIC, CellType.STRING});
        validateMap.put(Integer.class, new CellType[]{CellType.NUMERIC});
        validateMap.put(Float.class, new CellType[]{CellType.NUMERIC});
        validateMap.put(Long.class, new CellType[]{CellType.NUMERIC});
        validateMap.put(Boolean.class, new CellType[]{CellType.BOOLEAN});
    }


    /**--------------------------------------------------导出---------------------------------------------------------//


    /**
     * 不是基于模板的，导出对象到Excel，直接新建一个Excel完成导出，基于路径的导出
     * @param outPath 导出路径
     * @param objs 对象列表
     * @param clz 对象类型
     */
    public void exportExcel(String outPath,List objs,Class clz) {
        Workbook wb = handlerExcel(objs, clz);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(outPath);
            wb.write(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fos!=null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 不是基于模板的，导出对象到Excel，直接新建一个Excel完成导出，基于流
     * @param os 输出流
     * @param objs 对象列表
     * @param clz 对象类型
     */
    public <T> void exportExcel(OutputStream os,List<T> objs,Class clz) {
        try {
            Workbook wb = handlerExcel(objs, clz);
            wb.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 基于模板的导出，将对象转换为Excel并且导出，导出到流
     * @param datas 模板中的替换的常量数据
     * @param template 模板路径
     * @param os 输出流
     * @param objs 对象列表
     * @param clz 对象的类型
     * @param isClasspath 模板是否在classPath路径下
     */
    public void exportExcelByTemplate(Map<String,String> datas, String template, OutputStream os, List objs, Class clz, boolean isClasspath) {
        try {
            ExcelTemplate et = handlerExcel(template, objs, clz, isClasspath);
            et.replaceFinalData(datas);
            et.wirteToStream(os);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 基于模板的导出，将对象转换为Excel并且导出，导出到一个具体的路径中
     * @param datas 模板中的替换的常量数据
     * @param template 模板路径
     * @param outPath 输出路径
     * @param objs 对象列表
     * @param clz 对象的类型
     * @param isClasspath 模板是否在classPath路径下
     */
    public void exportExcelByTemplate(Map<String,String> datas,String template,String outPath,List objs,Class clz,boolean isClasspath) {
        ExcelTemplate et = handlerExcel(template, objs, clz, isClasspath);
        et.replaceFinalData(datas);
        et.writeToFile(outPath);
    }

    /**
     * 基于模板的导出，将对象转换为Excel并且导出，导出到流，基于Properties作为常量数据
     * @param prop 基于Properties的常量数据模型
     * @param template 模板路径
     * @param os 输出流
     * @param objs 对象列表
     * @param clz 对象的类型
     * @param isClasspath 模板是否在classPath路径下
     */
    public void exportExcelByTemplate(Properties prop, String template, OutputStream os, List objs, Class clz, boolean isClasspath) {
        ExcelTemplate et = handlerExcel(template, objs, clz, isClasspath);
        et.replaceFinalData(prop);
        et.wirteToStream(os);
    }

    /**
     * 基于模板的导出，将对象转换为Excel并且导出，导出到一个具体的路径中,基于Properties作为常量数据
     * @param prop 基于Properties的常量数据模型
     * @param template 模板路径
     * @param outPath 输出路径
     * @param objs 对象列表
     * @param clz 对象的类型
     * @param isClasspath 模板是否在classPath路径下
     */
    public void exportExcelByTemplate(Properties prop,String template,String outPath,List objs,Class clz,boolean isClasspath) {
        ExcelTemplate et = handlerExcel(template, objs, clz, isClasspath);
        et.replaceFinalData(prop);
        et.writeToFile(outPath);
    }

    /**
     * 利用JAVA的反射机制，将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上<br>
     * 用于单个sheet
     *
     * @param <T>
     * @param headers 表格属性列名数组
     * @param dataset 需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
     *                javabean属性的数据类型有基本数据类型及String,Date,String[],Double[]
     * @param out     与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     */
    public <T> void exportExcel(Map<String,String> headers, Collection<T> dataset, OutputStream out) {
        exportExcel(headers, dataset, out, null);
    }

    /**
     * 利用JAVA的反射机制，将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上<br>
     * 用于单个sheet
     *
     * @param <T>
     * @param headers 表格属性列名数组
     * @param dataset 需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
     *                javabean属性的数据类型有基本数据类型及String,Date,String[],Double[]
     * @param out     与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     * @param pattern 如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
     */
    public <T> void exportExcel(Map<String,String> headers, Collection<T> dataset, OutputStream out, String pattern) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet();

        handlerExcel(sheet, headers, dataset, pattern);
        try {
            workbook.write(out);
        } catch (IOException e) {
            LG.error(e.toString(), e);
        }
    }

    /**
     * 利用JAVA的反射机制，将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上<br>
     * 基于数组数据
     * @param datalist
     * @param out
     */
    public void exportExcel(String[][] datalist, OutputStream out) {
        try {
            // 声明一个工作薄
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 生成一个表格
            HSSFSheet sheet = workbook.createSheet();

            for (int i = 0; i < datalist.length; i++) {
                String[] r = datalist[i];
                HSSFRow row = sheet.createRow(i);
                for (int j = 0; j < r.length; j++) {
                    HSSFCell cell = row.createCell(j);
                    //cell max length 32767
                    if (r[j].length() > 32767) {
                        r[j] = "--此字段过长(超过32767),已被截断--" + r[j];
                        r[j] = r[j].substring(0, 32766);
                    }
                    cell.setCellValue(r[j]);
                }
            }
            //自动列宽
            if (datalist.length > 0) {
                int colcount = datalist[0].length;
                for (int i = 0; i < colcount; i++) {
                    sheet.autoSizeColumn(i);
                }
            }
            workbook.write(out);
        } catch (IOException e) {
            LG.error(e.toString(), e);
        }
    }

    /**
     * 利用JAVA的反射机制，将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上<br>
     * 用于多个sheet
     *
     * @param <T>
     * @param sheets {@link ExcelSheet}的集合
     * @param out    与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     */
    public <T> void exportExcel(List<ExcelSheet<T>> sheets, OutputStream out) {
        exportExcel(sheets, out, null);
    }

    /**
     * 利用JAVA的反射机制，将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上<br>
     * 用于多个sheet
     *
     * @param <T>
     * @param sheets  {@link ExcelSheet}的集合
     * @param out     与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     * @param pattern 如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
     */
    public <T> void exportExcel(List<ExcelSheet<T>> sheets, OutputStream out, String pattern) {
        if (CollectionUtils.isEmpty(sheets)) {
            return;
        }
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        for (ExcelSheet<T> sheet : sheets) {
            // 生成一个表格
            HSSFSheet hssfSheet = workbook.createSheet(sheet.getSheetName());
            handlerExcel(hssfSheet, sheet.getHeaders(), sheet.getDataset(), pattern);
        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            LG.error(e.toString(), e);
        }
    }

    /**
     * 处理对象转换为Excel  基于模板导出
     * @param template
     * @param objs
     * @param clz
     * @param isClasspath
     * @return
     */
    public ExcelTemplate handlerExcel (String template, List objs, Class clz, boolean isClasspath)  {
        ExcelTemplate et = ExcelTemplate.getInstance();
        try {
            if(isClasspath) {
                et.readTemplateByClasspath(template);
            } else {
                et.readTemplateByPath(template);
            }
            List<ExcelHeader> headers = getHeaders(clz);
            Collections.sort(headers);
            //输出标题
            et.createNewRow();
            for(ExcelHeader eh:headers) {
                et.createCell(eh.getTitle());
            }
            //输出值
            for(Object obj:objs) {
                et.createNewRow();
                for(ExcelHeader eh:headers) {
                    et.createCell(BeanUtils.getProperty(obj,eh.getMethodName()));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return et;
    }

    /**
     * 在excel中写入数据
     * @param objs
     * @param clz
     * @return
     */
    private <T> Workbook handlerExcel(List<T> objs, Class clz) {
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            HSSFSheet sheet = wb.createSheet();
            HSSFRow row = sheet.createRow(0);
            HSSFCellStyle style = getStyle(wb);
            List<ExcelHeader> headers = getHeaders(clz);
            Collections.sort(headers);
            row.setHeight((short)450);
            //写标题
            for(int i=0;i<headers.size();i++) {
                HSSFCell cell = row.createCell(i);
                String value = headers.get(i).getTitle();
                setCellValue(cell,value,null,i,null,row,style);
            }
            //写数据
            for(int i=0;i<objs.size();i++) {
                row = sheet.createRow(i+1);
                T obj = objs.get(i);
                for(int j=0;j<headers.size();j++) {
                    HSSFCell cell = row.createCell(j);
                    Field field = clz.getDeclaredField(headers.get(j).getMethodName());
                    field.setAccessible(true);
                    Object value = field.get(obj);
                    setCellValue(cell,value,null,i,null,row,style);
                }
            }
            // 设定自动宽度
            for (int i = 0; i < headers.size(); i++) {
                sheet.autoSizeColumn(i);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return wb;
    }

    /**
     * 每个sheet的写入
     * @param sheet   页签
     * @param headers 表头
     * @param dataset 数据集合
     * @param pattern 日期格式
     */
    private <T> void handlerExcel(HSSFSheet sheet, Map<String,String> headers, Collection<T> dataset, String pattern) {
        //时间格式默认"yyyy-MM-dd"
        if (StringUtils.isEmpty(pattern)){
            pattern = "yyyy-MM-dd";
        }
        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        // 标题行转中文
        Set<String> keys = headers.keySet();
        Iterator<String> it1 = keys.iterator();
        String key = "";    //存放临时键变量
        int c= 0;   //标题列数
        while (it1.hasNext()){
            key = it1.next();
            if (headers.containsKey(key)) {
                HSSFCell cell = row.createCell(c);
                HSSFRichTextString text = new HSSFRichTextString(headers.get(key));
                cell.setCellValue(text);
                c++;
            }
        }

        // 遍历集合数据，产生数据行
        Iterator<T> it = dataset.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T t = it.next();
            try {
                if (t instanceof Map) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> map = (Map<String, Object>) t;
                    int cellNum = 0;
                    //遍历列名
                    Iterator<String> it2 = keys.iterator();
                    while (it2.hasNext()){
                        key = it2.next();
                        if (!headers.containsKey(key)) {
                            LG.error("Map 中 不存在 key [" + key + "]");
                            continue;
                        }
                        Object value = map.get(key);
                        HSSFCell cell = row.createCell(cellNum);

                        cellNum = setCellValue(cell,value,pattern,cellNum,null,row,null);

                        cellNum++;
                    }
                } else {
                    List<FieldForSortting> fields = sortFieldByAnno(t.getClass());
                    int cellNum = 0;
                    for (int i = 0; i < fields.size(); i++) {
                        HSSFCell cell = row.createCell(cellNum);
                        Field field = fields.get(i).getField();
                        field.setAccessible(true);
                        Object value = field.get(t);

                        cellNum = setCellValue(cell,value,pattern,cellNum,field,row,null);

                        cellNum++;
                    }
                }
            } catch (Exception e) {
                LG.error(e.toString(), e);
            }
        }
        // 设定自动宽度
        for (int i = 0; i < headers.size(); i++) {
            sheet.autoSizeColumn(i);
        }
    }

    /**
     * 获取列表头
     * @param clz
     * @return
     */
    private List<ExcelHeader> getHeaders(Class clz) {
        List<ExcelHeader> headers = new ArrayList<ExcelHeader>();
        Field[] fields = clz.getDeclaredFields();
        for(Field field : fields) {
            if(field.isAnnotationPresent(ExcelCell.class)) {
                ExcelCell er = field.getAnnotation(ExcelCell.class);
                headers.add(new ExcelHeader(er.title(),er.index(),field.getName()));
            }
        }
        return headers;
    }

    /**
     * 向单元格写入数据，设置格式
     * @param cell
     * @param value
     * @param pattern
     * @param cellNum
     * @param field
     * @param row
     * @param style
     * @return
     */
    private int setCellValue(HSSFCell cell,Object value,String pattern,int cellNum,Field field,HSSFRow row,HSSFCellStyle style){
        String textValue = null;
        if (value instanceof Integer) {
            int intValue = (Integer) value;
            cell.setCellValue(intValue);
        } else if (value instanceof Float) {
            float fValue = (Float) value;
            cell.setCellValue(fValue);
        } else if (value instanceof Double) {
            double dValue = (Double) value;
            cell.setCellValue(dValue);
        } else if (value instanceof Long) {
            long longValue = (Long) value;
            cell.setCellValue(longValue);
        } else if (value instanceof Boolean) {
            boolean bValue = (Boolean) value;
            if(bValue){
                cell.setCellValue("是");
            }else {
                cell.setCellValue("否");
            }
        } else if (value instanceof Date) {
            Date date = (Date) value;
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            textValue = sdf.format(date);
        } else if (value instanceof String[]) {
            String[] strArr = (String[]) value;
            for (int j = 0; j < strArr.length; j++) {
                String str = strArr[j];
                cell.setCellValue(str);
                if (j != strArr.length - 1) {
                    cellNum++;
                    cell = row.createCell(cellNum);
                }
            }
        } else if (value instanceof Double[]) {
            Double[] douArr = (Double[]) value;
            for (int j = 0; j < douArr.length; j++) {
                Double val = douArr[j];
                // 值不为空则set Value
                if (val != null) {
                    cell.setCellValue(val);
                }

                if (j != douArr.length - 1) {
                    cellNum++;
                    cell = row.createCell(cellNum);
                }
            }
        } else {
            // 其它数据类型都当作字符串简单处理
            String empty = StringUtils.EMPTY;
            if(field != null) {
                ExcelCell anno = field.getAnnotation(ExcelCell.class);
                if (anno != null) {
                    empty = anno.defaultValue();
                }
            }
            textValue = value == null ? empty : value.toString();
        }
        if (textValue != null) {
            HSSFRichTextString richString = new HSSFRichTextString(textValue);
            cell.setCellValue(richString);
        }
        //设置单元格格式
        if(style != null){
            cell.setCellStyle(style);
        }
        return cellNum;
    }

    /**
     * 设置当前表格样式
     */
    HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN); //下边框
        style.setBorderLeft(BorderStyle.THIN);//左边框
        style.setBorderTop(BorderStyle.THIN);//上边框
        style.setBorderRight(BorderStyle.THIN);//右边框
        style.setAlignment(HorizontalAlignment.CENTER);// 居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);;//垂直
        return style;
    }







    /**--------------------------------------------------导入---------------------------------------------------------//


     /**
     * 从流中读取相应的Excel文件到对象列表，标题行为0，没有尾行
     * @param clz 类型
     * @return 对象列表
     */
    public <T> List<T> importExcel(InputStream inputStream,Class<T> clz,String fileName) {
        return importExcel(inputStream, clz,fileName, 0,0);
    }

    /**
     * 从流中读取相应的Excel文件到对象列表
     * @param clz 对象类型
     * @param fileName 文件名，带后缀
     * @param readLine 开始行，注意是标题所在行
     * @param tailLine 底部有多少行，在读入对象时，会减去这些行
     * @return
     */
    public <T> List<T> importExcel(InputStream inputStream,Class<T> clz,String fileName,int readLine,int tailLine) {
        Workbook workbook = null;
        try {
            if(fileName.endsWith(XLS)){
                //2003
                workbook = new HSSFWorkbook(inputStream);
            }else if(fileName.endsWith(XLSX)){
                //2007
                workbook = new XSSFWorkbook(inputStream);
            }else{
                throw new RRException("文件不是Excel文件",100);
            }
            return handlerExcel(workbook, clz, readLine,tailLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从文件路径读取相应的Excel文件到对象列表，标题行为0，没有尾行
     * @param path 路径
     * @param clz 类型
     * @return 对象列表
     */
    public <T> List<T> importExcel(String path,Class<T> clz) {
        return importExcel(path, clz,0,0);
    }

    /**
     * 从文件路径读取相应的Excel文件到对象列表
     * @param path 文件路径下的path
     * @param clz 对象类型
     * @param readLine 开始行，注意是标题所在行
     * @param tailLine 底部有多少行，在读入对象时，会减去这些行
     * @return
     */
    public <T> List<T> importExcel(String path,Class<T> clz,int readLine,int tailLine) {
        Workbook wb = null;
        try {
            wb = new HSSFWorkbook(TemplateFileUtil.getTemplates(path));
            return handlerExcel(wb, clz, readLine,tailLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把Excel的数据封装成voList
     * @param clazz       vo的Class
     * @param inputStream excel输入流
     * @param pattern     如果有时间数据，设定输入格式。默认为"yyy-MM-dd"
     * @param logs        错误log集合
     * @param arrayCount  如果vo中有数组类型,那就按照index顺序,把数组应该有几个值写上.
     * @return voList
     * @throws RuntimeException
     */
    public <T> Collection<T> importExcel(Class<T> clazz, InputStream inputStream, String pattern, ExcelLogs logs, Integer... arrayCount) {
        Workbook workBook;
        try {
            workBook = WorkbookFactory.create(inputStream);
        } catch (Exception e) {
            LG.error("加载excel文件错误：",e);
            return null;
        }
        List<T> list = new ArrayList<>();
        Sheet sheet = workBook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();
        try {
            List<ExcelLog> logList = new ArrayList<>();
            // Map<title,index>
            Map<String, Integer> titleMap = new HashMap<>();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() == 0) {
                    if (clazz == Map.class) {
                        // 解析map用的key,就是excel标题行
                        Iterator<Cell> cellIterator = row.cellIterator();
                        Integer index = 0;
                        while (cellIterator.hasNext()) {
                            String value = cellIterator.next().getStringCellValue();
                            titleMap.put(value, index);
                            index++;
                        }
                    }
                    continue;
                }
                // 整行都空，就跳过
                boolean allRowIsNull = true;
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Object cellValue = getCellValue(cellIterator.next());
                    if (cellValue != null) {
                        allRowIsNull = false;
                        break;
                    }
                }
                if (allRowIsNull) {
                    LG.warn("Excel row " + row.getRowNum() + " all row value is null!");
                    continue;
                }
                StringBuilder log = new StringBuilder();
                if (clazz == Map.class) {
                    Map<String, Object> map = new HashMap<>();
                    for (String k : titleMap.keySet()) {
                        Integer index = titleMap.get(k);
                        Cell cell = row.getCell(index);
                        // 判空
                        if (cell == null) {
                            map.put(k, null);
                        } else {
                            cell.setCellType(CellType.STRING);
                            String value = cell.getStringCellValue();
                            map.put(k, value);
                        }
                    }
                    list.add((T) map);

                } else {
                    T t = clazz.newInstance();
                    int arrayIndex = 0;// 标识当前第几个数组了
                    int cellIndex = 0;// 标识当前读到这一行的第几个cell了
                    List<FieldForSortting> fields = sortFieldByAnno(clazz);
                    for (FieldForSortting ffs : fields) {
                        Field field = ffs.getField();
                        field.setAccessible(true);
                        if (field.getType().isArray()) {
                            Integer count = arrayCount[arrayIndex];
                            Object[] value;
                            if (field.getType().equals(String[].class)) {
                                value = new String[count];
                            } else {
                                // 目前只支持String[]和Double[]
                                value = new Double[count];
                            }
                            for (int i = 0; i < count; i++) {
                                Cell cell = row.getCell(cellIndex);
                                String errMsg = validateCell(cell, field, cellIndex);
                                if (StringUtils.isBlank(errMsg)) {
                                    value[i] = getCellValue(cell);
                                } else {
                                    log.append(errMsg);
                                    log.append(";");
                                    logs.setHasError(true);
                                }
                                cellIndex++;
                            }
                            field.set(t, value);
                            arrayIndex++;
                        } else {
                            Cell cell = row.getCell(cellIndex);
                            String errMsg = validateCell(cell, field, cellIndex);
                            if (StringUtils.isBlank(errMsg)) {
                                Object value = null;
                                // 处理特殊情况,Excel中的String,转换成Bean的Date
                                if (field.getType().equals(Date.class)
                                        && cell.getCellTypeEnum() == CellType.STRING) {
                                    Object strDate = getCellValue(cell);
                                    try {
                                        value = new SimpleDateFormat(pattern).parse(strDate.toString());
                                    } catch (ParseException e) {

                                        errMsg =
                                                MessageFormat.format("the cell [{0}] can not be converted to a date ",
                                                        CellReference.convertNumToColString(cell.getColumnIndex()));
                                    }
                                } else {
                                    value = getCellValue(cell);
                                    // 处理特殊情况,excel的value为String,且bean中为其他,且defaultValue不为空,那就=defaultValue
                                    ExcelCell annoCell = field.getAnnotation(ExcelCell.class);
                                    if (value instanceof String && !field.getType().equals(String.class)
                                            && StringUtils.isNotBlank(annoCell.defaultValue())) {
                                        value = annoCell.defaultValue();
                                    }
                                }
                                field.set(t, value);
                            }
                            if (StringUtils.isNotBlank(errMsg)) {
                                log.append(errMsg);
                                log.append(";");
                                logs.setHasError(true);
                            }
                            cellIndex++;
                        }
                    }
                    list.add(t);
                    logList.add(new ExcelLog(t, log.toString(), row.getRowNum() + 1));
                }
            }
            logs.setLogList(logList);
        } catch (InstantiationException e) {
            throw new RuntimeException(MessageFormat.format("can not instance class:{0}",
                    clazz.getSimpleName()), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(MessageFormat.format("can not instance class:{0}",
                    clazz.getSimpleName()), e);
        }
        return list;
    }

    /**
     * 把excel转成对象
     * @param wb
     * @param clz
     * @param readLine
     * @param tailLine
     * @return
     */
    private <T> List<T> handlerExcel(Workbook wb,Class<T> clz,int readLine,int tailLine) {
        Sheet sheet = wb.getSheetAt(0);
        List<T> objs = null;
        try {
            Row row = sheet.getRow(readLine);
            objs = new ArrayList<T>();
            Map<Integer,String> maps = getHeaderMap(row, clz);
            if(maps==null||maps.size()<=0) throw new RuntimeException("要读取的Excel的格式不正确，检查是否设定了合适的行");
            for(int i=readLine+1;i<=sheet.getLastRowNum()-tailLine;i++) {
                row = sheet.getRow(i);
                T obj = clz.newInstance();
                for(Cell c:row) {
                    BeanUtils.copyProperty(obj,maps.get(c.getColumnIndex()), getCellValue(c));
                }
                objs.add(obj);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            throw new RRException("导入模板或数据有误，请检查后重新导入。");
        }
        return objs;
    }

    private Map<Integer,String> getHeaderMap(Row titleRow,Class clz) {
        List<ExcelHeader> headers = getHeaders(clz);
        Map<Integer,String> maps = new HashMap<Integer, String>();
        for(Cell c:titleRow) {
            String title = c.getStringCellValue();
            for(ExcelHeader eh:headers) {
                if(eh.getTitle().equals(title.trim())) {
                    maps.put(c.getColumnIndex(), eh.getMethodName());
                    break;
                }
            }
        }
        return maps;
    }

    /**
     * 验证Cell类型是否正确
     * @param cell    cell单元格
     * @param field   栏位
     * @param cellNum 第几个栏位,用于errMsg
     * @return
     */
    private String validateCell(Cell cell, Field field, int cellNum) {
        String columnName = CellReference.convertNumToColString(cellNum);
        String result = null;
        CellType[] cellTypeArr = validateMap.get(field.getType());
        if (cellTypeArr == null) {
            result = MessageFormat.format("Unsupported type [{0}]", field.getType().getSimpleName());
            return result;
        }
        ExcelCell annoCell = field.getAnnotation(ExcelCell.class);
        if (cell == null
                || (cell.getCellTypeEnum() == CellType.STRING && StringUtils.isBlank(cell
                .getStringCellValue()))) {
            if (annoCell != null && annoCell.valid().allowNull() == false) {
                result = MessageFormat.format("the cell [{0}] can not null", columnName);
            }
            ;
        } else if (cell.getCellTypeEnum() == CellType.BLANK && annoCell.valid().allowNull()) {
            return result;
        } else {
            List<CellType> cellTypes = Arrays.asList(cellTypeArr);

            // 如果類型不在指定範圍內,並且沒有默認值
            if (!(cellTypes.contains(cell.getCellTypeEnum()))
                    || StringUtils.isNotBlank(annoCell.defaultValue())
                    && cell.getCellTypeEnum() == CellType.STRING) {
                StringBuilder strType = new StringBuilder();
                for (int i = 0; i < cellTypes.size(); i++) {
                    CellType cellType = cellTypes.get(i);
                    strType.append(getCellTypeByInt(cellType));
                    if (i != cellTypes.size() - 1) {
                        strType.append(",");
                    }
                }
                result =
                        MessageFormat.format("the cell [{0}] type must [{1}]", columnName, strType.toString());
            } else {
                // 类型符合验证,但值不在要求范围内的
                // String in
                if (annoCell.valid().in().length != 0 && cell.getCellTypeEnum() == CellType.STRING) {
                    String[] in = annoCell.valid().in();
                    String cellValue = cell.getStringCellValue();
                    boolean isIn = false;
                    for (String str : in) {
                        if (str.equals(cellValue)) {
                            isIn = true;
                        }
                    }
                    if (!isIn) {
                        result = MessageFormat.format("the cell [{0}] value must in {1}", columnName, in);
                    }
                }
                // 数字型
                if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                    double cellValue = cell.getNumericCellValue();
                    // 小于
                    if (!Double.isNaN(annoCell.valid().lt())) {
                        if (!(cellValue < annoCell.valid().lt())) {
                            result =
                                    MessageFormat.format("the cell [{0}] value must less than [{1}]", columnName,
                                            annoCell.valid().lt());
                        }
                    }
                    // 大于
                    if (!Double.isNaN(annoCell.valid().gt())) {
                        if (!(cellValue > annoCell.valid().gt())) {
                            result =
                                    MessageFormat.format("the cell [{0}] value must greater than [{1}]", columnName,
                                            annoCell.valid().gt());
                        }
                    }
                    // 小于等于
                    if (!Double.isNaN(annoCell.valid().le())) {
                        if (!(cellValue <= annoCell.valid().le())) {
                            result =
                                    MessageFormat.format("the cell [{0}] value must less than or equal [{1}]",
                                            columnName, annoCell.valid().le());
                        }
                    }
                    // 大于等于
                    if (!Double.isNaN(annoCell.valid().ge())) {
                        if (!(cellValue >= annoCell.valid().ge())) {
                            result =
                                    MessageFormat.format("the cell [{0}] value must greater than or equal [{1}]",
                                            columnName, annoCell.valid().ge());
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 获取cell类型的文字描述
     *
     * @param cellType <pre>
     *                 CellType.BLANK
     *                 CellType.BOOLEAN
     *                 CellType.ERROR
     *                 CellType.FORMULA
     *                 CellType.NUMERIC
     *                 CellType.STRING
     *                 </pre>
     * @return
     */
    private static String getCellTypeByInt(CellType cellType) {
        if(cellType == CellType.BLANK)
            return "Null type";
        else if(cellType == CellType.BOOLEAN)
            return "Boolean type";
        else if(cellType == CellType.ERROR)
            return "Error type";
        else if(cellType == CellType.FORMULA)
            return "Formula type";
        else if(cellType == CellType.NUMERIC)
            return "Numeric type";
        else if(cellType == CellType.STRING)
            return "String type";
        else
            return "Unknown type";
    }

    /**
     * 获取单元格值
     * @param cell
     * @return
     */
    private Object getCellValue(Cell cell) {
        if (cell == null
                || (cell.getCellTypeEnum() == CellType.STRING && StringUtils.isBlank(cell
                .getStringCellValue()))) {
            return null;
        }
        CellType cellType = cell.getCellTypeEnum();
        if(cellType == CellType.BLANK)
            return null;
        else if(cellType == CellType.BOOLEAN)
            return cell.getBooleanCellValue();
        else if(cellType == CellType.ERROR)
            return cell.getErrorCellValue();
        else if(cellType == CellType.FORMULA) {
            try {
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }
            } catch (IllegalStateException e) {
                return cell.getRichStringCellValue();
            }
        } else if(cellType == CellType.NUMERIC){
            if (DateUtil.isCellDateFormatted(cell)) {
                return cell.getDateCellValue();
            } else {
                return cell.getNumericCellValue();
            }
        } else if(cellType == CellType.STRING){
            if(cell.getStringCellValue().equals("是")){
                return true;
            } else if(cell.getStringCellValue().equals("否")){
                return false;
            } else
                return cell.getStringCellValue();
        } else
            return null;
    }

    /**
     * 根据annotation的seq排序后的栏位
     * @param clazz
     * @return
     */
    private List<FieldForSortting> sortFieldByAnno(Class<?> clazz) {
        Field[] fieldsArr = clazz.getDeclaredFields();
        List<FieldForSortting> fields = new ArrayList<>();
        List<FieldForSortting> annoNullFields = new ArrayList<>();
        for (Field field : fieldsArr) {
            ExcelCell ec = field.getAnnotation(ExcelCell.class);
            if (ec == null) {
                // 没有ExcelCell Annotation 视为不汇入
                continue;
            }
            int id = ec.index();
            fields.add(new FieldForSortting(field, id));
        }
        fields.addAll(annoNullFields);
        sortByProperties(fields, true, false, "index");
        return fields;
    }

    /**
     * list排序
     * @param list
     * @param isNullHigh
     * @param isReversed
     * @param props
     */
    private void sortByProperties(List<? extends Object> list, boolean isNullHigh, boolean isReversed, String... props) {
        if (CollectionUtils.isNotEmpty(list)) {
            Comparator<?> typeComp = ComparableComparator.getInstance();
            if (isNullHigh == true) {
                typeComp = ComparatorUtils.nullHighComparator(typeComp);
            } else {
                typeComp = ComparatorUtils.nullLowComparator(typeComp);
            }
            if (isReversed) {
                typeComp = ComparatorUtils.reversedComparator(typeComp);
            }

            List<Object> sortCols = new ArrayList<Object>();

            if (props != null) {
                for (String prop : props) {
                    sortCols.add(new BeanComparator(prop, typeComp));
                }
            }
            if (sortCols.size() > 0) {
                Comparator<Object> sortChain = new ComparatorChain(sortCols);
                Collections.sort(list, sortChain);
            }
        }
    }


}
