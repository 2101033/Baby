# ベースイメージの指定
FROM gradle:7.3.3-jdk17 AS build

# 作業ディレクトリの指定
WORKDIR /usr/src/app

# プロジェクトのビルド
COPY . .
RUN gradle clean build -x test

# 二段階ビルドのため、必要なファイルだけを取り出す
FROM adoptopenjdk:17-jre-hotspot-alpine3.14

WORKDIR /app

# 一つ目のビルドステージから必要なファイルをコピー
COPY --from=build /usr/src/app/build/libs/*.jar app.jar

# ポートのエクスポージャ
EXPOSE 8080

# アプリケーションのエントリーポイント
ENTRYPOINT ["java", "-jar", "app.jar"]
