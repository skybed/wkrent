package com.wkrent.web.config;

import com.google.common.collect.Lists;
import com.wkrent.common.util.UUIDUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class AreaInitUtil {

    private static void printNode(Node n, Node parent, int level, List<String> sqlList) {
        parent = parent==null? n:parent;
        String code = n.getAttributes().getNamedItem("Code").getNodeValue() + "-" + n.getAttributes().getNamedItem("Name").getNodeValue();
        String parentCode = parent.getAttributes().getNamedItem("Code").getNodeValue() + "-" + parent.getAttributes().getNamedItem("Name").getNodeValue();
        String sql = "insert into bg_area(area_id,area_code,area_name, area_parent_code, area_level)values( "
                + "\"" + UUIDUtil.getUUID() + "\","
                + "\"" + code + "\","
                + "\"" + n.getAttributes().getNamedItem("Name").getNodeValue() + "\","
                + "\"" + parentCode + "\","
                + level
                + ");";
        sqlList.add(sql);
    }

    public static void main(String[] args) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            List<String> sqlList = Lists.newArrayList();
            DocumentBuilder db = dbf.newDocumentBuilder();
            String path = new File("").getCanonicalPath() + "\\wkrent-web\\src\\main\\resources\\LocList.xml";

            Document document = db.parse(path);
            NodeList l = document.getElementsByTagName("CountryRegion");
            System.out.println("一共有" + l.getLength() + "个国家");
            for (int i = 0; i < l.getLength(); i++) {//州
                Node n = l.item(i);
                printNode(n,null,1, sqlList);
                NodeList childNodes = n.getChildNodes();
                for (int k = 0; k < childNodes.getLength(); k++) {
                    Node n2 = childNodes.item(k);
                    if("State".equals(n2.getNodeName())){
                        if(n2.getAttributes().getNamedItem("Name")==null){
                            NodeList childNodesS = n2.getChildNodes();
                            for(int z = 0; z < childNodesS.getLength(); z++) {
                                Node ns = childNodesS.item(z);
                                if("City".equals(ns.getNodeName())){
//                    				System.out.println("这个国家没有州");
                                    printNode(ns,n,3, sqlList);
                                }
                            }
                            continue;
                        }
                        printNode(n2,n,2, sqlList);
                        NodeList childNodes2 = n2.getChildNodes();
                        for(int u = 0; u < childNodes2.getLength(); u++) {
                            Node n3 = childNodes2.item(u);
                            if("City".equals(n3.getNodeName())){
                                printNode(n3,n2,3, sqlList);
                            }
                        }
                    }
                }
            }
            if(CollectionUtils.isNotEmpty(sqlList)){
                String sql = new File("").getCanonicalPath() + "\\wkrent-web\\src\\main\\resources\\area.sql";
                File file = new File(sql);
                if(!file.exists()){
                    file.createNewFile();
                }
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
               for(String sqlStr : sqlList){
                   bufferedWriter.write(sqlStr);
                   bufferedWriter.newLine();
               }
                bufferedWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
