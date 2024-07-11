## 実行方法
1. 実行ファイルのあるディレクトリへ移動
```
cd src
```
2. 全てのjavaファイルをコンパイル
```
javac *.java
```
3. 実行(ファイル名は適宜変更)
```
java -classpath ".:../lib/sqlite-jdbc-3.46.0.0.jar:../lib/slf4j-api-2.0.13.jar:../lib/slf4j-simple-2.0.13.jar" ファイル名
```
ex.
```
java -classpath ".:../lib/sqlite-jdbc-3.46.0.0.jar:../lib/slf4j-api-2.0.13.jar:../lib/slf4j-simple-2.0.13.jar" SelectSample
```
