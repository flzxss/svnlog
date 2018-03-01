package svnlog;

import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
//http://central.maven.org/maven2/org/apache/poi/poi/3.17/poi-3.17.jar
//http://central.maven.org/maven2/cn/hutool/hutool-all/4.0.5/hutool-all-4.0.5.jar
public class SvnLog {

    public static void log(String url, String xlsPath) {
        String cmds = "svn log " + url;
        String execForStr = RuntimeUtil.execForStr(cmds);
        String separation = "------------------------------------------------------------------------";
        String[] strs = execForStr.split(separation);
        List<List<String>> list = new ArrayList<List<String>>();
        for (int i = 0; i < strs.length; i++) {
            String line = strs[i];
            line = line.trim();
            String left = StrUtil.subBefore(line, "\r\n", false);
            String right = StrUtil.subAfter(line, "\r\n", false);
            // System.out.println(left+">");
            if (StrUtil.isBlank(left)) {
                continue;
            }
            List<String> cols = StrUtil.splitTrim(left, "|");
            cols.add(2, format(cols.get(2)));
            cols.remove(3);
            cols.add(StrUtil.trim(right));
            System.out.println(cols + "");
            list.add(cols);
        }
        ExcelWriter writer = ExcelUtil.getWriter(xlsPath);
        writer.write(list).close();
    }

    private static String format(String str) {
        String left = StrUtil.subBefore(str, "+0800", false);
        String right = StrUtil.subAfter(str, "+0800", false);
        right = StrUtil.subBetween(right, "(", ")");
        right = StrUtil.subBefore(right, ",", false);
        return left + right;
    }

}
