package com.utils;

import com.exceptions.NotExcelFileException;
import com.pojo.Student;
import org.apache.log4j.Logger;


import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * created by wushijia
 * 解析Excel的工具类
 */
public class ExcelUtils {
    private static final Logger log = Logger.getLogger(ExcelUtils.class);
    public static List<Student> getListByExcelFile(MultipartFile file){
        List<Student> res = new ArrayList<>();
        try {
            // 检查文件是否为空
            checkFile(file);
            Workbook workbook = getWorkBook(file);// 得到工作簿
            Student stu = null;
            if(workbook != null){
                for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){//这种情况是考虑到有多个sheet的情况
                    Sheet sheet = workbook.getSheetAt(sheetNum);
                    if(sheet == null){
                        continue;
                    }
                    // 获取第一行
                    int firstRowNum = sheet.getFirstRowNum();
                    //获取最后一行
                    int lastRowNum = sheet.getLastRowNum();
                    //由于第一行是表头，所以不需要解析
                    for(int rowNum = firstRowNum + 1; rowNum <= lastRowNum;rowNum++){
                        //获取当前行
                        Row row = sheet.getRow(rowNum);
                        if(row == null){
                            continue;
                        }
                        stu = new Student();
                        // 获取第一列
                        int firstCellNum = row.getFirstCellNum();
                        // 获取最后一列，但是注意有可能' '字符也考虑入内，所以需要加个判断
                        int lastCellNum = row.getLastCellNum();
                        String cellValue = "";
                        for (int cellNum = firstCellNum;cellNum <= lastCellNum;cellNum++){//开始循环取一行的内容
                            Cell cell = row.getCell(cellNum);
                            cellValue = getCellValue(cell);
                            switch (cellNum){//根据不同的列数来判断数据类型
                                case 0:// 学号
                                    stu.setStudentId(cellValue);
                                    break;
                                case 1:// 姓名
                                    stu.setName(cellValue);
                                    break;
                                case 2:// 班级
                                    stu.setClassId(cellValue);
                                    break;
                                case 3:// 电话
                                    stu.setPhoneNumber(cellValue);
                                    break;
                                case 4:// 年龄
                                    stu.setAge(Integer.parseInt(cellValue));
                                    break;
                                case 5:// 身份证号
                                    stu.setIdCard(cellValue);
                                    break;
                                 default:
                                     break;
                            }

                        }
                        res.add(stu);

                    }
                }
            }

        }catch (Exception e){
            log.error(e.getMessage());
        }
        return res;
    }
    private static void checkFile(MultipartFile file) throws Exception{
        if(null == file){
            throw new NullPointerException("文件不存在");
        }
        String originalFilename = file.getOriginalFilename();
        if(!originalFilename.endsWith("xls")&&!originalFilename.endsWith("xlsx")){
            throw new NotExcelFileException("该文件不是Excel文件");
        }
    }
    private static Workbook getWorkBook(MultipartFile file) throws Exception{
        String name = file.getOriginalFilename();
        Workbook workbook = null;
        // 获取文件输入流
        InputStream inputStream = file.getInputStream();
        if(null != name && name.endsWith("xls")){
            // 2003版本
            workbook = new HSSFWorkbook(inputStream);
        }else if(null != name && name.endsWith("xlsx")){
            // 2007版本
            workbook = new XSSFWorkbook(inputStream);
        }
        return workbook;
    }
    private static String getCellValue(Cell cell){// 得到单元格内的数据，将其全部转化为字符串
        String value = "";
        if(cell == null ){
            return value;
        }
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_NUMERIC: //数字
                value = stringDateProcess(cell);
                break;
            case Cell.CELL_TYPE_STRING: //字符串
                value = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
                value = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: //空值
                value = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障
                value = "非法字符";
                break;
            default:
                value = "未知类型";
                break;
        }
        return value;
    }
    /**
     * 时间格式处理
     * @return
     * @author Liu Xin Nan
     * @data 2017年11月27日
     */
    public static String stringDateProcess(Cell cell){
        String result = "";
        if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
            SimpleDateFormat sdf = null;
            if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                sdf = new SimpleDateFormat("HH:mm");
            } else {// 日期
                sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            }
            Date date = cell.getDateCellValue();
            result = sdf.format(date);
        } else if (cell.getCellStyle().getDataFormat() == 58) {
            // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            double value = cell.getNumericCellValue();
            Date date = org.apache.poi.ss.usermodel.DateUtil
                    .getJavaDate(value);
            result = sdf.format(date);
        } else {
            double value = cell.getNumericCellValue();
            CellStyle style = cell.getCellStyle();
            DecimalFormat format = new DecimalFormat();
            String temp = style.getDataFormatString();
            // 单元格设置成常规
            if (temp.equals("General")) {
                format.applyPattern("#");
            }
            result = format.format(value);
        }

        return result;
    }
}
