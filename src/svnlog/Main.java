package svnlog;

public class Main {

    public static void main(String[] args) {
        String url = "svn://112.74.182.63/icessm";
        String xls = "C:/Users/Administrator/Desktop/war005/aa/svninfo2.xls";
        SvnLog.log(url, xls);
    }

}
