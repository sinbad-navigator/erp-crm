package com.ec.common.utils.file;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ec.common.config.ErpCrmConfig;
import com.ec.common.constant.Constants;
import com.ec.common.utils.StringUtils;

/**
 * 图片处理工具类
 *
 * @author ec
 */
public class ImageUtils {
    private static final Logger log = LoggerFactory.getLogger(ImageUtils.class);

    public static byte[] getImage(String imagePath) {
        InputStream is = getFile(imagePath);
        try {
            return IOUtils.toByteArray(is);
        } catch (Exception e) {
            log.error("图片加载异常 {}", e);
            return null;
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    public static InputStream getFile(String imagePath) {
        try {
            byte[] result = readFile(imagePath);
            // Array.copyOf() 用于复制指定的数组内容以达到扩容的目的，该方法对不同的基本数据类型都有对应的重载方法
            //Arrays.copyOf()方法返回的数组是新的数组对象，原数组对象仍是原数组对象，不变，该拷贝不会影响原来的数组。
            // copyOf()的第二个自变量指定要建立的新数组长度，如果新数组的长度超过原数组的长度，则保留数组默认值.
            result = Arrays.copyOf(result, result.length);
            return new ByteArrayInputStream(result);
        } catch (Exception e) {
            log.error("获取图片异常 {}", e);
        }
        return null;
    }

    /**
     * 读取文件为字节数据
     *
     * @param key 地址
     * @return 字节数据
     */
    public static byte[] readFile(String url) {
        InputStream in = null;
        //ByteArrayOutputStream 对byte类型数据进行写入的类 相当于一个中间缓冲层，
        //将类写入到文件等其他outputStream。它是对字节进行操作，属于内存操作流
        ByteArrayOutputStream baos = null;
        try {
            if (url.startsWith("http")) {
                // 网络地址
                URL urlObj = new URL(url);
                /*抽象类 URLConnection 超类，它代表应用程序和 URL 之间的通信链接。
                此类的实例可用于读取和写入此 URL 引用的资源。*/
                URLConnection urlConnection = urlObj.openConnection();
                urlConnection.setConnectTimeout(30 * 1000);
                urlConnection.setReadTimeout(60 * 1000);
                urlConnection.setDoInput(true);
                in = urlConnection.getInputStream();
            } else {
                // 本机地址
                String localPath = ErpCrmConfig.getProfile();
                String downloadPath = localPath + StringUtils.substringAfter(url, Constants.RESOURCE_PREFIX);
                in = new FileInputStream(downloadPath);
            }
            return IOUtils.toByteArray(in);
        } catch (Exception e) {
            log.error("获取文件路径异常 {}", e);
            return null;
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(baos);
        }
    }
}
