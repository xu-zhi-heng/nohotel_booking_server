package com.xu.nohotel.controller;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xu.nohotel.domain.User;
import com.xu.nohotel.service.UserService;
import com.xu.nohotel.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.net.ssl.SSLException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

@RestController
@RequestMapping("/face")
public class FaceContrller {
    @Autowired
    private UserService userService;
    @RequestMapping("/getToken")
    public Object face(@RequestParam String id,@RequestParam String img) {
        JSONObject jsonObject = new JSONObject();
        String url = "https://api-cn.faceplusplus.com/facepp/v3/detect";
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, byte[]> byteMap = new HashMap<>();
        map.put("api_key", "Bzrk3w-sVCCdk8cy_np-mm4WTuFEIGyz");
        map.put("api_secret", "KiL6iuvKdCWjl4PgBVRhMq-ipGXwzFJE");
        map.put("return_landmark", "0");
        map.put("return_attributes", "gender,age,smiling,emotion,beauty");
        map.put("image_base64", img);
        try {
            byte[] bacd = post(url, map, byteMap);
            String str = new String(bacd);
            Map<String,Object> res = null;
            Gson gson = new Gson();
            res = gson.fromJson(str, new TypeToken<Map<String, Object>>() {
            }.getType());
            List list = (List) res.get("faces");
            Map map1 = (Map) list.get(0);
            String faceToken = (String) map1.get("face_token");
            User user = new User();
            user.setId(Integer.parseInt(id));
            user.setFaceToken(faceToken);
            boolean b = userService.updateFaceToken(user);
            if(b) {
                jsonObject.put(Consts.CODE,1);
                jsonObject.put("faceToken",faceToken);
                jsonObject.put(Consts.MSG,"认证成功");
                return jsonObject;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"认证识别");
        return jsonObject;
    }

    private final static int CONNECT_TIME_OUT = 30000;
    private final static int READ_OUT_TIME = 50000;
    private static String boundaryString = getBoundary();

    private static byte[] post(String urlStr, HashMap<String, String> map, HashMap<String, byte[]> fileMap) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setUseCaches(false);
        con.setRequestMethod("POST");
        con.setConnectTimeout(CONNECT_TIME_OUT);
        con.setReadTimeout(READ_OUT_TIME);
        con.setRequestProperty("accept", "*/*");
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);
        con.setRequestProperty("connection", "Keep-Alive");
        con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
        DataOutputStream obos = new DataOutputStream(con.getOutputStream());
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry) iter.next();
            String key = entry.getKey();
            String value = entry.getValue();
            obos.writeBytes("--" + boundaryString + "\r\n");
            obos.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"\r\n");
            obos.writeBytes("\r\n");
            obos.writeBytes(value + "\r\n");
        }
        if (fileMap != null && fileMap.size() > 0) {
            Iterator fileIter = fileMap.entrySet().iterator();
            while (fileIter.hasNext()) {
                Map.Entry<String, byte[]> fileEntry = (Map.Entry<String, byte[]>) fileIter.next();
                obos.writeBytes("--" + boundaryString + "\r\n");
                obos.writeBytes("Content-Disposition: form-data; name=\"" + fileEntry.getKey()
                        + "\"; filename=\"" + encode(" ") + "\"\r\n");
                obos.writeBytes("\r\n");
                obos.write(fileEntry.getValue());
                obos.writeBytes("\r\n");
            }
        }
        obos.writeBytes("--" + boundaryString + "--" + "\r\n");
        obos.writeBytes("\r\n");
        obos.flush();
        obos.close();
        InputStream ins = null;
        int code = con.getResponseCode();
        try {
            if (code == 200) {
                ins = con.getInputStream();
            } else {
                ins = con.getErrorStream();
            }
        } catch (SSLException e) {
            e.printStackTrace();
            return new byte[0];
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buff = new byte[4096];
        int len;
        while ((len = ins.read(buff)) != -1) {
            baos.write(buff, 0, len);
        }
        byte[] bytes = baos.toByteArray();
        ins.close();
        return bytes;
    }

    private static String getBoundary() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_";
        for (int i = 0; i < 32; ++i) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    private static String encode(String value) throws Exception {
        return URLEncoder.encode(value, "UTF-8");
    }

    public static byte[] getBytesFromFile(File f) {
        if (f == null) {
            return null;
        }
        try {
            FileInputStream stream = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = stream.read(b)) != -1)
                out.write(b, 0, n);
            stream.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
        }
        return null;
    }

    @RequestMapping("/validation")
    public String Validation(String flag) {
        System.out.println(flag);
        if("success".equals(flag)) {
            System.out.println("身份确认成功");
            return "ok";
        }
        return null;
    }

}
