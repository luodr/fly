package com.ldr.util;


import org.apache.commons.fileupload.ProgressListener;

public class ListenerUploadProgress implements ProgressListener {
    
    /**
     * @param bytesRead �Ѿ���ȡ���ֽ���
     * @param contentLength �ļ��ܳ���
     * @param items ��ǰ�ϴ������ĸ��ļ�
     */
    public void update(long bytesRead, long contentLength, int items) {
        System.out.println(bytesRead);
        System.out.println(contentLength);
        System.out.println(items);
    }
    

}