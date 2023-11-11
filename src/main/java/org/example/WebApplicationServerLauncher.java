package org.example;

import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class WebApplicationServerLauncher {

    private static final Logger logger = LoggerFactory.getLogger(WebApplicationServerLauncher.class);

    public static void main(String[] args) throws Exception {
        String webappDirLocation = "webapps/"; // Root Directory
        Tomcat tomcat = new Tomcat(); // Tomcat객체 생성
        tomcat.setPort(8080); // 포트 8080 설정

        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath()); // localhost:8080/ 로 접속시 Root Directory인 webapps 하위에서 빌드된 클래스파일(들) 탐색
        logger.info("configuring app with basedir: {}", new File("./" + webappDirLocation).getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();
    }
}