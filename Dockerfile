# 使用 OpenJDK 17 作为基础镜像
FROM openjdk:17-jdk-slim

# 设置工作目录
WORKDIR /app

# 复制 jar 包（你 CI 里会构建出这个）
COPY target/photo-judge-backend-0.0.1-SNAPSHOT.jar app.jar

# 启动应用
ENTRYPOINT ["java", "-jar", "app.jar"]