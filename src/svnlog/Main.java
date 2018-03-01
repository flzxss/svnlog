package svnlog;

public class Main {

    public static void main(String[] args) {
        String url = "svn://127.0.0.1/web";
        String xls = "C:/Users/Administrator/Desktop/svninfo.xls";
        SvnLog.log(url, xls);
    }

}
