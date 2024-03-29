/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import java.io.File;
import java.util.HashMap;

/**
 *
 * @author root
 */
public class MimeTypeUtil {

    File f;
    private HashMap<String, String> mimeMap = new HashMap<String, String>();

    public MimeTypeUtil(File f) {
        this.f = f;
        setMap();
    }

    public String getMimeType() {
        String ext = getExtension().toLowerCase();
        String mime = mimeMap.get(ext);
        return mime;
    }

    public static void main(String[] args) {
        MimeTypeUtil m = new MimeTypeUtil(new File("/root/Downloads/avion.swf"));
        System.out.println("M:" + m.getMimeType());
    }

    private String getExtension() {
        String fileName = f.getName();
        int mid = fileName.lastIndexOf(".");
        String ext = fileName.substring(mid + 1, fileName.length());
        return ext;
    }

    private void setMap() {
        mimeMap.put("3dm", "x-world/x-3dmf");
        mimeMap.put("3dmf", "x-world/x-3dmf");
        mimeMap.put("a", "application/octet-stream");
        mimeMap.put("aab", "application/x-authorware-bin");
        mimeMap.put("aam", "application/x-authorware-map");
        mimeMap.put("aas", "application/x-authorware-seg");
        mimeMap.put("abc", "text/vndabc");
        mimeMap.put("acgi", "text/html");
        mimeMap.put("afl", "video/animaflex");
        mimeMap.put("ai", "application/postscript");
        mimeMap.put("aif", "audio/aiff");
        mimeMap.put("aif", "audio/x-aiff");
        mimeMap.put("aifc", "audio/aiff");
        mimeMap.put("aifc", "audio/x-aiff");
        mimeMap.put("aiff", "audio/aiff");
        mimeMap.put("aiff", "audio/x-aiff");
        mimeMap.put("aim", "application/x-aim");
        mimeMap.put("aip", "text/x-audiosoft-intra");
        mimeMap.put("ani", "application/x-navi-animation");
        mimeMap.put("aos", "application/x-nokia-9000-communicator-add-on-software");
        mimeMap.put("aps", "application/mime");
        mimeMap.put("arc", "application/octet-stream");
        mimeMap.put("arj", "application/arj");
        mimeMap.put("arj", "application/octet-stream");
        mimeMap.put("art", "image/x-jg");
        mimeMap.put("asf", "video/x-ms-asf");
        mimeMap.put("asm", "text/x-asm");
        mimeMap.put("asp", "text/asp");
        mimeMap.put("asx", "application/x-mplayer2");
        mimeMap.put("asx", "video/x-ms-asf");
        mimeMap.put("asx", "video/x-ms-asf-plugin");
        mimeMap.put("au", "audio/basic");
        mimeMap.put("au", "audio/x-au");
        mimeMap.put("avi", "application/x-troff-msvideo");
        mimeMap.put("avi", "video/avi");
        mimeMap.put("avi", "video/msvideo");
        mimeMap.put("avi", "video/x-msvideo");
        mimeMap.put("avs", "video/avs-video");
        mimeMap.put("bcpio", "application/x-bcpio");
        mimeMap.put("bin", "application/mac-binary");
        mimeMap.put("bin", "application/macbinary");
        mimeMap.put("bin", "application/octet-stream");
        mimeMap.put("bin", "application/x-binary");
        mimeMap.put("bin", "application/x-macbinary");
        mimeMap.put("bm", "image/bmp");
        mimeMap.put("bmp", "image/bmp");
        mimeMap.put("bmp", "image/x-windows-bmp");
        mimeMap.put("boo", "application/book");
        mimeMap.put("book", "application/book");
        mimeMap.put("boz", "application/x-bzip2");
        mimeMap.put("bsh", "application/x-bsh");
        mimeMap.put("bz", "application/x-bzip");
        mimeMap.put("bz2", "application/x-bzip2");
        mimeMap.put("c", "text/plain");
        mimeMap.put("c", "text/x-c");
        mimeMap.put("c++", "text/plain");
        mimeMap.put("cat", "application/vndms-pkiseccat");
        mimeMap.put("cc", "text/plain");
        mimeMap.put("cc", "text/x-c");
        mimeMap.put("ccad", "application/clariscad");
        mimeMap.put("cco", "application/x-cocoa");
        mimeMap.put("cdf", "application/cdf");
        mimeMap.put("cdf", "application/x-cdf");
        mimeMap.put("cdf", "application/x-netcdf");
        mimeMap.put("cer", "application/pkix-cert");
        mimeMap.put("cer", "application/x-x509-ca-cert");
        mimeMap.put("cha", "application/x-chat");
        mimeMap.put("chat", "application/x-chat");
        mimeMap.put("class", "application/java");
        mimeMap.put("class", "application/java-byte-code");
        mimeMap.put("class", "application/x-java-class");
        mimeMap.put("com", "application/octet-stream");
        mimeMap.put("com", "text/plain");
        mimeMap.put("conf", "text/plain");
        mimeMap.put("cpio", "application/x-cpio");
        mimeMap.put("cpp", "text/x-c");
        mimeMap.put("cpt", "application/mac-compactpro");
        mimeMap.put("cpt", "application/x-compactpro");
        mimeMap.put("cpt", "application/x-cpt");
        mimeMap.put("crl", "application/pkcs-crl");
        mimeMap.put("crl", "application/pkix-crl");
        mimeMap.put("crt", "application/pkix-cert");
        mimeMap.put("crt", "application/x-x509-ca-cert");
        mimeMap.put("crt", "application/x-x509-user-cert");
        mimeMap.put("csh", "application/x-csh");
        mimeMap.put("csh", "text/x-scriptcsh");
        mimeMap.put("css", "application/x-pointplus");
        mimeMap.put("css", "text/css");
        mimeMap.put("cxx", "text/plain");
        mimeMap.put("dcr", "application/x-director");
        mimeMap.put("deepv", "application/x-deepv");
        mimeMap.put("def", "text/plain");
        mimeMap.put("der", "application/x-x509-ca-cert");
        mimeMap.put("dif", "video/x-dv");
        mimeMap.put("dir", "application/x-director");
        mimeMap.put("dl", "video/dl");
        mimeMap.put("dl", "video/x-dl");
        mimeMap.put("doc", "application/msword");
        mimeMap.put("docx", "application/msword");
        mimeMap.put("dot", "application/msword");
        mimeMap.put("dp", "application/commonground");
        mimeMap.put("drw", "application/drafting");
        mimeMap.put("dump", "application/octet-stream");
        mimeMap.put("dv", "video/x-dv");
        mimeMap.put("dvi", "application/x-dvi");
        mimeMap.put("dwf", "drawing/x-dwf (old)");
        mimeMap.put("dwf", "model/vnddwf");
        mimeMap.put("dwg", "application/acad");
        mimeMap.put("dwg", "image/vnddwg");
        mimeMap.put("dwg", "image/x-dwg");
        mimeMap.put("dxf", "application/dxf");
        mimeMap.put("dxf", "image/vnddwg");
        mimeMap.put("dxf", "image/x-dwg");
        mimeMap.put("dxr", "application/x-director");
        mimeMap.put("el", "text/x-scriptelisp");
        mimeMap.put("elc", "application/x-bytecodeelisp (compiled elisp)");
        mimeMap.put("elc", "application/x-elc");
        mimeMap.put("env", "application/x-envoy");
        mimeMap.put("eps", "application/postscript");
        mimeMap.put("es", "application/x-esrehber");
        mimeMap.put("etx", "text/x-setext");
        mimeMap.put("evy", "application/envoy");
        mimeMap.put("evy", "application/x-envoy");
        mimeMap.put("exe", "application/octet-stream");
        mimeMap.put("f", "text/plain");
        mimeMap.put("f", "text/x-fortran");
        mimeMap.put("f77", "text/x-fortran");
        mimeMap.put("f90", "text/plain");
        mimeMap.put("f90", "text/x-fortran");
        mimeMap.put("fdf", "application/vndfdf");
        mimeMap.put("fif", "application/fractals");
        mimeMap.put("fif", "image/fif");
        mimeMap.put("fli", "video/fli");
        mimeMap.put("fli", "video/x-fli");
        mimeMap.put("flo", "image/florian");
        mimeMap.put("flx", "text/vndfmiflexstor");
        mimeMap.put("fmf", "video/x-atomic3d-feature");
        mimeMap.put("for", "text/plain");
        mimeMap.put("for", "text/x-fortran");
        mimeMap.put("fpx", "image/vndfpx");
        mimeMap.put("fpx", "image/vndnet-fpx");
        mimeMap.put("frl", "application/freeloader");
        mimeMap.put("funk", "audio/make");
        mimeMap.put("g", "text/plain");
        mimeMap.put("g3", "image/g3fax");
        mimeMap.put("gif", "image/gif");
        mimeMap.put("gl", "video/gl");
        mimeMap.put("gl", "video/x-gl");
        mimeMap.put("gsd", "audio/x-gsm");
        mimeMap.put("gsm", "audio/x-gsm");
        mimeMap.put("gsp", "application/x-gsp");
        mimeMap.put("gss", "application/x-gss");
        mimeMap.put("gtar", "application/x-gtar");
        mimeMap.put("gz", "application/x-compressed");
        mimeMap.put("gz", "application/x-gzip");
        mimeMap.put("gzip", "application/x-gzip");
        mimeMap.put("gzip", "multipart/x-gzip");
        mimeMap.put("h", "text/plain");
        mimeMap.put("h", "text/x-h");
        mimeMap.put("hdf", "application/x-hdf");
        mimeMap.put("help", "application/x-helpfile");
        mimeMap.put("hgl", "application/vndhp-hpgl");
        mimeMap.put("hh", "text/plain");
        mimeMap.put("hh", "text/x-h");
        mimeMap.put("hlb", "text/x-script");
        mimeMap.put("hlp", "application/hlp");
        mimeMap.put("hlp", "application/x-helpfile");
        mimeMap.put("hlp", "application/x-winhelp");
        mimeMap.put("hpg", "application/vndhp-hpgl");
        mimeMap.put("hpgl", "application/vndhp-hpgl");
        mimeMap.put("hqx", "application/binhex");
        mimeMap.put("hqx", "application/binhex4");
        mimeMap.put("hqx", "application/mac-binhex");
        mimeMap.put("hqx", "application/mac-binhex40");
        mimeMap.put("hqx", "application/x-binhex40");
        mimeMap.put("hqx", "application/x-mac-binhex40");
        mimeMap.put("hta", "application/hta");
        mimeMap.put("htc", "text/x-component");
        mimeMap.put("htm", "text/html");
        mimeMap.put("html", "text/html");
        mimeMap.put("htmls", "text/html");
        mimeMap.put("htt", "text/webviewhtml");
        mimeMap.put("htx", "text/html");
        mimeMap.put("ice", "x-conference/x-cooltalk");
        mimeMap.put("ico", "image/x-icon");
        mimeMap.put("idc", "text/plain");
        mimeMap.put("ief", "image/ief");
        mimeMap.put("iefs", "image/ief");
        mimeMap.put("iges", "application/iges");
        mimeMap.put("iges", "model/iges");
        mimeMap.put("igs", "application/iges");
        mimeMap.put("igs", "model/iges");
        mimeMap.put("ima", "application/x-ima");
        mimeMap.put("imap", "application/x-httpd-imap");
        mimeMap.put("inf", "application/inf");
        mimeMap.put("ins", "application/x-internett-signup");
        mimeMap.put("ip", "application/x-ip2");
        mimeMap.put("isu", "video/x-isvideo");
        mimeMap.put("it", "audio/it");
        mimeMap.put("iv", "application/x-inventor");
        mimeMap.put("ivr", "i-world/i-vrml");
        mimeMap.put("ivy", "application/x-livescreen");
        mimeMap.put("jam", "audio/x-jam");
        mimeMap.put("jav", "text/plain");
        mimeMap.put("jav", "text/x-java-source");
        mimeMap.put("java", "text/plain");
        mimeMap.put("java", "text/x-java-source");
        mimeMap.put("jcm", "application/x-java-commerce");
        mimeMap.put("jfif", "image/jpeg");
        mimeMap.put("jfif", "image/pjpeg");
        mimeMap.put("jfif-tbnl", "image/jpeg");
        mimeMap.put("jpe", "image/jpeg");
        mimeMap.put("jpe", "image/pjpeg");
        mimeMap.put("jpeg", "image/jpeg");
        mimeMap.put("jpeg", "image/pjpeg");
        mimeMap.put("jpg", "image/jpeg");
        mimeMap.put("jpg", "image/pjpeg");
        mimeMap.put("jps", "image/x-jps");
        mimeMap.put("js", "application/x-javascript");
        mimeMap.put("jut", "image/jutvision");
        mimeMap.put("kar", "audio/midi");
        mimeMap.put("kar", "music/x-karaoke");
        mimeMap.put("ksh", "application/x-ksh");
        mimeMap.put("ksh", "text/x-scriptksh");
        mimeMap.put("la", "audio/nspaudio");
        mimeMap.put("la", "audio/x-nspaudio");
        mimeMap.put("lam", "audio/x-liveaudio");
        mimeMap.put("latex", "application/x-latex");
        mimeMap.put("lha", "application/lha");
        mimeMap.put("lha", "application/octet-stream");
        mimeMap.put("lha", "application/x-lha");
        mimeMap.put("lhx", "application/octet-stream");
        mimeMap.put("list", "text/plain");
        mimeMap.put("lma", "audio/nspaudio");
        mimeMap.put("lma", "audio/x-nspaudio");
        mimeMap.put("log", "text/plain");
        mimeMap.put("lsp", "application/x-lisp");
        mimeMap.put("lsp", "text/x-scriptlisp");
        mimeMap.put("lst", "text/plain");
        mimeMap.put("lsx", "text/x-la-asf");
        mimeMap.put("ltx", "application/x-latex");
        mimeMap.put("lzh", "application/octet-stream");
        mimeMap.put("lzh", "application/x-lzh");
        mimeMap.put("lzx", "application/lzx");
        mimeMap.put("lzx", "application/octet-stream");
        mimeMap.put("lzx", "application/x-lzx");
        mimeMap.put("m", "text/plain");
        mimeMap.put("m", "text/x-m");
        mimeMap.put("m1v", "video/mpeg");
        mimeMap.put("m2a", "audio/mpeg");
        mimeMap.put("m2v", "video/mpeg");
        mimeMap.put("m3u", "audio/x-mpequrl");
        mimeMap.put("man", "application/x-troff-man");
        mimeMap.put("map", "application/x-navimap");
        mimeMap.put("mar", "text/plain");
        mimeMap.put("mbd", "application/mbedlet");
        mimeMap.put("mc$", "application/x-magic-cap-package-10");
        mimeMap.put("mcd", "application/mcad");
        mimeMap.put("mcd", "application/x-mathcad");
        mimeMap.put("mcf", "image/vasa");
        mimeMap.put("mcf", "text/mcf");
        mimeMap.put("mcp", "application/netmc");
        mimeMap.put("me", "application/x-troff-me");
        mimeMap.put("mht", "message/rfc822");
        mimeMap.put("mhtml", "message/rfc822");
        mimeMap.put("mid", "application/x-midi");
        mimeMap.put("mid", "audio/midi");
        mimeMap.put("mid", "audio/x-mid");
        mimeMap.put("mid", "audio/x-midi");
        mimeMap.put("mid", "music/crescendo");
        mimeMap.put("mid", "x-music/x-midi");
        mimeMap.put("midi", "application/x-midi");
        mimeMap.put("midi", "audio/midi");
        mimeMap.put("midi", "audio/x-mid");
        mimeMap.put("midi", "audio/x-midi");
        mimeMap.put("midi", "music/crescendo");
        mimeMap.put("midi", "x-music/x-midi");
        mimeMap.put("mif", "application/x-frame");
        mimeMap.put("mif", "application/x-mif");
        mimeMap.put("mime", "message/rfc822");
        mimeMap.put("mime", "www/mime");
        mimeMap.put("mjf", "audio/x-vndaudioexplosionmjuicemediafile");
        mimeMap.put("mjpg", "video/x-motion-jpeg");
        mimeMap.put("mm", "application/base64");
        mimeMap.put("mm", "application/x-meme");
        mimeMap.put("mme", "application/base64");
        mimeMap.put("mod", "audio/mod");
        mimeMap.put("mod", "audio/x-mod");
        mimeMap.put("moov", "video/quicktime");
        mimeMap.put("mov", "video/quicktime");
        mimeMap.put("movie", "video/x-sgi-movie");
        mimeMap.put("mp2", "audio/mpeg");
        mimeMap.put("mp2", "audio/x-mpeg");
        mimeMap.put("mp2", "video/mpeg");
        mimeMap.put("mp2", "video/x-mpeg");
        mimeMap.put("mp2", "video/x-mpeq2a");
        mimeMap.put("mp3", "audio/mpeg3");
        mimeMap.put("mp3", "audio/x-mpeg-3");
        mimeMap.put("mp3", "video/mpeg");
        mimeMap.put("mp3", "video/x-mpeg");
        mimeMap.put("mpa", "audio/mpeg");
        mimeMap.put("mpa", "video/mpeg");
        mimeMap.put("mpc", "application/x-project");
        mimeMap.put("mpe", "video/mpeg");
        mimeMap.put("mpeg", "video/mpeg");
        mimeMap.put("mpg", "audio/mpeg");
        mimeMap.put("mpg", "video/mpeg");
        mimeMap.put("mpga", "audio/mpeg");
        mimeMap.put("mpp", "application/vndms-project");
        mimeMap.put("mpt", "application/x-project");
        mimeMap.put("mpv", "application/x-project");
        mimeMap.put("mpx", "application/x-project");
        mimeMap.put("mrc", "application/marc");
        mimeMap.put("ms", "application/x-troff-ms");
        mimeMap.put("mv", "video/x-sgi-movie");
        mimeMap.put("my", "audio/make");
        mimeMap.put("mzz", "application/x-vndaudioexplosionmzz");
        mimeMap.put("nap", "image/naplps");
        mimeMap.put("naplps", "image/naplps");
        mimeMap.put("nc", "application/x-netcdf");
        mimeMap.put("ncm", "application/vndnokiaconfiguration-message");
        mimeMap.put("nif", "image/x-niff");
        mimeMap.put("niff", "image/x-niff");
        mimeMap.put("nix", "application/x-mix-transfer");
        mimeMap.put("nsc", "application/x-conference");
        mimeMap.put("nvd", "application/x-navidoc");
        mimeMap.put("o", "application/octet-stream");
        mimeMap.put("oda", "application/oda");
        mimeMap.put("omc", "application/x-omc");
        mimeMap.put("omcd", "application/x-omcdatamaker");
        mimeMap.put("omcr", "application/x-omcregerator");
        mimeMap.put("p", "text/x-pascal");
        mimeMap.put("p10", "application/pkcs10");
        mimeMap.put("p10", "application/x-pkcs10");
        mimeMap.put("p12", "application/pkcs-12");
        mimeMap.put("p12", "application/x-pkcs12");
        mimeMap.put("p7a", "application/x-pkcs7-signature");
        mimeMap.put("p7c", "application/pkcs7-mime");
        mimeMap.put("p7c", "application/x-pkcs7-mime");
        mimeMap.put("p7m", "application/pkcs7-mime");
        mimeMap.put("p7m", "application/x-pkcs7-mime");
        mimeMap.put("p7r", "application/x-pkcs7-certreqresp");
        mimeMap.put("p7s", "application/pkcs7-signature");
        mimeMap.put("part", "application/pro_eng");
        mimeMap.put("pas", "text/pascal");
        mimeMap.put("pbm", "image/x-portable-bitmap");
        mimeMap.put("pcl", "application/vndhp-pcl");
        mimeMap.put("pcl", "application/x-pcl");
        mimeMap.put("pct", "image/x-pict");
        mimeMap.put("pcx", "image/x-pcx");
        mimeMap.put("pdb", "chemical/x-pdb");
        mimeMap.put("pdf", "application/pdf");
        mimeMap.put("pfunk", "audio/make");
        mimeMap.put("pfunk", "audio/makemyfunk");
        mimeMap.put("pgm", "image/x-portable-graymap");
        mimeMap.put("pgm", "image/x-portable-greymap");
        mimeMap.put("pic", "image/pict");
        mimeMap.put("pict", "image/pict");
        mimeMap.put("pkg", "application/x-newton-compatible-pkg");
        mimeMap.put("pko", "application/vndms-pkipko");
        mimeMap.put("pl", "text/plain");
        mimeMap.put("pl", "text/x-scriptperl");
        mimeMap.put("plx", "application/x-pixclscript");
        mimeMap.put("pm", "image/x-xpixmap");
        mimeMap.put("pm", "text/x-scriptperl-module");
        mimeMap.put("pm4", "application/x-pagemaker");
        mimeMap.put("pm5", "application/x-pagemaker");
        mimeMap.put("png", "image/png");
        mimeMap.put("pnm", "application/x-portable-anymap");
        mimeMap.put("pnm", "image/x-portable-anymap");
        mimeMap.put("pot", "application/mspowerpoint");
        mimeMap.put("pot", "application/vndms-powerpoint");
        mimeMap.put("pov", "model/x-pov");
        mimeMap.put("ppa", "application/vndms-powerpoint");
        mimeMap.put("ppm", "image/x-portable-pixmap");
        mimeMap.put("pps", "application/mspowerpoint");
        mimeMap.put("pps", "application/vndms-powerpoint");
        mimeMap.put("ppt", "application/mspowerpoint");
        mimeMap.put("ppt", "application/powerpoint");
        mimeMap.put("ppt", "application/vndms-powerpoint");
        mimeMap.put("ppt", "application/x-mspowerpoint");
        mimeMap.put("pptx", "application/x-mspowerpoint");
        mimeMap.put("ppz", "application/mspowerpoint");
        mimeMap.put("pre", "application/x-freelance");
        mimeMap.put("prt", "application/pro_eng");
        mimeMap.put("ps", "application/postscript");
        mimeMap.put("psd", "application/octet-stream");
        mimeMap.put("pvu", "paleovu/x-pv");
        mimeMap.put("pwz", "application/vndms-powerpoint");
        mimeMap.put("py", "text/x-scriptphyton");
        mimeMap.put("pyc", "applicaiton/x-bytecodepython");
        mimeMap.put("qcp", "audio/vndqcelp");
        mimeMap.put("qd3", "x-world/x-3dmf");
        mimeMap.put("qd3d", "x-world/x-3dmf");
        mimeMap.put("qif", "image/x-quicktime");
        mimeMap.put("qt", "video/quicktime");
        mimeMap.put("qtc", "video/x-qtc");
        mimeMap.put("qti", "image/x-quicktime");
        mimeMap.put("qtif", "image/x-quicktime");
        mimeMap.put("ra", "audio/x-pn-realaudio");
        mimeMap.put("ra", "audio/x-pn-realaudio-plugin");
        mimeMap.put("ra", "audio/x-realaudio");
        mimeMap.put("ram", "audio/x-pn-realaudio");
        mimeMap.put("ras", "application/x-cmu-raster");
        mimeMap.put("ras", "image/cmu-raster");
        mimeMap.put("ras", "image/x-cmu-raster");
        mimeMap.put("rast", "image/cmu-raster");
        mimeMap.put("rexx", "text/x-scriptrexx");
        mimeMap.put("rf", "image/vndrn-realflash");
        mimeMap.put("rgb", "image/x-rgb");
        mimeMap.put("rm", "application/vndrn-realmedia");
        mimeMap.put("rm", "audio/x-pn-realaudio");
        mimeMap.put("rmi", "audio/mid");
        mimeMap.put("rmm", "audio/x-pn-realaudio");
        mimeMap.put("rmp", "audio/x-pn-realaudio");
        mimeMap.put("rmp", "audio/x-pn-realaudio-plugin");
        mimeMap.put("rng", "application/ringing-tones");
        mimeMap.put("rng", "application/vndnokiaringing-tone");
        mimeMap.put("rnx", "application/vndrn-realplayer");
        mimeMap.put("roff", "application/x-troff");
        mimeMap.put("rp", "image/vndrn-realpix");
        mimeMap.put("rpm", "audio/x-pn-realaudio-plugin");
        mimeMap.put("rt", "text/richtext");
        mimeMap.put("rt", "text/vndrn-realtext");
        mimeMap.put("rtf", "application/rtf");
        mimeMap.put("rtf", "application/x-rtf");
        mimeMap.put("rtf", "text/richtext");
        mimeMap.put("rtx", "application/rtf");
        mimeMap.put("rtx", "text/richtext");
        mimeMap.put("rv", "video/vndrn-realvideo");
        mimeMap.put("s", "text/x-asm");
        mimeMap.put("s3m", "audio/s3m");
        mimeMap.put("saveme", "application/octet-stream");
        mimeMap.put("sbk", "application/x-tbook");
        mimeMap.put("scm", "application/x-lotusscreencam");
        mimeMap.put("scm", "text/x-scriptguile");
        mimeMap.put("scm", "text/x-scriptscheme");
        mimeMap.put("scm", "video/x-scm");
        mimeMap.put("sdml", "text/plain");
        mimeMap.put("sdp", "application/sdp");
        mimeMap.put("sdp", "application/x-sdp");
        mimeMap.put("sdr", "application/sounder");
        mimeMap.put("sea", "application/sea");
        mimeMap.put("sea", "application/x-sea");
        mimeMap.put("set", "application/set");
        mimeMap.put("sgm", "text/sgml");
        mimeMap.put("sgm", "text/x-sgml");
        mimeMap.put("sgml", "text/sgml");
        mimeMap.put("sgml", "text/x-sgml");
        mimeMap.put("sh", "application/x-bsh");
        mimeMap.put("sh", "application/x-sh");
        mimeMap.put("sh", "application/x-shar");
        mimeMap.put("sh", "text/x-scriptsh");
        mimeMap.put("shar", "application/x-bsh");
        mimeMap.put("shar", "application/x-shar");
        mimeMap.put("shtml", "text/html");
        mimeMap.put("shtml", "text/x-server-parsed-html");
        mimeMap.put("sid", "audio/x-psid");
        mimeMap.put("sit", "application/x-sit");
        mimeMap.put("sit", "application/x-stuffit");
        mimeMap.put("skd", "application/x-koan");
        mimeMap.put("skm", "application/x-koan");
        mimeMap.put("skp", "application/x-koan");
        mimeMap.put("skt", "application/x-koan");
        mimeMap.put("sl", "application/x-seelogo");
        mimeMap.put("smi", "application/smil");
        mimeMap.put("smil", "application/smil");
        mimeMap.put("snd", "audio/basic");
        mimeMap.put("snd", "audio/x-adpcm");
        mimeMap.put("sol", "application/solids");
        mimeMap.put("spc", "application/x-pkcs7-certificates");
        mimeMap.put("spc", "text/x-speech");
        mimeMap.put("spl", "application/futuresplash");
        mimeMap.put("spr", "application/x-sprite");
        mimeMap.put("sprite", "application/x-sprite");
        mimeMap.put("src", "application/x-wais-source");
        mimeMap.put("ssi", "text/x-server-parsed-html");
        mimeMap.put("ssm", "application/streamingmedia");
        mimeMap.put("sst", "application/vndms-pkicertstore");
        mimeMap.put("step", "application/step");
        mimeMap.put("stl", "application/sla");
        mimeMap.put("stl", "application/vndms-pkistl");
        mimeMap.put("stl", "application/x-navistyle");
        mimeMap.put("stp", "application/step");
        mimeMap.put("sv4cpio", "application/x-sv4cpio");
        mimeMap.put("sv4crc", "application/x-sv4crc");
        mimeMap.put("svf", "image/vnddwg");
        mimeMap.put("svf", "image/x-dwg");
        mimeMap.put("svr", "application/x-world");
        mimeMap.put("svr", "x-world/x-svr");
        mimeMap.put("swf", "application/x-shockwave-flash");
        mimeMap.put("t", "application/x-troff");
        mimeMap.put("talk", "text/x-speech");
        mimeMap.put("tar", "application/x-tar");
        mimeMap.put("tbk", "application/toolbook");
        mimeMap.put("tbk", "application/x-tbook");
        mimeMap.put("tcl", "application/x-tcl");
        mimeMap.put("tcl", "text/x-scripttcl");
        mimeMap.put("tcsh", "text/x-scripttcsh");
        mimeMap.put("tex", "application/x-tex");
        mimeMap.put("texi", "application/x-texinfo");
        mimeMap.put("texinfo", "application/x-texinfo");
        mimeMap.put("text", "application/plain");
        mimeMap.put("text", "text/plain");
        mimeMap.put("tgz", "application/gnutar");
        mimeMap.put("tgz", "application/x-compressed");
        mimeMap.put("tif", "image/tiff");
        mimeMap.put("tif", "image/x-tiff");
        mimeMap.put("tiff", "image/tiff");
        mimeMap.put("tiff", "image/x-tiff");
        mimeMap.put("tr", "application/x-troff");
        mimeMap.put("tsi", "audio/tsp-audio");
        mimeMap.put("tsp", "application/dsptype");
        mimeMap.put("tsp", "audio/tsplayer");
        mimeMap.put("tsv", "text/tab-separated-values");
        mimeMap.put("turbot", "image/florian");
        mimeMap.put("txt", "text/plain");
        mimeMap.put("uil", "text/x-uil");
        mimeMap.put("uni", "text/uri-list");
        mimeMap.put("unis", "text/uri-list");
        mimeMap.put("unv", "application/i-deas");
        mimeMap.put("uri", "text/uri-list");
        mimeMap.put("uris", "text/uri-list");
        mimeMap.put("ustar", "application/x-ustar");
        mimeMap.put("ustar", "multipart/x-ustar");
        mimeMap.put("uu", "application/octet-stream");
        mimeMap.put("uu", "text/x-uuencode");
        mimeMap.put("uue", "text/x-uuencode");
        mimeMap.put("vcd", "application/x-cdlink");
        mimeMap.put("vcs", "text/x-vcalendar");
        mimeMap.put("vda", "application/vda");
        mimeMap.put("vdo", "video/vdo");
        mimeMap.put("vew", "application/groupwise");
        mimeMap.put("viv", "video/vivo");
        mimeMap.put("viv", "video/vndvivo");
        mimeMap.put("vivo", "video/vivo");
        mimeMap.put("vivo", "video/vndvivo");
        mimeMap.put("vmd", "application/vocaltec-media-desc");
        mimeMap.put("vmf", "application/vocaltec-media-file");
        mimeMap.put("voc", "audio/voc");
        mimeMap.put("voc", "audio/x-voc");
        mimeMap.put("vos", "video/vosaic");
        mimeMap.put("vox", "audio/voxware");
        mimeMap.put("vqe", "audio/x-twinvq-plugin");
        mimeMap.put("vqf", "audio/x-twinvq");
        mimeMap.put("vql", "audio/x-twinvq-plugin");
        mimeMap.put("vrml", "application/x-vrml");
        mimeMap.put("vrml", "model/vrml");
        mimeMap.put("vrml", "x-world/x-vrml");
        mimeMap.put("vrt", "x-world/x-vrt");
        mimeMap.put("vsd", "application/x-visio");
        mimeMap.put("vst", "application/x-visio");
        mimeMap.put("vsw", "application/x-visio");
        mimeMap.put("w60", "application/wordperfect60");
        mimeMap.put("w61", "application/wordperfect61");
        mimeMap.put("w6w", "application/msword");
        mimeMap.put("wav", "audio/wav");
        mimeMap.put("wav", "audio/x-wav");
        mimeMap.put("wb1", "application/x-qpro");
        mimeMap.put("wbmp", "image/vndwapwbmp");
        mimeMap.put("web", "application/vndxara");
        mimeMap.put("wiz", "application/msword");
        mimeMap.put("wk1", "application/x-123");
        mimeMap.put("wmf", "windows/metafile");
        mimeMap.put("wml", "text/vndwapwml");
        mimeMap.put("wmlc", "application/vndwapwmlc");
        mimeMap.put("wmls", "text/vndwapwmlscript");
        mimeMap.put("wmlsc", "application/vndwapwmlscriptc");
        mimeMap.put("word", "application/msword");
        mimeMap.put("wp", "application/wordperfect");
        mimeMap.put("wp5", "application/wordperfect");
        mimeMap.put("wp5", "application/wordperfect60");
        mimeMap.put("wp6", "application/wordperfect");
        mimeMap.put("wpd", "application/wordperfect");
        mimeMap.put("wpd", "application/x-wpwin");
        mimeMap.put("wq1", "application/x-lotus");
        mimeMap.put("wri", "application/mswrite");
        mimeMap.put("wri", "application/x-wri");
        mimeMap.put("wrl", "application/x-world");
        mimeMap.put("wrl", "model/vrml");
        mimeMap.put("wrl", "x-world/x-vrml");
        mimeMap.put("wrz", "model/vrml");
        mimeMap.put("wrz", "x-world/x-vrml");
        mimeMap.put("wsc", "text/scriplet");
        mimeMap.put("wsrc", "application/x-wais-source");
        mimeMap.put("wtk", "application/x-wintalk");
        mimeMap.put("xbm", "image/x-xbitmap");
        mimeMap.put("xbm", "image/x-xbm");
        mimeMap.put("xbm", "image/xbm");
        mimeMap.put("xdr", "video/x-amt-demorun");
        mimeMap.put("xgz", "xgl/drawing");
        mimeMap.put("xif", "image/vndxiff");
        mimeMap.put("xl", "application/excel");
        mimeMap.put("xla", "application/excel");
        mimeMap.put("xla", "application/x-excel");
        mimeMap.put("xla", "application/x-msexcel");
        mimeMap.put("xlb", "application/excel");
        mimeMap.put("xlb", "application/vndms-excel");
        mimeMap.put("xlb", "application/x-excel");
        mimeMap.put("xlc", "application/excel");
        mimeMap.put("xlc", "application/vndms-excel");
        mimeMap.put("xlc", "application/x-excel");
        mimeMap.put("xld", "application/excel");
        mimeMap.put("xld", "application/x-excel");
        mimeMap.put("xlk", "application/excel");
        mimeMap.put("xlk", "application/x-excel");
        mimeMap.put("xll", "application/excel");
        mimeMap.put("xll", "application/vndms-excel");
        mimeMap.put("xll", "application/x-excel");
        mimeMap.put("xlm", "application/excel");
        mimeMap.put("xlm", "application/vndms-excel");
        mimeMap.put("xlm", "application/x-excel");
        mimeMap.put("xls", "application/excel");
        mimeMap.put("xls", "application/vndms-excel");
        mimeMap.put("xls", "application/x-excel");
        mimeMap.put("xls", "application/x-msexcel");
        mimeMap.put("xlsx", "application/vnd.ms-excel");
        mimeMap.put("xlt", "application/excel");
        mimeMap.put("xlt", "application/x-excel");
        mimeMap.put("xlv", "application/excel");
        mimeMap.put("xlv", "application/x-excel");
        mimeMap.put("xlw", "application/excel");
        mimeMap.put("xlw", "application/vndms-excel");
        mimeMap.put("xlw", "application/x-excel");
        mimeMap.put("xlw", "application/x-msexcel");
        mimeMap.put("xm", "audio/xm");
        mimeMap.put("xml", "application/xml");
        mimeMap.put("xml", "text/xml");
        mimeMap.put("xmz", "xgl/movie");
        mimeMap.put("xpix", "application/x-vndls-xpix");
        mimeMap.put("xpm", "image/x-xpixmap");
        mimeMap.put("xpm", "image/xpm");
        mimeMap.put("x-png", "image/png");
        mimeMap.put("xsr", "video/x-amt-showrun");
        mimeMap.put("xwd", "image/x-xwd");
        mimeMap.put("xwd", "image/x-xwindowdump");
        mimeMap.put("xyz", "chemical/x-pdb");
        mimeMap.put("z", "application/x-compress");
        mimeMap.put("z", "application/x-compressed");
        mimeMap.put("zip", "application/x-compressed");
        mimeMap.put("zip", "application/x-zip-compressed");
        mimeMap.put("zip", "application/zip");
        mimeMap.put("zip", "multipart/x-zip");
        mimeMap.put("zoo", "application/octet-stream");
        mimeMap.put("zsh", "text/x-scriptzsh");

    }
}
