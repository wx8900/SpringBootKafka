package com.test.demo.redis;

import java.io.IOException;
import java.net.Socket;

/**
 * Write Jedis by myself
 * @author Jack
 * @date   2019/08/09   02:48 AM
 */
public class MyJedis {

    public static String set(Socket socket, String key, String value) throws IOException {
        StringBuffer str  = new StringBuffer();
        str.append("*3").append("\r\n");
        str.append("$3").append("\r\n");
        str.append("SET").append("\r\n");
        str.append("$").append(key.getBytes().length).append("\r\n");
        str.append(key).append("\r\n");
        str.append("$").append(value.getBytes().length).append("\r\n");
        str.append(value).append("\r\n");
        //发送RESP
        socket.getOutputStream().write(str.toString().getBytes());
        byte[] response = new byte[2048];
        socket.getInputStream().read(response);
        return new String(response);
    }

    public static String get(Socket socket, String key) throws IOException {
        StringBuffer str  = new StringBuffer();
        str.append("*2").append("\r\n");
        str.append("$3").append("\r\n");
        str.append("GET").append("\r\n");
        str.append("$").append(key.getBytes().length).append("\r\n");
        str.append(key).append("\r\n");
        //发送RESP
        socket.getOutputStream().write(str.toString().getBytes());
        byte[] response = new byte[2048];
        socket.getInputStream().read(response);
        return new String(response);
    }

    /**
     * 打开Redis服务，cd /Users/Jack_Cai/redis-4.0.11
     * src/redis-server
     * redis-cli
     * 127.0.0.1:6379> keys "jack"
     * 127.0.0.1:6379> get "jack001"
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 6379);
        set(socket, "jack001", "180");
        System.out.println(get(socket,"jack"));
    }
}
