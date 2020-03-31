package com.volkodav4ik;

import java.util.List;
import java.util.Map;

public class ResponseFromPostman {
    private Map<String, String> args;
    private String data;
    private Map<String, String> files;
    private Map<String, String> form;
    private Map<String, String> headers;
    private Map<String, String> json;
    private String url;

    public ResponseFromPostman() {
    }

    private String mapToString(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        if (map.isEmpty()) {
            return "{}";
        } else {
            int count = map.size();
            sb.append("{\n");
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (count == 1) {
                    sb.append("          \"" + entry.getKey() + "\"=\"" + entry.getValue() + "\"\n");
                } else {
                    sb.append("          \"" + entry.getKey() + "\"=\"" + entry.getValue() + "\",\n");
                }
                count--;
            }
            sb.append("}");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "ResponseFromPostman\n{" +
                "\nargs=" + mapToString(args) +
                ", \ndata='" + data + '\'' +
                ", \nfiles=" + mapToString(files) +
                ", \nform=" + mapToString(form) +
                ", \nheaders=" + mapToString(headers) +
                ", \njson=" + mapToString(json) +
                ", \nurl='" + url + '\'' +
                "\n" + '}';
    }
}
