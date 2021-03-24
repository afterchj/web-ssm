package com.tpadsz.ssm.controller;


import com.tpadsz.ssm.model.FAQ;
import com.tpadsz.ssm.service.FAQService;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongjian.chen on 2018/4/26.
 */

@Controller
@RequestMapping("/pandaHelp")
public class PandaHelpController {

    @Autowired
    FAQService faqService;

    @RequestMapping("/FAQ")
    public String searchByKey(String keyword, ModelMap modelMap) throws WriteException, IOException, BiffException {
        System.out.println("search keying...");
        if (keyword == null || keyword.equals("")) {
//            modelMap.addAttribute("flag", "0");
            return "panda_help";
        }
        List<FAQ> list = new ArrayList<>();
        try {
            list = faqService.selectByKey(filterStr(keyword));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("keyword=" + keyword + ",size=" + list.size());
        modelMap.put("list", list);
        if (list.size() == 0) {
            modelMap.remove("list");
            modelMap.addAttribute("flag", "0");
        }
        modelMap.addAttribute("keyword", keyword);
        return "panda_help";
    }


    public String filterStr(String str) {
//        String keyword;
        String[] keyArray;
        List<String> list = faqService.getAllKey();
        for (String strS : list) {
            keyArray = strS.split(" ");
            for (int i = 0; i < keyArray.length; i++) {
                if (str.contains(keyArray[i])) {
//                    keyword = keyArray[i];
                    return keyArray[i];
                }
            }
        }
        return str;
    }

//    public static List<FAQ> exporpExcel() throws IOException, BiffException, WriteException {
//        List<FAQ> list = new ArrayList();
//        String filePath = "D:\\b.xls";
//        InputStream is = null;
//        Workbook rwb = null;
//
//        try {
//            is = new FileInputStream(filePath);//定义文本输入流
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            rwb = Workbook.getWorkbook(is);//打开Workbook
//        } catch (BiffException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //获取Excel表的Sheet1区域的数据
//        Sheet sht = rwb.getSheet(0);
//        int col = sht.getColumns(); //获得Excel列
//        int row = sht.getRows(); //获得Excel行
////        System.out.println("row=" + row);
//
//        //先将数据按行装入一个一维数组中， 然后将数组逐个加入到ArrayList
//        for (int i = 1; i < row - 1; i++) {
//            FAQ faq = new FAQ();
//            for (int j = 0; j < col; j++) {
//                faq.setId(Integer.parseInt(sht.getCell(0, i).getContents()));
//                faq.setQuestion(sht.getCell(1, i).getContents());
//                faq.setUrl(sht.getCell(2, i).getContents());
//                faq.setAnswer(sht.getCell(3, i).getContents());
//                faq.setKeyword(sht.getCell(4, i).getContents());
//            }
//            list.add(faq);
//        }
//        return list;
//    }
}
