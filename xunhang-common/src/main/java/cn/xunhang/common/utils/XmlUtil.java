package cn.xunhang.common.utils;

import cn.xunhang.common.exception.RRException;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class XmlUtil {

    private Logger LG = LoggerFactory.getLogger(XmlUtil.class);

    /**
     * 解析XML文件
     * @param file
     * @return
     */
    public static Map<String, Object> parseXML(File file) {
        Document doc = null;
        try {
            if(!file.getName().endsWith(".xml")){
                throw new RRException("xml文件导入格式不正确",100);
            }
            //解析xml文件
            //创建SAXReader的对象reader
            SAXReader reader = new SAXReader();
            //通过reader对象的read方法加载xml文件
            doc = reader.read(file);

            //将xml字符串转为document对象
//            doc = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
//            LG.error(XmlUtil.class, e, "响应参数解析失败:{}", xml);
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>();
        if (doc == null) {
            return map;
        } else {
            //获取根节点
            Element rootElement = doc.getRootElement();
            element2map(rootElement, map);
        }
        return map;
    }

    /**
     * map转xml字符串
     * @param map
     * @return
     */
    public static String map2xml(Map<String, Object> map, String rootElement) {
        StringBuffer xmlStr = new StringBuffer("");
        if (map.isEmpty())
            return xmlStr.toString();
        xmlStr.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><" + rootElement + ">");
        map2Element(map, xmlStr);
        xmlStr.append("</" + rootElement + ">");
        return xmlStr.toString();
    }

    /**
     * 递归原则 : 一个map对应一个xml节点,key为当前xml节点名,value为当前xml节点子集
     * 如果xml节点没有子节点(叶子节点),那么map的key为xml节点名,value为xml节点文本内容
     * 如果xml节点有一个子节点,那么map的key为xml节点名,value为xml节点子集
     * 如果xml节点有多个子节点,对应map的key不存在(每一次),map的key为xml节点名,value为xml节点子集
     * 如果xml节点有多个子节点,对应map的key已存在,且value为map类型(第二次),map的key为xml节点名,值从map类型转为list,而list中添加2份当前xml节点子集
     * 如果xml节点有多个子节点,对应map的key已存在,且value为list类型(第三/多次),那么直接加到list中去.
     * @param elmt
     * @param map
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    private static void element2map(Element elmt, Map<String, Object> map) {
        if (null == elmt) return;

        String name = elmt.getName();

        //判断当前节点的内容是否为文本（最里面一层节点）
        if (elmt.isTextOnly()) {
            map.put(name, elmt.getText());
        } else {
            Map<String, Object> mapSub = new HashMap<>();

            //获取当前节点的所有子节点
            List<Element> elements = (List<Element>)elmt.elements();

            //利用递归获取节点值
            for (Element elmtSub : elements)
            {
                element2map(elmtSub, mapSub);
            }
            Object first = map.get(name);

            //判断
            if (null == first) {
                map.put(name, mapSub);
            } else {
                if (first instanceof List<?>) {
                    ((List<Map<String, Object>>)first).add(mapSub);
                } else {
                    List<Object> listSub = new ArrayList<Object>();
                    listSub.add(first);
                    listSub.add(mapSub);
                    map.put(name, listSub);
                }
            }
        }
    }

    /**
     * 递归map转xml字符串
     * @param map
     * @param sb
     */
    @SuppressWarnings("unchecked")
    private static void map2Element(Map<String, Object> map, StringBuffer sb) {
        Set<String> set = map.keySet();
        for (Iterator<String> it = set.iterator(); it.hasNext();) {
            String key = (String)it.next();
            Object value = map.get(key);
            if (null == value)
                value = "";
            if (value instanceof List<?>) {
                List<Object> list = (List<Object>)map.get(key);
                sb.append("<" + key + ">");
                for (int i = 0; i < list.size(); i++) {
                    Map<String, Object> hm = (Map<String, Object>)list.get(i);
                    map2Element(hm, sb);
                }
                sb.append("</" + key + ">");
            } else {
                if (value instanceof Map<?, ?>) {
                    sb.append("<" + key + ">");
                    map2Element((Map<String, Object>)value, sb);
                    sb.append("</" + key + ">");
                } else {
                    sb.append("<" + key + ">" + value + "</" + key + ">");
                }
            }
        }
    }







//    private static List<Book> bookLists = new ArrayList<Book>();

    /**
     * 解析XML文件
     * @throws DocumentException
     */
//    public static void parseXML(String xml) throws DocumentException {
//        //将xml字符串转为document对象
//        Document document = DocumentHelper.parseText(xml);
//        //通过document对象获取根节点bookstore
//        Element bookStore = document.getRootElement();
//        //通过element对象的elementIterator方法获取迭代器
//        Iterator it = bookStore.elementIterator();
//        //遍历迭代器，获取根节点中的信息
//        int cnt = 1;
//        while(it.hasNext()) {
//            Book bookEntity = new Book();
//            Element book = (Element) it.next();
//            //获取book的属性名和属性值
//            List<Attribute> bookAttrs = book.attributes();
//            for (Attribute attribute : bookAttrs) {
//                System.out.println("节点名：" + attribute.getName() + "\t节点值：" + attribute.getStringValue());
//                if(attribute.getName().equals("id")) {
//                    bookEntity.setId(attribute.getStringValue());
//                }
//            }
//            Iterator itt = book.elementIterator();
//            while(itt.hasNext()) {
//                Element bookChild = (Element) itt.next();
//                System.out.println("节点名：" + bookChild.getName() + "\t节点值：" + bookChild.getStringValue());
//                if(bookChild.getName().equals("name")) {
//                    bookEntity.setName(bookChild.getStringValue());
//                } else if(bookChild.getName().equals("anthor")) {
//                    bookEntity.setAnthor(bookChild.getStringValue());
//                } else if(bookChild.getName().equals("Year")) {
//                    bookEntity.setYear(bookChild.getStringValue());
//                } else if(bookChild.getName().equals("language")) {
//                    bookEntity.setLanguage(bookChild.getStringValue());
//                } else if(bookChild.getName().equals("version")) {
//                    bookEntity.setVersion(bookChild.getStringValue());
//                } else if(bookChild.getName().equals("price")) {
//                    bookEntity.setPrice(bookChild.getStringValue());
//                }
//            }
//            System.out.println("======结束遍历第" + cnt + "本书======");
//            ++cnt;
//            bookLists.add(bookEntity);
//            bookEntity = null;
//            System.out.println(bookLists.size());
//        }
//    }

    /**
     * 生成xml
     * @throws IOException
     */
    public void createXML() throws IOException {
        //1.创建document对象，代表整个xml文档
        Document document = DocumentHelper.createDocument();

        //2.创建根节点rss
        Element rss = document.addElement("rss");

        //3.向rss节点中添加version属性
        rss.addAttribute("version", "2.0");

        //4.生成子节点及节点内容
        Element channel = rss.addElement("channel");
        Element title = channel.addElement("title");
        title.setText("国内最新新闻");

        Element image = channel.addElement("image");
        Element imageTitle = image.addElement("title");
        imageTitle.setText("新闻国内");
        Element imageLink = image.addElement("link");
        imageLink.setText("http://news.qq.com");
        Element imageUrl = image.addElement("url");
        imageUrl.setText("http://mat1.qq.com/news/rss/logo_news.gif");

        //设置生成xml的格式(换行、缩进)
        OutputFormat format = OutputFormat.createPrettyPrint();
        //设置编码,默认
        format.setEncoding("GBK");

        //5.生成xml文件
        File file = new File("rssNews1.xml");
        XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);

        //设置是否转义，默认true
        writer.setEscapeText(false);

        writer.write(document);
        writer.close();
    }

    public static void main(String[] args) throws IOException{
        XmlUtil d4j = new XmlUtil();
        d4j.createXML();

    }





}
