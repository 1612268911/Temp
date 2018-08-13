/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ExcelUtil
 * Author:   jj
 * Date:     2018/8/8 16:35
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cszt.util;

import org.apache.poi.ss.usermodel.Cell;

import java.text.DecimalFormat;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author jj
 * @create 2018/8/8
 * @since 1.0.0
 */
public class ExcelUtil {
    /**
     * 判断cell类型
     *
     * @Title: extacted4cellType
     * @Description: TODO
     * @param cell
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String extacted4cellType(Cell cell) {
        if (cell == null) {
            return "";
        }
        String str;
        DecimalFormat df = new DecimalFormat("0");
        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            str = String.valueOf(cell.getBooleanCellValue());
            return str;
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            str = df.format(cell.getNumericCellValue());
            return str;
        } else {
            str = cell.getStringCellValue();
            return str;
        }

    }
}