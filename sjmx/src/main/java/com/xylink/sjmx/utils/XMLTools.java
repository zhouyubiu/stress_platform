//package com.xylink.sjmx.utils;
//
//import com.xylink.sjmx.common.ResponseData;
//import com.xylink.sjmx.pojo.JmxScripts;
//import lombok.Data;
//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.Element;
//import org.dom4j.Node;
//import org.dom4j.io.SAXReader;
//import org.dom4j.io.XMLWriter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.Writer;
//import java.nio.file.Files;
//import java.util.HashMap;
//
///**
// * @NAME: XMLTools
// * @USER: zhouyu
// * @DATE: 2020/8/1
// */
//
//@Data
//@Component
//@ConfigurationProperties(prefix = "xmltools")
//public class XMLTools {
//
//    private String JMXTemplatePath;
//    private HashMap<String,String>elementXpaths;
//
//
//    //尝试获取目标路径
//    private Document getDocument(String newPath){
//        Document document = null;
//        SAXReader reader = new SAXReader();
//        try {
//           document = reader.read(new File(newPath));
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
//        return document;
//    }
//
//    //写入xml，生成jmx
//    public void write(Document document,String newPath){
//        Writer writer = null;//写入指定的文件路径
//        try {
//            writer = new FileWriter(newPath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        XMLWriter xmlWriter = new XMLWriter(writer);
//        try {
//            xmlWriter.write(document);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            xmlWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//    public void setJmx(Document document,HashMap<String,String> jxmap){
//
//        jxmap.forEach((k,v)->{
//            System.out.println(v);
//            Element element = (((Element)document
//                    .selectSingleNode(this.elementXpaths.get(k)))
//                    .addAttribute(v,k));
//
//        });
//        System.out.println("888888888888888");
//
//
//    }
//    public void setAttribute(Document document,HashMap<String,String> jxmap){
//
////        jxmap.forEach((k,v)->{
////            System.out.println(v);
////            Element element = (((Element)document
////                    .selectSingleNode(this.elementXpaths.get(k)))
////                    .addAttribute(v,k));
////
////        });
////        System.out.println("888888888888888");
//
//
//    }
//    public void setValue(Document document,HashMap<String,String> jxmap){
//
////        jxmap.forEach((k,v)->{
////            System.out.println(v);
////            Element element = (((Element)document
////                    .selectSingleNode(this.elementXpaths.get(k)))
////                    .addAttribute(v,k));
////
////        });
////        System.out.println("888888888888888");
//
//
//    }
//
//
//    public void setTestPlan(Document document,HashMap<String,String>threadGroup){
//
//
//
//    }
//
//    public ResponseData<String> creatJMX(JmxScripts jmxScripts){
//
//        ResponseData<String> copyJmx = this.copyJmx(jmxScripts.getJmxAbsolutePath());
//        if(!copyJmx.isSuccessful()){
//            return copyJmx;
//        }
//        //获取doc
//        HashMap<String,String>jxmap=jmxScripts.getXmlAndMemberMap();
//        Document document=this.getDocument(copyJmx.getData());
//        this.setJmx(document,jxmap);
//        //        this.setJmx(document);
//        //TO DO 设置脚本
//        //TO DO 写入脚本参数
//        //尝试运行
//        return null;
//
//    }
//
//
//    //拷贝模版到目标目录
//    public ResponseData<String> copyJmx(String newPath){
//
//        File oldpaths = new File(this.JMXTemplatePath);
//        File newpaths = new File(newPath);
//        boolean exists = newpaths.exists();
//        if(!newpaths.exists()){
//            File fileParentPath = newpaths.getParentFile();
//            boolean mkdirs = fileParentPath.mkdirs();
//            try {
//                Files.copy(oldpaths.toPath(), newpaths.toPath());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return ResponseData.creatBySuccessMsg(newpaths.getAbsolutePath(),"jmeter脚本创建成功");
//            }
//        return ResponseData.creatBySuccessMsg(newpaths.getAbsolutePath(),"jmeter脚本已经存在");
//    }
//
//
//}
