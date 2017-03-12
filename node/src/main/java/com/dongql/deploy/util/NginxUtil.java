package com.dongql.deploy.util;

import com.dongql.deploy.bean.NginxUpStream;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * 处理nginx.conf文件，用新的upstream参数替换原来的
 * Created by dongqilin on 2017/3/12.
 */
public class NginxUtil {

    private NginxUtil(){}

    public static String nginx(String config) {
        String parent = new File(config).getParentFile().getParent();
        String exec;
        if (parent.startsWith("/")) {
            exec = "cd " + parent + " && sbin/nginx";
        } else {
            exec = "cmd.exe /C cd " + parent + " && nginx";
        }
        return exec + " -s reload -c " + config;
    }

    public static boolean refineNginxConf(String path, List<NginxUpStream> config) {
        File file = new File(path);
        StringJoiner joiner = new StringJoiner("\n");
        for (NginxUpStream upStream : config) {
            joiner.add(upStream.toString());
        }
        String data = joiner.toString();
        System.out.println(data);
        return refineNginxConf(file, data);
    }

    private static boolean refineNginxConf(File file, String config) {
        Path path = file.toPath();
        Charset charset = Charset.forName("utf-8");
        List<String> lines;
        try {
            lines = Files.lines(path, charset).collect(Collectors.toList());
        } catch (IOException e) {
            return false;
        }

        try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
            List<String> result = new ArrayList<>();
            boolean hit = false, added = false;
            for (String line : lines) {
                String l = line.trim();
                if (l.startsWith("upstream")) {
                    hit = true;
                    continue;
                }
                if ((hit && l.equals("}"))) {
                    hit = false;
                    continue;
                }
                if (hit) {
                    if (!added) {
                        added = true;
                        result.add(config);
                    }
                    continue;
                }
                result.add(line);
            }
            StringJoiner joiner = new StringJoiner("\n");
            for (String line : result) {
                joiner.add(line);
            }
            String data = joiner.toString();
            writer.write(data);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
